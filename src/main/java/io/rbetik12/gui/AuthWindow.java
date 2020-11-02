package io.rbetik12.gui;

import io.rbetik12.models.AuthWindowTranslation;
import io.rbetik12.models.Languages;
import io.rbetik12.models.WindowType;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthWindow extends JFrame {

    final private int windowWidth = 200;
    final private int windowHeight = 220;

    private JLabel errorLabel;
    private JPasswordField passwordField;
    private JTextField usernameField;

    private String usernameString = "Username";
    private String passwordString = "Password";
    private String submitString = "Submit";
    private String closeString = "Close";
    private String errorString = "Error";

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

        drawAuthUi();

        pack();
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void drawAuthUi() {
        JLabel usernameLabel = new JLabel(usernameString);
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);
        usernameField.setAlignmentX(CENTER_ALIGNMENT);
        usernameField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, usernameField.getPreferredSize().height));
        usernameField.setEditable(true);

        JLabel passwordLabel = new JLabel(passwordString);
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
        passwordField.setEditable(true);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        add(passwordField);

        add(Box.createVerticalStrut(10));

        JButton sumbit = new JButton(submitString);
        sumbit.setAlignmentX(CENTER_ALIGNMENT);
        add(sumbit);

        add(Box.createVerticalStrut(10));

        JButton close = new JButton(closeString);
        close.setAlignmentX(CENTER_ALIGNMENT);
        add(close);

        add(Box.createVerticalStrut(5));

        String[] languages = {"Русский", "Deutsche", "Svenska", "English"};

        JComboBox languagesList = new JComboBox(languages);
        languagesList.setSelectedIndex(0);
        languagesList.setMaximumSize(new Dimension(Integer.MAX_VALUE, languagesList.getPreferredSize().height));
        add(languagesList);

        errorLabel = new JLabel(errorString);
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
        errorLabel.setVisible(false);
        add(errorLabel);

        languagesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TranslationManager translationManager = new TranslationManager();
                AuthWindowTranslation windowTranslation;
                switch ((String) languagesList.getSelectedItem()) {
                    case "Русский":
                        windowTranslation = translationManager.getAuthTranslation(Languages.Russian);
                        break;
                    case "Deutsche":
                        windowTranslation = translationManager.getAuthTranslation(Languages.German);
                        break;
                    case "Svenska":
                        windowTranslation = translationManager.getAuthTranslation(Languages.Swedish);
                        break;
                    default:
                        windowTranslation = translationManager.getAuthTranslation(Languages.English);
                        break;
                }
                usernameLabel.setText(windowTranslation.username);
                passwordLabel.setText(windowTranslation.password);
                sumbit.setText(windowTranslation.submit);
                close.setText(windowTranslation.close);
                errorLabel.setText(windowTranslation.error);
            }
        });

        sumbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
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

    private void authenticate() {
        if (NetworkManager.authenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
            errorLabel.setVisible(false);
            WindowManager.loadWindow(WindowType.Table, this);
        } else {
            errorLabel.setVisible(true);
        }
    }
}
