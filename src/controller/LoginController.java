/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.LoginManager;
import view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import view.DashboardFrame;

public class LoginController {
    private LoginManager loginManager;
    private LoginFrame loginFrame;
    
    // Constructor - connects Model and View
    public LoginController(LoginManager loginManager, LoginFrame loginFrame) {
        this.loginManager = loginManager;
        this.loginFrame = loginFrame;
        
        // Set up all button actions
        setupLoginButton();
        setupShowPasswordCheckbox();
        setupEnterKeyActions();
    }
    
    // 1. Set up Login Button Action
    private void setupLoginButton() {
        loginFrame.getBtnLogin().addActionListener(e -> {
            System.out.println("Login button clicked"); // Debug
            login();
        });
    }
    
    // 2. Set up Show Password Checkbox (if not already in view)
    private void setupShowPasswordCheckbox() {
        loginFrame.getChkShowPassword().addActionListener(e -> {
            if (loginFrame.getChkShowPassword().isSelected()) {
                // Show password as plain text
                loginFrame.getTxtPassword().setEchoChar((char) 0);
            } else {
                // Hide password with dots
                loginFrame.getTxtPassword().setEchoChar('•');
            }
        });
    }
    
    // 3. Set up Enter key to trigger login
    private void setupEnterKeyActions() {
        // Enter key in username field
        loginFrame.getTxtUsername().addActionListener(e -> login());
        
        // Enter key in password field
        loginFrame.getTxtPassword().addActionListener(e -> login());
    }
    
    // 4. Main Login Logic
    private void login() {
        System.out.println("Attempting login..."); // Debug
        
        // Get values from view
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();
        
        System.out.println("Username: " + username);
        System.out.println("Password: " + password.replaceAll(".", "*")); // Hide password in logs
        
        // VALIDATION 1: Empty fields
        if (username.isEmpty()) {
            showErrorMessage("Please enter username");
            loginFrame.getTxtUsername().requestFocus(); // Focus on username field
            return;
        }
        
        if (password.isEmpty()) {
            showErrorMessage("Please enter password");
            loginFrame.getTxtPassword().requestFocus(); // Focus on password field
            return;
        }
        
        // VALIDATION 2: Check credentials using Model
        boolean isValid = loginManager.validateLogin(username, password);
        
        if (isValid) {
            // SUCCESS: Login approved
            System.out.println("Login successful for user: " + username);
            
            // Show success message
            loginFrame.showMessage("Login successful! Redirecting...", false);
            
            // Get user role
            String role = loginManager.getUserRole(username);
            System.out.println("User role: " + role);
            
            // Wait 1 second then open dashboard
            Timer timer = new Timer(1000, (ActionEvent e) -> {
                openDashboard(role, username);
                loginFrame.dispose(); // Close login window
            });
            timer.setRepeats(false);
            timer.start();
            
        } else {
            // FAILURE: Invalid credentials
            System.out.println("Login failed - invalid credentials");
            showErrorMessage("Invalid username or password");
            
            // Clear password field for security
            loginFrame.clearPassword();
            
            // Focus back on password field
            loginFrame.getTxtPassword().requestFocus();
        }
    }
    
    // 5. Show Error Message
    private void showErrorMessage(String message) {
        loginFrame.showMessage(message, true);
        
        // Optional: Play error sound or shake window
        Toolkit.getDefaultToolkit().beep(); // Beep sound
    }
    
    // 6. Open Dashboard After Successful Login
    private void openDashboard(String role, String username) {
        System.out.println("Opening dashboard for: " + username + " (" + role + ")");
        
        try {
            // Create and show dashboard
            DashboardFrame dashboard = new DashboardFrame(role, username);
            dashboard.setVisible(true);
            
            // You can create a DashboardController here later
            // new DashboardController(dashboard, role, username);
            
        } catch (Exception e) {
            System.err.println("Error opening dashboard: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback: Show error and exit
            JOptionPane.showMessageDialog(null,
                "Error opening main application: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // 7. Optional: Reset/Exit Methods
    public void resetLoginForm() {
        loginFrame.clearFields();
        loginFrame.showMessage("", false);
        loginFrame.getTxtUsername().requestFocus();
    }
    
    public void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(loginFrame,
            "Are you sure you want to exit?",
            "Exit Gym Management System",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    } 
}
