package io.rbetik12.gui;

import io.rbetik12.models.WindowType;

import javax.swing.*;

public class WindowManager {
    public static void LoadWindow(WindowType windowType) {
        OpenWindow(windowType);
    }

    public static void LoadWindow(WindowType windowType, JFrame currentFrame) {
        currentFrame.dispose();
        OpenWindow(windowType);
    }

    private static void OpenWindow(WindowType windowType) {
        switch (windowType) {
            case Table:
                new TableWindow();
                break;
            case Auth:
                new AuthWindow();
                break;
            case MusicBand:
                new MusicBandWindow();
                break;
            default:
                throw new IllegalArgumentException("Window type " + windowType + " is not supported");
        }
    }
}
