/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.GymManager;
import model.Member;
import view.DashboardFrame;
import view.MemberFormFrame; // You'll create this next

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DashboardController {
    private DashboardFrame dashboard;
    private GymManager gymManager;
    
    public DashboardController(DashboardFrame dashboard) {
        this.dashboard = dashboard;
        this.gymManager = new GymManager(); // Create model
        
        setupButtonActions();
        loadDashboardStatistics();
    }
    
    private void setupButtonActions() {
        // 1. New Member Button
        dashboard.getBtnNewMember().addActionListener(e -> openNewMemberForm());
        
        // 2. Update & Delete Member Button
        dashboard.getBtnUpdateDeleteMember().addActionListener(e -> openMemberManagement());
        
        // 3. List of Members Button
        dashboard.getBtnListMembers().addActionListener(e -> showMemberList());
        
        // 4. Payment Button
        dashboard.getBtnPayment().addActionListener(e -> openPaymentSystem());
        
        // 5. Logout Button
        dashboard.getBtnLogout().addActionListener(e -> logout());
        
        // 6. Exit Button
        dashboard.getBtnExit().addActionListener(e -> exitApplication());
    }
    
    private void openNewMemberForm() {
        System.out.println("Opening New Member Form...");
        
        // Create and show member form
        MemberFormFrame memberForm = new MemberFormFrame();
        memberForm.setVisible(true);
        memberForm.setLocationRelativeTo(dashboard);
        
        // Create controller for member form
        new MemberFormController(memberForm, gymManager, this);
    }
    
    private void openMemberManagement() {
        System.out.println("Opening Member Management...");
        
        // Show dialog with options
        String[] options = {"Update Member", "Delete Member", "Cancel"};
        int choice = JOptionPane.showOptionDialog(dashboard,
            "What would you like to do?",
            "Member Management",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 0) {
            // Update Member
            String memberId = JOptionPane.showInputDialog(dashboard,
                "Enter Member ID to update:",
                "Update Member",
                JOptionPane.QUESTION_MESSAGE);
            
            if (memberId != null && !memberId.trim().isEmpty()) {
                Member member = gymManager.getMemberById(memberId);
                if (member != null) {
                    openUpdateMemberForm(member);
                } else {
                    JOptionPane.showMessageDialog(dashboard,
                        "Member not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } else if (choice == 1) {
            // Delete Member
            String memberId = JOptionPane.showInputDialog(dashboard,
                "Enter Member ID to delete:",
                "Delete Member",
                JOptionPane.WARNING_MESSAGE);
            
            if (memberId != null && !memberId.trim().isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(dashboard,
                    "Are you sure you want to delete member " + memberId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = gymManager.deleteMember(memberId);
                    if (deleted) {
                        JOptionPane.showMessageDialog(dashboard,
                            "Member deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                        loadDashboardStatistics();
                    } else {
                        JOptionPane.showMessageDialog(dashboard,
                            "Member not found!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    private void openUpdateMemberForm(Member member) {
        System.out.println("Opening Update Form for: " + member.getName());
        // TODO: Create update form
        JOptionPane.showMessageDialog(dashboard,
            "Update form for " + member.getName() + " (ID: " + member.getMemberId() + ")",
            "Update Member",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showMemberList() {
        System.out.println("Showing Member List...");
        
        List<Member> members = gymManager.getAllMembers();
        
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(dashboard,
                "No members found!",
                "Member List",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Create table data
        String[] columns = {"ID", "Name", "Email", "Phone", "Type", "Status"};
        Object[][] data = new Object[members.size()][6];
        
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            data[i][0] = m.getMemberId();
            data[i][1] = m.getName();
            data[i][2] = m.getEmail();
            data[i][3] = m.getPhone();
            data[i][4] = m.getMembershipType();
            data[i][5] = m.getStatus();
        }
        
        // Create and show table
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JOptionPane.showMessageDialog(dashboard,
            scrollPane,
            "Member List (Total: " + members.size() + ")",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void openPaymentSystem() {
        System.out.println("Opening Payment System...");
        
        String memberId = JOptionPane.showInputDialog(dashboard,
            "Enter Member ID for payment:",
            "Payment",
            JOptionPane.QUESTION_MESSAGE);
        
        if (memberId != null && !memberId.trim().isEmpty()) {
            Member member = gymManager.getMemberById(memberId);
            if (member != null) {
                // Show payment form
                String[] paymentMethods = {"Cash", "Card", "Online"};
                String paymentMethod = (String) JOptionPane.showInputDialog(dashboard,
                    "Select payment method for " + member.getName() + ":",
                    "Payment Method",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    paymentMethods,
                    paymentMethods[0]);
                
                if (paymentMethod != null) {
                    String amountStr = JOptionPane.showInputDialog(dashboard,
                        "Enter payment amount:",
                        "Payment Amount",
                        JOptionPane.QUESTION_MESSAGE);
                    
                    if (amountStr != null) {
                        try {
                            double amount = Double.parseDouble(amountStr);
                            if (amount > 0) {
                                JOptionPane.showMessageDialog(dashboard,
                                    "Payment of $" + amount + " received from " + member.getName() + "\n" +
                                    "Payment Method: " + paymentMethod,
                                    "Payment Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(dashboard,
                                "Invalid amount entered!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(dashboard,
                    "Member not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(dashboard,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dashboard.dispose(); // Close dashboard
            
            // TODO: You can reopen login screen here
            System.out.println("User logged out");
            
            // For now, just exit
            System.exit(0);
        }
    }
    
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(dashboard,
            "Are you sure you want to exit the application?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // Load statistics on dashboard
    public void loadDashboardStatistics() {
        int totalMembers = gymManager.getTotalMembers();
        int activeMembers = gymManager.getActiveMembers();
        double avgBMI = gymManager.getAverageBMI();
        
        System.out.println("Dashboard Stats:");
        System.out.println("- Total Members: " + totalMembers);
        System.out.println("- Active Members: " + activeMembers);
        System.out.println("- Average BMI: " + String.format("%.2f", avgBMI));
        
        // You can update labels on your dashboard if you have them
        // dashboard.setStats(totalMembers, activeMembers, avgBMI);
    }
    
    // Method to refresh dashboard after member operations
    public void refreshDashboard() {
        loadDashboardStatistics();
    }
    
}
