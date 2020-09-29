package io.rbetik12.gui;

import io.rbetik12.models.NetAction;
import io.rbetik12.models.WindowType;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WindowManager {
    public static void LoadWindow(WindowType windowType) {
        OpenWindow(windowType);
    }

    public static void LoadWindow(WindowType windowType, JFrame currentFrame) {
        currentFrame.dispose();
        OpenWindow(windowType);
    }

    public static void LoadModalWindow(WindowType windowType, ActionListener actionListener) {
        OpenModalWindow(windowType, actionListener);
    }

    public static void LoadModalWindow(WindowType windowType, NetAction netAction) {
        OpenModalWindow(windowType, netAction, null);
    }

    public static void LoadModalWindow(WindowType windowType, NetAction netAction, Object parameter) {
        OpenModalWindow(windowType, netAction, parameter);
    }

    private static void OpenWindow(WindowType windowType) {
        switch (windowType) {
            case Table:
                new TableWindow();
                break;
            case Auth:
                new AuthWindow();
                break;
            case Objects:
                new ObjectsWindow();
                break;
            default:
                throw new IllegalArgumentException("Window type " + windowType + " is not supported");
        }
    }

    private static void OpenModalWindow(WindowType type, ActionListener actionListener) {
        switch (type) {
            default:
                throw new IllegalArgumentException("Window type " + type + " is not supported");
        }
    }

    private static void OpenModalWindow(WindowType type, NetAction action, Object parameter) {
        switch (type) {
            case MusicBand:
                if (parameter == null)
                    new MusicBandWindow(action);
                else
                    new MusicBandWindow(action, parameter);
                break;
            default:
                throw new IllegalArgumentException("Window type " + type + " is not supported");
        }
    }
}
