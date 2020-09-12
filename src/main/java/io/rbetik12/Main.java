package io.rbetik12;

import io.rbetik12.gui.WindowManager;
import io.rbetik12.models.WindowType;

import java.awt.*;

public class Main {
    private static void createAndShowAuthUi() {
        WindowManager.LoadWindow(WindowType.Table);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowAuthUi();
            }
        });
    }
}
