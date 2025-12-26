package controller;

import model.GymManager;
import model.LoginManager;
import view.LoginFrame;
import view.DashboardFrame;
import javax.swing.*;

public class SimpleLoginController {
    private LoginFrame view;
    
    public SimpleLoginController(LoginFrame view) {
        this.view = view;
        setupListeners();
    }
    
    private void setupListeners() {
        try {
            // Get fields using reflection
            java.lang.reflect.Field btnField = LoginFrame.class.getDeclaredField("btnLogin");
            btnField.setAccessible(true);
            JButton loginButton = (JButton) btnField.get(view);
            
            java.lang.reflect.Field userField = LoginFrame.class.getDeclaredField("jTextField1");
            userField.setAccessible(true);
            JTextField usernameField = (JTextField) userField.get(view);
            
            java.lang.reflect.Field passField = LoginFrame.class.getDeclaredField("txtPassword");
            passField.setAccessible(true);
            JPasswordField passwordField = (JPasswordField) passField.get(view);
            
            java.lang.reflect.Field checkField = LoginFrame.class.getDeclaredField("chkSshowPassword");
            checkField.setAccessible(true);
            JCheckBox showPassword = (JCheckBox) checkField.get(view);
            
            // Login action
            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.equals("admin") && password.equals("admin123")) {
                    JOptionPane.showMessageDialog(view, "Login Successful!");
                    view.dispose();
                    
                    // Show dashboard
                    DashboardFrame dashboard = new DashboardFrame();
                    new SimpleDashboardController(dashboard);
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Wrong credentials!");
                }
            });
            
            // Show password
            showPassword.addActionListener(e -> {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('•');
                }
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}