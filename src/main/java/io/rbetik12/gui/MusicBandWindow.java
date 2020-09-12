package io.rbetik12.gui;

import io.rbetik12.models.Coordinates;
import io.rbetik12.models.Label;
import io.rbetik12.models.MusicBand;
import io.rbetik12.models.MusicGenre;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicBandWindow extends JFrame {
    private final int windowWidth = 300;
    private final int windowHeight = 300;

    public MusicBandWindow() {
        super("Music band");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        DrawInputMenu();

        pack();
        setSize(windowWidth, windowHeight);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void DrawInputMenu() {
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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NetworkManager.AddElement(new MusicBand(
                        nameField.getText(),
                        new Coordinates((double) xField.getValue(), (double) yField.getValue()),
                        (int) numberOfPartField.getValue(),
                        getMusicGenre((String) genreList.getSelectedItem()),
                        new Label(label.getText())
                ));

                dispose();
            }
        });
    }

    private MusicGenre getMusicGenre(String genre) {
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
}
