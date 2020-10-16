package io.rbetik12;

import io.rbetik12.gui.WindowManager;
import io.rbetik12.models.WindowType;

import java.awt.*;

public class Main {
    private static void createAndShowAuthUi() {
        WindowManager.loadWindow(WindowType.Auth);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowAuthUi();
            }
        });
    }
}
