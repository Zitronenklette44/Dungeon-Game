package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import action.Logger;
import rooms.DungeonCore;
import rooms.RoomTemplate;

public class DungeonInfos {

    private static int selectedRow = -1; // Variable to store the selected row index

    public static void erstellen() {
        SwingUtilities.invokeLater(DungeonInfos::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DungeonInfos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setBackground(Color.GRAY.darker());

        // Panel for General Info
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(800, 100)); // Small fixed height

        // General Info Table
        GeneralInfoTableModel generalInfoModel = new GeneralInfoTableModel();
        JTable generalInfoTable = new JTable(generalInfoModel);
        generalInfoTable.setPreferredScrollableViewportSize(new Dimension(800, 50)); // Fit content
        generalInfoTable.setBackground(Color.GRAY);
        generalInfoTable.setForeground(Color.WHITE);
        generalInfoTable.setEnabled(false); // Disable editing
        JScrollPane generalScrollPane = new JScrollPane(generalInfoTable);
        generalScrollPane.getViewport().setBackground(Color.GRAY.darker());
        topPanel.add(generalScrollPane, BorderLayout.CENTER);

        // Table Model for Room Information
        RoomTableModel roomModel = new RoomTableModel(DungeonCore.thisRooms);
        JTable roomTable = new JTable(roomModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Enable single row selection
        roomTable.setBackground(Color.GRAY);
        roomTable.setForeground(Color.WHITE);

        // Custom Renderer to highlight the current room
        roomTable.setDefaultRenderer(Object.class, new RoomTableCellRenderer());

        // ScrollPane for Room Information Table
        JScrollPane roomScrollPane = new JScrollPane(roomTable);
        roomScrollPane.getViewport().setBackground(Color.GRAY.darker());

        // Panel for Teleport Button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton teleportButton = new JButton("Teleport to Selected Room");
        bottomPanel.add(teleportButton, BorderLayout.CENTER);

        // Adding components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(roomScrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Timer for automatic refresh
        Timer timer = new Timer(1000, e -> {
            // Assuming DungeonCore.thisRooms gets updated somewhere else
            generalInfoModel.fireTableDataChanged();
            roomModel.setRooms(DungeonCore.thisRooms);
            roomModel.fireTableDataChanged();

            // Restore the selection after the table is refreshed
            if (selectedRow != -1 && selectedRow < roomTable.getRowCount()) {
                roomTable.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
        timer.start();

        // ActionListener for the teleport button
        teleportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) { // Check if a row is actually selected
                    DungeonCore.changeRoom(selectedRow); // Change to the selected room
                    roomTable.repaint(); // Repaint the table to update the highlighted room
                }
            }
        });

        // ListSelectionListener to keep track of the selected row
        roomTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && roomTable.getSelectedRow() !=-1) {
                    selectedRow = roomTable.getSelectedRow();
                }
            }
        });

        frame.setVisible(true);
    }

    static class GeneralInfoTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private final String[] columnNames = {"Dungeon Type", "Dungeon Length"};
        private final Object[][] data = {
            {DungeonCore.dungeonType, DungeonCore.thisRooms.size()}
        };

        @Override
        public int getRowCount() {
            return 1; // Only one row for general information
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[0][columnIndex];
        }

        @Override
        public void fireTableDataChanged() {
            data[0][0] = DungeonCore.dungeonType;
            data[0][1] = DungeonCore.thisRooms.size();
            super.fireTableDataChanged();
        }
    }

    static class RoomTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private final String[] columnNames = {"Name", "Room Variant", "Image Path", "Enemy Count", "Has Functional Objects"};
        private ArrayList<RoomTemplate> rooms;

        public RoomTableModel(ArrayList<RoomTemplate> rooms) {
            this.rooms = rooms;
        }

        @Override
        public int getRowCount() {
            return rooms.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            RoomTemplate room = rooms.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return room.name;
                case 1:
                    return room.getRoomVariant();
                case 2:
                    return room.ImagePath.substring(room.ImagePath.lastIndexOf('/') + 1);
                case 3:
                    return room.getEntitys() != null ? room.getEntitys().length : 0;
                case 4:
                    return !room.getFunctional().isEmpty();
                default:
                    return null;
            }
        }

        public void setRooms(ArrayList<RoomTemplate> rooms) {
            this.rooms = rooms;
        }
    }

    static class RoomTableCellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row == DungeonCore.currentRoom) {
                cell.setBackground(Color.BLACK); // Highlight the current room
            } else {
                cell.setBackground(isSelected ? Color.LIGHT_GRAY : Color.GRAY); // Default background with selection highlight
                cell.setForeground(Color.WHITE); // Default foreground
            }
            return cell;
        }
    }
}
