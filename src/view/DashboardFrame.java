package view;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private JMenuBar menuBar;
    private JLabel welcomeLabel;

    public DashboardFrame() {
        initComponents();
        setupMenuActions();
    }

    private void initComponents() {
        setTitle("Gym Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create Menu Bar
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.DARK_GRAY);
        menuBar.setForeground(Color.WHITE);

        // Create Menus
        String[] menuItems = {"New Member", "Update & Delete Member", "List Of Members", "Payment", "Logout", "Exit"};
        
        for (String item : menuItems) {
            JMenu menu = new JMenu(item);
            menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
            menu.setForeground(Color.WHITE);
            menuBar.add(menu);
            
            // Store reference for later use
            menu.putClientProperty("menuName", item);
        }
        
        setJMenuBar(menuBar);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 153, 204));
        mainPanel.setLayout(new GridBagLayout());

        welcomeLabel = new JLabel("Welcome to Gym Management System!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);

        mainPanel.add(welcomeLabel);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupMenuActions() {
        // Get all menus and attach actions
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            String menuName = (String) menu.getClientProperty("menuName");
            
            menu.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    handleMenuClick(menuName);
                }
            });
        }
    }

    private void handleMenuClick(String menuName) {
        switch (menuName) {
            case "New Member":
                JOptionPane.showMessageDialog(this, "Opening New Member Form...");
                // Open New Member Form
                NewMember newMemberFrame = new NewMember();
                newMemberFrame.setVisible(true);
                newMemberFrame.setLocationRelativeTo(null);
                break;
                
            case "Update & Delete Member":
                JOptionPane.showMessageDialog(this, "Opening Update/Delete Member Form...");
                // Open Update/Delete Form
                UpdateandDeleteMember updateFrame = new UpdateandDeleteMember();
                updateFrame.setVisible(true);
                updateFrame.setLocationRelativeTo(null);
                break;
                
            case "List Of Members":
                JOptionPane.showMessageDialog(this, "Opening List of Members...");
                // Open List of Members
                ListOfMembers listFrame = new ListOfMembers();
                listFrame.setVisible(true);
                listFrame.setLocationRelativeTo(null);
                break;
                
            case "Payment":
                JOptionPane.showMessageDialog(this, "Opening Payment Form...");
                // Open Payment Form
                Payment paymentFrame = new Payment();
                paymentFrame.setVisible(true);
                paymentFrame.setLocationRelativeTo(null);
                break;
                
            case "Logout":
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to logout?", 
                    "Confirm Logout", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    this.dispose();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                }
                break;
                
            case "Exit":
                System.exit(0);
                break;
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame();
            frame.setVisible(true);
        });
    }
}