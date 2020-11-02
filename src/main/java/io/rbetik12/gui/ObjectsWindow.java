package io.rbetik12.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ObjectsWindow extends JFrame {
    final private ObjectsPanel objectsPanel;

    public ObjectsWindow() {
        super("Bands");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        objectsPanel = new ObjectsPanel();
        add(objectsPanel);

        pack();
        setSize(800, 600);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    public void updateObjects() {
        objectsPanel.setAlphaTimer();
    }
}
