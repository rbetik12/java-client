package io.rbetik12.gui;

import io.rbetik12.models.Coordinates;
import io.rbetik12.models.Label;
import io.rbetik12.models.MusicBand;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicBandWindowFilled extends MusicBandWindow {

    final private MusicBand band;

    public MusicBandWindowFilled(MusicBand e) {
        super();
        band = e;
        init();
    }

    @Override
    protected void DrawInputMenu() {
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setAlignmentX(CENTER_ALIGNMENT);
        nameField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
        nameField.setEditable(true);
        nameField.setText(band.getName());
        add(nameField);

        JLabel xLabel = new JLabel("X");
        xLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(xLabel);

        JFormattedTextField xField = new JFormattedTextField();
        xField.setValue(0.0);
        xField.setColumns(4);
        xField.setEditable(true);
        xField.setMaximumSize(new Dimension(Integer.MAX_VALUE, xField.getPreferredSize().height));
        xField.setValue(band.getCoordinates().getX());
        add(xField);

        JLabel yLabel = new JLabel("Y");
        yLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(yLabel);

        JFormattedTextField yField = new JFormattedTextField();
        yField.setValue(0.0);
        yField.setColumns(4);
        yField.setEditable(true);
        yField.setMaximumSize(new Dimension(Integer.MAX_VALUE, yField.getPreferredSize().height));
        yField.setValue(band.getCoordinates().getY());
        add(yField);

        JLabel numberOfPartLabel = new JLabel("Number of participants");
        numberOfPartLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(numberOfPartLabel);

        JFormattedTextField numberOfPartField = new JFormattedTextField();
        numberOfPartField.setValue(0);
        numberOfPartField.setColumns(4);
        numberOfPartField.setEditable(true);
        numberOfPartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, numberOfPartField.getPreferredSize().height));
        numberOfPartField.setValue(band.getNumberOfParticipants());
        add(numberOfPartField);

        JLabel musicGenreLabel = new JLabel("Band genre");
        musicGenreLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(musicGenreLabel);

        String[] genres = {"Rock", "Hip hop", "Psychedelic cloud rap", "Blues", "Punk rock"};

        JComboBox genreList = new JComboBox(genres);
        genreList.setSelectedIndex(0);
        genreList.setMaximumSize(new Dimension(Integer.MAX_VALUE, numberOfPartField.getPreferredSize().height));
        int index = 0;
        for (int i = 0; i < genres.length; i++) {
            if (band.getGenre().equals(getMusicGenre(genres[i])))
                index = i;
        }
        genreList.setSelectedIndex(index);
        add(genreList);

        JLabel bandLabel = new JLabel("Band label");
        bandLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(bandLabel);

        JTextField label = new JTextField();
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        label.setText(band.getLabel().getName());
        label.setEditable(true);
        add(label);

        add(Box.createVerticalStrut(10));

        JButton sendButton = new JButton("Send");
        sendButton.setAlignmentX(CENTER_ALIGNMENT);
        add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MusicBand band = new MusicBand(
                        nameField.getText(),
                        new Coordinates((double) xField.getValue(), (double) yField.getValue()),
                        (int) numberOfPartField.getValue(),
                        getMusicGenre((String) genreList.getSelectedItem()),
                        new Label(label.getText()),
                        null
                );

                NetworkManager.UpdateElement(band);

                dispose();
            }
        });

        JButton deleteButton = new JButton("Remove");
        deleteButton.setAlignmentX(CENTER_ALIGNMENT);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MusicBand band = new MusicBand(
                        nameField.getText(),
                        new Coordinates((double) xField.getValue(), (double) yField.getValue()),
                        (int) numberOfPartField.getValue(),
                        getMusicGenre((String) genreList.getSelectedItem()),
                        new Label(label.getText()),
                        null
                );

                NetworkManager.Remove(band);

                dispose();
            }
        });
    }
}
