package io.rbetik12.network;

import io.rbetik12.models.MusicBand;
import io.rbetik12.models.User;

public class NetworkManager {

    private static boolean SendAuth(User user) {
        Request request = new Request(CommandType.Auth, user);
        Connection.getConnection().Send(request);
        return true;
    }

    public static boolean Authenticate(String username, String password) {
        boolean res = SendAuth(new User(username, password));
        return res;
    }

    public static void AddElement(MusicBand e) {
        System.out.println("Added new music band: " + e);
    }

    public static void UpdateElement(long id, MusicBand e) {
        System.out.println("Updating element with id: " + id + " " + e);
    }

    public static void Remove(long id) {
        System.out.println("Removed element with id: " + id);
    }

    public static void AddIfMin(MusicBand e) {
        System.out.println("Add if min: " + e);
    }

    public static void RemoveLower(MusicBand e) {
        System.out.println("Remove lower: " + e);
    }

    public static void RemoveGreater(MusicBand e) {
        System.out.println("Remove greater: " + e);
    }

    public static void UpdateElement(MusicBand e) {
        System.out.println("Updating element:" + e);
    }

    public static void Remove(MusicBand e) {
        System.out.println("Removed element: " + e);
    }
}
