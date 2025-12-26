/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
 // Store user credentials (for now, hardcoded - later use database)
    private final List<User> users;
    
    public LoginManager() {
        users = new ArrayList<>();
        // Add some default users (for testing)
        initializeDefaultUsers();
    }
    
    private void initializeDefaultUsers() {
        // Admin user
        users.add(new User("admin", "admin123", "admin"));
        // Trainer user  
        users.add(new User("trainer", "trainer123", "trainer"));
        // Member user
        users.add(new User("member", "member123", "member"));
    }
    
    // Validate login credentials
    public boolean validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    // Get user role
    public String getUserRole(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getRole();
            }
        }
        return null;
    }
    
    // Add new user (for registration feature later)
    public boolean addUser(String username, String password, String role) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Username taken
            }
        }
        users.add(new User(username, password, role));
        return true;
    }   
}
