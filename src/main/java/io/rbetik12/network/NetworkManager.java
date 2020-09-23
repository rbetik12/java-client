package io.rbetik12.network;

import io.rbetik12.models.MusicBand;

public class NetworkManager {

    public static boolean Authenticate(String username, String password) {
        return username.equals("rbetik12") && password.equals("123456");
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
}
