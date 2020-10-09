package io.rbetik12.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;

public class Connection {
    private static Connection instance;

    private DatagramSocket socket;
    private InetAddress ip;
    final private int port = 7000;

    private Connection() {
        try {
            ip = InetAddress.getLocalHost();
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Can't create socket: " + e);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e);
        }
    }

    public void Send(Request request) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        try {
            ObjectOutput oo = new ObjectOutputStream(bStream);
            oo.writeObject(request);
            oo.close();
        } catch (IOException e) {
            System.out.println("Can't serialize object: " + e);
        }

        byte[] serializedRequest = bStream.toByteArray();

        DatagramPacket dp = new DatagramPacket(serializedRequest, serializedRequest.length, ip, port);

        System.out.println(request);

        try {
            socket.send(dp);
        } catch (IOException e) {
            System.out.println("Can't send request: " + e);
        }

    }

    public static Connection getConnection() {
        if (instance == null)
            instance = new Connection();
        return instance;
    }
}

