package io.rbetik12.gui;

import io.rbetik12.models.MusicBand;
import io.rbetik12.models.NetAction;
import io.rbetik12.models.WindowType;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WindowManager {
    private static TableWindow tableWindow;
    private static ObjectsWindow objectsWindow;

    public static TableWindow getTableWindow() {
        return tableWindow;
    }

    public static ObjectsWindow getObjectsWindow() {
        return objectsWindow;
    }

    public static void loadWindow(WindowType windowType) {
        openWindow(windowType);
    }

    public static void loadWindow(WindowType windowType, JFrame currentFrame) {
        currentFrame.dispose();
        openWindow(windowType);
    }

    public static void loadModalWindow(WindowType windowType, ActionListener actionListener) {
        OpenModalWindow(windowType, actionListener);
    }

    public static void loadModalWindow(WindowType windowType, NetAction netAction) {
        OpenModalWindow(windowType, netAction, null);
    }

    public static void loadModalWindow(WindowType windowType, NetAction netAction, Object parameter) {
        OpenModalWindow(windowType, netAction, parameter);
    }

    private static void openWindow(WindowType windowType) {
        switch (windowType) {
            case Table:
                tableWindow = new TableWindow();
                break;
            case Auth:
                new AuthWindow();
                break;
            case Objects:
                objectsWindow = new ObjectsWindow();
                break;
            default:
                throw new IllegalArgumentException("Window type " + windowType + " is not supported");
        }
    }

    public static void updateWindows() {
        if (WindowManager.getTableWindow() != null)
            WindowManager.getTableWindow().updateTableModel();
        if (WindowManager.getObjectsWindow() != null)
            WindowManager.getObjectsWindow().updateObjects();
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
            case MusicBandFilled:
                new MusicBandWindowFilled((MusicBand) parameter);
                break;
            default:
                throw new IllegalArgumentException("Window type " + type + " is not supported");
        }
    }
}
