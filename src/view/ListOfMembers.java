package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListOfMembers extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    
    public ListOfMembers() {
        initComponents();
        loadSampleData();
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("List of Members");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("List of Members");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 153, 204));
        titlePanel.add(titleLabel);
        
        // Table
        String[] columns = {"Member ID", "Name", "Mobile", "Email", "Gender", 
                          "Father Name", "Mother Name", "Gym Time", "Citizenship", "Age", "Amount"};
        
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 153, 204));
        
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        
        backButton.addActionListener(e -> {
            this.dispose();
        });
        
        buttonPanel.add(backButton);
        
        // Add components
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadSampleData() {
        // Add sample data for testing
        Object[] row1 = {"MEM001", "John Doe", "9876543210", "john@email.com", "Male", 
                        "Robert Doe", "Mary Doe", "05:00 AM to 11:00 AM", "123456789", "25", "5000"};
        Object[] row2 = {"MEM002", "Jane Smith", "9876543211", "jane@email.com", "Female", 
                        "William Smith", "Sarah Smith", "04:00 PM to 08:00 PM", "987654321", "28", "5000"};
        
        tableModel.addRow(row1);
        tableModel.addRow(row2);
    }
}