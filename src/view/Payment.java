package view;

import java.awt.*;
import javax.swing.*;

public class Payment extends JFrame {
    
    public Payment() {
        initComponents();
        setVisible(true);
    }
    
    private void initComponents() {
        setTitle("Payment Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("Payment Form");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 153, 204));
        titlePanel.add(titleLabel);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);
        
        // Add form fields
        String[] labels = {"Member ID:", "Name:", "Mobile:", "Email:", 
                          "Amount:", "Payment Date:"};
        
        for (String label : labels) {
            JLabel jLabel = new JLabel(label);
            jLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            formPanel.add(jLabel);
            
            JTextField textField = new JTextField();
            formPanel.add(textField);
        }
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 153, 204));
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setForeground(Color.WHITE);
        
        JButton saveButton = new JButton("Save Payment");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        
        // Add actions
        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Searching for member...");
        });
        
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Payment saved successfully!");
            this.dispose();
        });
        
        backButton.addActionListener(e -> {
            this.dispose();
        });
        
        buttonPanel.add(searchButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        
        // Add components
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}