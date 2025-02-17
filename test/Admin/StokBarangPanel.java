import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StokBarangPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private List<String[]> data;

    public StokBarangPanel() {
        setLayout(new BorderLayout());

        data = new ArrayList<>();
        String[] columnNames = {"Nama Barang", "Jumlah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");

        addButton.addActionListener(new AddButtonListener());
        editButton.addActionListener(new EditButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Nama Barang:");
            String quantity = JOptionPane.showInputDialog("Jumlah:");

            if (name != null && quantity != null) {
                data.add(new String[]{name, quantity});
                tableModel.addRow(new String[]{name, quantity});
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String name = JOptionPane.showInputDialog("Nama Barang:", data.get(selectedRow)[0]);
                String quantity = JOptionPane.showInputDialog("Jumlah:", data.get(selectedRow)[1]);

                if (name != null && quantity != null) {
                    data.set(selectedRow, new String[]{name, quantity});
                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(quantity, selectedRow, 1);
                }
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                data.remove(selectedRow);
                tableModel.removeRow(selectedRow);
            }
        }
    }
}