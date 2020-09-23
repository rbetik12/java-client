package io.rbetik12.gui;

import io.rbetik12.models.NetAction;
import io.rbetik12.models.SortBy;
import io.rbetik12.models.WindowType;
import io.rbetik12.network.NetworkManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

        DrawTable();
        DrawCommandsMenu();

        pack();
        setSize(windowWidth, windowHeight);
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    private void DrawCommandsMenu() {
        JPanel commandsPanel = new JPanel(new FlowLayout());
        commandsPanel.setBackground(Color.decode("#673AB7"));
        add(commandsPanel);

        JLabel id = new JLabel("ID");
        id.setAlignmentX(CENTER_ALIGNMENT);
        commandsPanel.add(id);

        JFormattedTextField idField = new JFormattedTextField();
        idField.setValue(0L);
        idField.setColumns(4);
        idField.setEditable(true);
        idField.setMaximumSize(new Dimension(Integer.MAX_VALUE, idField.getPreferredSize().height));
        commandsPanel.add(idField);

        JButton addElementButton = new JButton("Add");
        addElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadModalWindow(WindowType.MusicBand, NetAction.Add);
            }
        });
        commandsPanel.add(addElementButton);

        JButton updateElementButton = new JButton("Update");
        updateElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadModalWindow(WindowType.MusicBand, NetAction.Update, idField.getValue());
            }
        });
        commandsPanel.add(updateElementButton);


        JButton removeElementButton = new JButton("Remove by ID");
        removeElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NetworkManager.Remove((long) idField.getValue());
            }
        });
        commandsPanel.add(removeElementButton);

        commandsPanel.add(Box.createVerticalStrut(100));

        JButton executeScriptButton = new JButton("Execute script");
        executeScriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        commandsPanel.add(executeScriptButton);

        commandsPanel.add(Box.createVerticalStrut(100));

        JButton addIfLower = new JButton("Add if lower");
        addIfLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadModalWindow(WindowType.MusicBand, NetAction.AddIfMin);
            }
        });
        commandsPanel.add(addIfLower);

        commandsPanel.add(Box.createVerticalStrut(100));

        JButton removeGreaterButton = new JButton("Remove greater");
        removeGreaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadModalWindow(WindowType.MusicBand, NetAction.RemoveGreater);
            }
        });
        commandsPanel.add(removeGreaterButton);

        commandsPanel.add(Box.createVerticalStrut(100));

        JButton removeLowerButton = new JButton("Remove lower");
        removeLowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowManager.LoadModalWindow(WindowType.MusicBand, NetAction.RemoveLower);
            }
        });
        commandsPanel.add(removeLowerButton);
    }

    private void DrawTable() {
        JPanel tablePanel = new JPanel();
        add(tablePanel);

        String[][] data = TableManager.getTable(SortBy.id);

        String[] columnNames = {"ID", "Name", "Creation date", "Number of participants", "Genre", "Label"};

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        JLabel label = new JLabel("Music bands");

        tablePanel.add(label);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                String name = table.getColumnName(col);
                String[][] sortedData;
                switch (name) {
                    case "ID":
                        sortedData = TableManager.getTable(SortBy.id);
                        break;
                    case "Name":
                        sortedData = TableManager.getTable(SortBy.name);
                        break;
                    case "Creation name":
                        sortedData = TableManager.getTable(SortBy.creationDate);
                        break;
                    case "Number of participants":
                        sortedData = TableManager.getTable(SortBy.numberOfParticipants);
                        break;
                    case "Genre":
                        sortedData = TableManager.getTable(SortBy.genre);
                        break;
                    case "Label":
                        sortedData = TableManager.getTable(SortBy.label);
                        break;
                    default:
                        sortedData = TableManager.getTable(SortBy.id);
                        break;
                }
                table.setModel(new DefaultTableModel(sortedData, columnNames));
            }
        });
    }
}
