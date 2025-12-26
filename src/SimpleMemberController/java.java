package controller;

import model.*;
import view.NewMember;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleMemberController {
    private NewMember view;
    
    public SimpleMemberController(NewMember view) {
        this.view = view;
        setupListeners();
        generateMemberId();
    }
    
    private void setupListeners() {
        try {
            // Get buttons
            java.lang.reflect.Field saveField = NewMember.class.getDeclaredField("jButton2");
            saveField.setAccessible(true);
            JButton saveButton = (JButton) saveField.get(view);
            
            java.lang.reflect.Field resetField = NewMember.class.getDeclaredField("jButton3");
            resetField.setAccessible(true);
            JButton resetButton = (JButton) resetField.get(view);
            
            java.lang.reflect.Field exitField = NewMember.class.getDeclaredField("jButton1");
            exitField.setAccessible(true);
            JButton exitButton = (JButton) exitField.get(view);
            
            // Save button action
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveMember();
                }
            });
            
            // Reset button
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(view, "Form cleared!");
                }
            });
            
            // Exit button
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.dispose();
                }
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void generateMemberId() {
        try {
            java.lang.reflect.Field idField = NewMember.class.getDeclaredField("memberIdField");
            idField.setAccessible(true);
            JTextField idFieldText = (JTextField) idField.get(view);
            
            // Generate ID
            GymManager gm = GymManager.getInstance();
            int count = gm.getAllMembers().size() + 1;
            String memberId = "M" + String.format("%03d", count);
            idFieldText.setText(memberId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveMember() {
        try {
            // Get GymManager instance
            GymManager gymManager = GymManager.getInstance();
            
            // Get all field values
            java.lang.reflect.Field nameField = NewMember.class.getDeclaredField("jTextField1");
            nameField.setAccessible(true);
            JTextField nameText = (JTextField) nameField.get(view);
            
            java.lang.reflect.Field mobileField = NewMember.class.getDeclaredField("jTextField2");
            mobileField.setAccessible(true);
            JTextField mobileText = (JTextField) mobileField.get(view);
            
            java.lang.reflect.Field idField = NewMember.class.getDeclaredField("memberIdField");
            idField.setAccessible(true);
            JTextField idText = (JTextField) idField.get(view);
            
            // Create member
            Member member = new Member();
            member.setMemberId(idText.getText());
            member.setName(nameText.getText());
            member.setMobileNumber(mobileText.getText());
            
            // Add member
            boolean success = gymManager.addMember(member);
            
            if (success) {
                // Show what was saved
                int totalMembers = gymManager.getAllMembers().size();
                String message = "Member Saved!\n" +
                               "ID: " + member.getMemberId() + "\n" +
                               "Name: " + member.getName() + "\n" +
                               "Total Members Now: " + totalMembers;
                JOptionPane.showMessageDialog(view, message);
                
                // Close window
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to save!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
}