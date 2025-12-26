package controller;

import model.GymManager;
import model.Member;
import view.NewMember;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberFormController {
    private NewMember view;
    private GymManager gymManager;

    public MemberFormController(NewMember view) {
        this.view = view;
        this.gymManager = GymManager.getInstance(); // Use singleton
        attachListeners();
    }

    private void attachListeners() {
        try {
            Class<?> clazz = view.getClass();
            
            // Get Save Button
            java.lang.reflect.Field saveField = clazz.getDeclaredField("jButton2");
            saveField.setAccessible(true);
            JButton saveButton = (JButton) saveField.get(view);
            
            // Get Reset Button
            java.lang.reflect.Field resetField = clazz.getDeclaredField("jButton3");
            resetField.setAccessible(true);
            JButton resetButton = (JButton) resetField.get(view);
            
            // Get Exit Button
            java.lang.reflect.Field exitField = clazz.getDeclaredField("jButton1");
            exitField.setAccessible(true);
            JButton exitButton = (JButton) exitField.get(view);
            
            // Get text fields for saving data
            java.lang.reflect.Field nameField = clazz.getDeclaredField("jTextField1");
            nameField.setAccessible(true);
            JTextField nameText = (JTextField) nameField.get(view);
            
            java.lang.reflect.Field mobileField = clazz.getDeclaredField("jTextField2");
            mobileField.setAccessible(true);
            JTextField mobileText = (JTextField) mobileField.get(view);
            
            java.lang.reflect.Field emailField = clazz.getDeclaredField("jTextField3");
            emailField.setAccessible(true);
            JTextField emailText = (JTextField) emailField.get(view);
            
            // Add listeners
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveMember();
                }
            });
            
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(view, "Form Reset!");
                }
            });
            
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
    
    private void saveMember() {
        try {
            // Get all field values using reflection
            Class<?> clazz = view.getClass();
            
            // Name
            java.lang.reflect.Field nameField = clazz.getDeclaredField("jTextField1");
            nameField.setAccessible(true);
            JTextField nameText = (JTextField) nameField.get(view);
            
            // Mobile
            java.lang.reflect.Field mobileField = clazz.getDeclaredField("jTextField2");
            mobileField.setAccessible(true);
            JTextField mobileText = (JTextField) mobileField.get(view);
            
            // Email
            java.lang.reflect.Field emailField = clazz.getDeclaredField("jTextField3");
            emailField.setAccessible(true);
            JTextField emailText = (JTextField) emailField.get(view);
            
            // Gender Combo
            java.lang.reflect.Field genderField = clazz.getDeclaredField("jComboBox1");
            genderField.setAccessible(true);
            JComboBox<String> genderCombo = (JComboBox<String>) genderField.get(view);
            
            // Father Name
            java.lang.reflect.Field fatherField = clazz.getDeclaredField("jTextField4");
            fatherField.setAccessible(true);
            JTextField fatherText = (JTextField) fatherField.get(view);
            
            // Mother Name
            java.lang.reflect.Field motherField = clazz.getDeclaredField("jTextField5");
            motherField.setAccessible(true);
            JTextField motherText = (JTextField) motherField.get(view);
            
            // Gym Time Combo
            java.lang.reflect.Field timeField = clazz.getDeclaredField("jComboBox2");
            timeField.setAccessible(true);
            JComboBox<String> timeCombo = (JComboBox<String>) timeField.get(view);
            
            // Citizenship
            java.lang.reflect.Field citizenshipField = clazz.getDeclaredField("jTextField6");
            citizenshipField.setAccessible(true);
            JTextField citizenshipText = (JTextField) citizenshipField.get(view);
            
            // Age
            java.lang.reflect.Field ageField = clazz.getDeclaredField("jTextField7");
            ageField.setAccessible(true);
            JTextField ageText = (JTextField) ageField.get(view);
            
            // Amount
            java.lang.reflect.Field amountField = clazz.getDeclaredField("jTextField8");
            amountField.setAccessible(true);
            JTextField amountText = (JTextField) amountField.get(view);
            
            // Validate required fields
            if (nameText.getText().trim().isEmpty() || mobileText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Name and Mobile are required!");
                return;
            }
            
            // Create new member
            Member member = new Member();
            member.setName(nameText.getText().trim());
            member.setMobileNumber(mobileText.getText().trim());
            member.setEmail(emailText.getText().trim());
            member.setGender(genderCombo.getSelectedItem().toString());
            member.setFatherName(fatherText.getText().trim());
            member.setMotherName(motherText.getText().trim());
            member.setGymTime(timeCombo.getSelectedItem().toString());
            member.setCitizenshipNumber(citizenshipText.getText().trim());
            
            // Parse age
            try {
                int age = Integer.parseInt(ageText.getText().trim());
                member.setAge(age);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid age!");
                return;
            }
            
            // Parse amount
            try {
                double amount = Double.parseDouble(amountText.getText().trim());
                member.setAmount(amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid amount!");
                return;
            }
            
            // Save to GymManager
            if (gymManager.addMember(member)) {
                JOptionPane.showMessageDialog(view, 
                    "Member saved successfully!\nMember ID: " + member.getMemberId());
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to save member!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error saving member: " + e.getMessage());
        }
    }
}