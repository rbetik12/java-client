package io.rbetik12.network;

import io.rbetik12.collection.CollectionManager;
import io.rbetik12.gui.WindowManager;
import io.rbetik12.models.MusicBand;
import io.rbetik12.models.MusicCollection;
import io.rbetik12.models.User;

import javax.swing.*;
import java.net.SocketException;
import java.time.ZonedDateTime;

public class NetworkManager {

    private static boolean sendAuth(User user) {
        boolean authResult = false;
        Request request = new Request(CommandType.Auth, user);
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return false;
            }
            CookieStorage.cookies = response.getCookies();

            authResult = response.getCookie("Auth").equals("yes");
            if (authResult) {
                CollectionManager.getManager().setCollection((MusicCollection) response.getBody());
            }
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
        return authResult;
    }

    public static boolean authenticate(String username, String password) {
        boolean res = sendAuth(new User(username, password));
        return res;
    }

    public static void addElement(MusicBand e) {
        Request request = new Request(CommandType.Add, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException exception) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void updateElement(long id, MusicBand e) {
        e.setId(id);
        Request request = new Request(CommandType.UpdateElement, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void remove(long id) {
        System.out.println("Removed element with id: " + id);
        Request request = new Request(CommandType.RemoveElement, id);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void addIfMin(MusicBand e) {
        System.out.println("Add if min: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.AddIfMin, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void removeLower(MusicBand e) {
        System.out.println("Remove lower: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.RemoveLower, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void removeGreater(MusicBand e) {
        System.out.println("Remove greater: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.RemoveGreater, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        try {
            Connection.getConnection().send(request);
            Response response = Connection.getConnection().receive();
            if (response == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
                return;
            }
            MusicCollection collection = (MusicCollection) response.getBody();
            CollectionManager.getManager().setCollection(collection);
            WindowManager.getTableWindow().updateTableModel();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Server is down!");
        }
    }

    public static void updateElement(MusicBand e) {
        System.out.println("Updating element:" + e);
        updateElement(e.getId(), e);
    }

    public static void remove(MusicBand e) {
        remove(e.getId());
    }
}
