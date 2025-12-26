package view;

import java.awt.*;
import javax.swing.*;

public class UpdateandDeleteMember extends JFrame {
    
    public UpdateandDeleteMember() {
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("Update/Delete Member");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("Update/Delete Member");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 153, 204));
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);
        
        // Add form fields
        String[] labels = {"Member ID:", "Name:", "Mobile:", "Email:", 
                          "Gender:", "Amount:", "Status:"};
        
        for (String label : labels) {
            JLabel jLabel = new JLabel(label);
            jLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            formPanel.add(jLabel);
            
            if (label.equals("Gender:")) {
                JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
                formPanel.add(genderCombo);
            } else if (label.equals("Status:")) {
                JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Active", "Inactive"});
                formPanel.add(statusCombo);
            } else {
                JTextField textField = new JTextField();
                formPanel.add(textField);
            }
        }
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 153, 204));
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setForeground(Color.WHITE);
        
        JButton updateButton = new JButton("Update");
        updateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateButton.setBackground(Color.DARK_GRAY);
        updateButton.setForeground(Color.WHITE);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        
        // Add actions
        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Searching for member...");
        });
        
        updateButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Member updated successfully!");
        });
        
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this member?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Member deleted successfully!");
                this.dispose();
            }
        });
        
        backButton.addActionListener(e -> {
            this.dispose();
        });
        
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        // Add components
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}