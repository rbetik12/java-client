package io.rbetik12.network;

import java.io.*;
import java.net.*;

public class Connection {
    private static Connection instance;

    private DatagramSocket socket;
    private InetAddress ip;
    private byte buffer[];
    final private int port = 7000;

    private Connection() {
        try {
            ip = InetAddress.getLocalHost();
            socket = new DatagramSocket();
            buffer = new byte[256000];
        } catch (SocketException e) {
            System.out.println("Can't create socket: " + e);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e);
        }
//        send(new Request(CommandType.OpenConnection, "Open connection"));
    }

    public void send(Request request) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        try {
            ObjectOutput oo = new ObjectOutputStream(bStream);
            oo.writeObject(request);
            oo.close();
        } catch (IOException e) {
            System.out.println("Can't serialize object: " + e);
            return;
        }

        byte[] serializedRequest = bStream.toByteArray();

        DatagramPacket dp = new DatagramPacket(serializedRequest, serializedRequest.length, ip, port);

        try {
            socket.send(dp);
        } catch (IOException e) {
            System.out.println("Can't send request: " + e);
        }

    }

    public Response receive() {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            System.out.println("Can't receive packet: " + e);
            return null;
        }

        ByteArrayInputStream in = new ByteArrayInputStream(packet.getData());
        Response response;
        try(ObjectInputStream is = new ObjectInputStream(in)) {
            response = (Response) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Can't deserialize object: " + e);
            return null;
        }

        return response;
    }

    public static Connection getConnection() {
        if (instance == null)
            instance = new Connection();
        return instance;
    }
}

