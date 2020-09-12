package io.rbetik12.network;

import io.rbetik12.models.MusicBand;

public class NetworkManager {

    public static boolean Authenticate(String username, String password) {
        return username.equals("rbetik12") && password.equals("123456");
    }

    public static void AddElement(MusicBand e) {
        System.out.println("Added new music band: " + e);
    }
}
