package io.rbetik12.network;

import io.rbetik12.collection.CollectionManager;
import io.rbetik12.models.MusicBand;
import io.rbetik12.models.MusicCollection;
import io.rbetik12.models.User;

import java.time.ZonedDateTime;

public class NetworkManager {

    private static boolean sendAuth(User user) {
        Request request = new Request(CommandType.Auth, user);
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        CookieStorage.cookies = response.getCookies();

        boolean authResult = response.getCookie("Auth").equals("yes");
        if (authResult) {
            CollectionManager.getManager().setCollection((MusicCollection) response.getBody());
        }
        return authResult;
    }

    public static boolean Authenticate(String username, String password) {
        boolean res = sendAuth(new User(username, password));
        return res;
    }

    public static void addElement(MusicBand e) {
        Request request = new Request(CommandType.Add, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void updateElement(long id, MusicBand e) {
        e.setId(id);
        Request request = new Request(CommandType.UpdateElement, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void remove(long id) {
        System.out.println("Removed element with id: " + id);
        Request request = new Request(CommandType.RemoveElement, id);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void addIfMin(MusicBand e) {
        System.out.println("Add if min: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.AddIfMin, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void removeLower(MusicBand e) {
        System.out.println("Remove lower: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.RemoveLower, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void removeGreater(MusicBand e) {
        System.out.println("Remove greater: " + e);
        e.setCreationDate(ZonedDateTime.now());
        Request request = new Request(CommandType.RemoveGreater, e);
        request.addCookie("UserId", CookieStorage.cookies.get("UserId"));
        Connection.getConnection().send(request);
        Response response = Connection.getConnection().receive();
        MusicCollection collection = (MusicCollection) response.getBody();
        CollectionManager.getManager().setCollection(collection);
    }

    public static void updateElement(MusicBand e) {
        System.out.println("Updating element:" + e);
    }

    public static void remove(MusicBand e) {
        System.out.println("Removed element: " + e);
    }
}
