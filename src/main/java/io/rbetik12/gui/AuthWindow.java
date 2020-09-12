package io.rbetik12.gui;

import io.rbetik12.models.WindowType;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthWindow extends JFrame {

    final private int windowWidth = 200;
    final private int windowHeight = 200;

    private JLabel errorLabel;
    private JPasswordField passwordField;
    private JTextField usernameField;

    public AuthWindow() {
        super("Authorization");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        DrawAuthUi();

        pack();
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void DrawAuthUi() {
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);
        usernameField.setAlignmentX(CENTER_ALIGNMENT);
        usernameField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, usernameField.getPreferredSize().height));
        usernameField.setEditable(true);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
        passwordField.setEditable(true);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        add(passwordField);

        add(Box.createVerticalStrut(10));

        JButton sumbit = new JButton("Submit");
        sumbit.setAlignmentX(CENTER_ALIGNMENT);
        add(sumbit);

        add(Box.createVerticalStrut(10));

        JButton close = new JButton("Close");
        close.setAlignmentX(CENTER_ALIGNMENT);
        add(close);

        add(Box.createVerticalStrut(5));

        errorLabel = new JLabel("Error: error");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        errorLabel.setVisible(false);
        add(errorLabel);

        sumbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Authenticate();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void Authenticate() {
        if (NetworkManager.Authenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
            errorLabel.setVisible(false);
            WindowManager.LoadWindow(WindowType.Table, this);
        } else {
            errorLabel.setVisible(true);
        }
    }
}
