package io.rbetik12.gui;

import io.rbetik12.models.*;
import io.rbetik12.models.Label;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicBandWindow extends JFrame {
    private final int windowWidth = 300;
    private final int windowHeight = 300;
    private ActionListener actionListener;
    private NetAction action;
    private Object parameter;

    public MusicBandWindow() {
        super("Music band");
    }

    public MusicBandWindow(ActionListener actionListener) {
        super("Music band");
        init();
        this.actionListener = actionListener;
    }

    public MusicBandWindow(NetAction action) {
        super("Music band");
        init();
        this.action = action;
    }

    public MusicBandWindow(NetAction action, Object parameter) {
        super("Music band");
        init();
        this.action = action;
        this.parameter = parameter;
    }

    protected void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        drawInputMenu();

        pack();
        setSize(windowWidth, windowHeight);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    protected void drawInputMenu() {
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setAlignmentX(CENTER_ALIGNMENT);
        nameField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
        nameField.setEditable(true);
        add(nameField);

        JLabel xLabel = new JLabel("X");
        xLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(xLabel);

        JFormattedTextField xField = new JFormattedTextField();
        xField.setValue(0.0);
        xField.setColumns(4);
        xField.setEditable(true);
        xField.setMaximumSize(new Dimension(Integer.MAX_VALUE, xField.getPreferredSize().height));
        add(xField);

        JLabel yLabel = new JLabel("Y");
        yLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(yLabel);

        JFormattedTextField yField = new JFormattedTextField();
        yField.setValue(0.0);
        yField.setColumns(4);
        yField.setEditable(true);
        yField.setMaximumSize(new Dimension(Integer.MAX_VALUE, yField.getPreferredSize().height));
        add(yField);

        JLabel numberOfPartLabel = new JLabel("Number of participants");
        numberOfPartLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(numberOfPartLabel);

        JFormattedTextField numberOfPartField = new JFormattedTextField();
        numberOfPartField.setValue(0);
        numberOfPartField.setColumns(4);
        numberOfPartField.setEditable(true);
        numberOfPartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, numberOfPartField.getPreferredSize().height));
        add(numberOfPartField);

        JLabel musicGenreLabel = new JLabel("Band genre");
        musicGenreLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(musicGenreLabel);

        String[] genres = {"Rock", "Hip hop", "Psychedelic cloud rap", "Blues", "Punk rock"};

        JComboBox genreList = new JComboBox(genres);
        genreList.setSelectedIndex(0);
        genreList.setMaximumSize(new Dimension(Integer.MAX_VALUE, numberOfPartField.getPreferredSize().height));
        add(genreList);

        JLabel bandLabel = new JLabel("Band label");
        bandLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(bandLabel);

        JTextField label = new JTextField();
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        label.setEditable(true);
        add(label);

        add(Box.createVerticalStrut(10));

        JButton sendButton = new JButton("Send");
        sendButton.setAlignmentX(CENTER_ALIGNMENT);
        add(sendButton);

        if (actionListener != null) {
            sendButton.addActionListener(actionListener);
        }
        else {
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MusicBand band = new MusicBand(
                            nameField.getText(),
                            new Coordinates((double) xField.getValue(), (double) yField.getValue()),
                            (int) numberOfPartField.getValue(),
                            getMusicGenre((String) genreList.getSelectedItem()),
                            new Label(label.getText()),
                            null
                    );
                    dispose();
                    switch(action) {
                        case Add:
                            NetworkManager.addElement(band);
                            break;
                        case Update:
                            NetworkManager.updateElement((long) parameter, band);
                            break;
                        case AddIfMin:
                            NetworkManager.addIfMin(band);
                            break;
                        case RemoveGreater:
                            NetworkManager.removeGreater(band);
                            break;
                        case RemoveLower:
                            NetworkManager.removeLower(band);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown action type: " + action);
                    }
                }
            });
        }
    }

    public static MusicGenre getMusicGenre(String genre) {
        switch (genre) {
            case "Rock":
                return MusicGenre.ROCK;
            case "Hip hop":
                return MusicGenre.HIP_HOP;
            case "Psychedelic cloud rap":
                return MusicGenre.PSYCHEDELIC_CLOUD_RAP;
            case "Blues":
                return MusicGenre.BLUES;
            case "Punk rock":
                return MusicGenre.PUNK_ROCK;
            default:
                throw new IllegalArgumentException("Cannot parse genre: " + genre);
        }
    }

    public static String getMusicGenreName(MusicGenre genre) {
        switch (genre) {
            case ROCK:
                return "Rock";
            case HIP_HOP:
                return "Hip hop";
            case PSYCHEDELIC_CLOUD_RAP:
                return "Psychedelic cloud rap";
            case BLUES:
                return "Blues";
            case PUNK_ROCK:
                return "Punk rock";
            default:
                throw new IllegalArgumentException("Cannot parse genre: " + genre);
        }
    }
}
