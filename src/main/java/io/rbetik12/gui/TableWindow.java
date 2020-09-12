package io.rbetik12.gui;

import io.rbetik12.models.WindowType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TableWindow extends JFrame {
    private final int windowWidth = 800;
    private final int windowHeight = 600;

    public TableWindow() {
        super("Client");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        DrawCommandsMenu();

        pack();
        setSize(windowWidth, windowHeight);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void DrawCommandsMenu() {
        JPanel commandsPanel = new JPanel(new FlowLayout());
        commandsPanel.setMaximumSize(new Dimension(windowWidth / 3, Integer.MAX_VALUE));
        commandsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        commandsPanel.setBackground(Color.decode("#673AB7"));
        add(commandsPanel);

        JButton addElementButton = new JButton("Add");
        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadWindow(WindowType.MusicBand);
            }
        });
        commandsPanel.add(addElementButton);
    }
}
