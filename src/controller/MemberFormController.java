package controller;

import model.GymManager;
import model.Member;
import view.MemberFormFrame;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MemberFormController {
    private MemberFormFrame memberForm;
    private GymManager gymManager;
    private DashboardController dashboardController; // To refresh dashboard
    
    public MemberFormController(MemberFormFrame memberForm, GymManager gymManager,
                               DashboardController dashboardController) {
        this.memberForm = memberForm;
        this.gymManager = gymManager;
        this.dashboardController = dashboardController;
        
        setupFormActions();
        initializeForm();
    }
    
    private void setupFormActions() {
        // Save button action
        memberForm.getBtnSave().addActionListener(e -> saveMember());
        
        // Reset button action
        memberForm.getBtnReset().addActionListener(e -> resetForm());
        
        // Auto-generate Member ID on focus
        memberForm.getTxtMemberId().addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                autoGenerateMemberId();
            }
        });
    }
    
    private void initializeForm() {
        // Auto-generate initial member ID
        autoGenerateMemberId();
        
        // Set default gym time (05:00 AM to 11:00 AM)
        memberForm.getTxtGymTime().setText("05:00 AM to 11:00 AM");
        
        // Set default gender to Male
        memberForm.getCboGender().setSelectedItem("Male");
        
        // Set default amount
        memberForm.getTxtAmount().setText("5000");
    }
    
    private void autoGenerateMemberId() {
        String currentId = memberForm.getTxtMemberId().getText().trim();
        if (currentId.isEmpty()) {
            String newId = gymManager.generateMemberId();
            memberForm.getTxtMemberId().setText(newId);
        }
    }
    
    private void saveMember() {
        try {
            // Get values from form
            String memberId = memberForm.getTxtMemberId().getText().trim();
            String name = memberForm.getTxtName().getText().trim();
            String motherName = memberForm.getTxtMotherName().getText().trim();
            String fatherName = memberForm.getTxtFatherName().getText().trim();
            String mobileNumber = memberForm.getTxtMobile().getText().trim();
            String email = memberForm.getTxtEmail().getText().trim();
            String citizenshipNumber = memberForm.getTxtCitizenship().getText().trim();
            String gender = (String) memberForm.getCboGender().getSelectedItem();
            String ageStr = memberForm.getTxtAge().getText().trim();
            String amountStr = memberForm.getTxtAmount().getText().trim();
            String gymTime = memberForm.getTxtGymTime().getText().trim();
            
            // === VALIDATION ===
            
            // 1. Required fields
            if (name.isEmpty() || motherName.isEmpty() || fatherName.isEmpty() ||
                mobileNumber.isEmpty() || email.isEmpty() || citizenshipNumber.isEmpty() ||
                ageStr.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(memberForm,
                    "Please fill all required fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 2. Age validation
            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age < 16 || age > 100) {
                    JOptionPane.showMessageDialog(memberForm,
                        "Age must be between 16 and 100 years!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(memberForm,
                    "Please enter a valid number for age!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 3. Amount validation
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(memberForm,
                        "Amount must be positive!",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(memberForm,
                    "Please enter a valid amount!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 4. Email validation
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(memberForm,
                    "Please enter a valid email address!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 5. Mobile validation (10 digits)
            if (!mobileNumber.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(memberForm,
                    "Mobile number must be 10 digits!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 6. Parse gym time (format: "HH:MM AM/PM to HH:MM AM/PM")
            LocalTime startTime = null;
            LocalTime endTime = null;
            try {
                String[] timeParts = gymTime.split(" to ");
                if (timeParts.length == 2) {
                    startTime = parseTime(timeParts[0].trim());
                    endTime = parseTime(timeParts[1].trim());
                    
                    if (startTime == null || endTime == null) {
                        throw new Exception("Invalid time format");
                    }
                } else {
                    throw new Exception("Invalid gym time format");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(memberForm,
                    "Please enter gym time in format: '05:00 AM to 11:00 AM'",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // === CREATE MEMBER OBJECT ===
            Member newMember = new Member(
                memberId, name, motherName, fatherName,
                mobileNumber, email, citizenshipNumber,
                gender, age, amount,
                startTime, endTime
            );
            
            // === SAVE TO DATABASE ===
            boolean success = gymManager.addMember(newMember);
            
            if (success) {
                // Show success message
                String message = String.format(
                    "Member Added Successfully!\n\n" +
                    "Member ID: %s\n" +
                    "Name: %s\n" +
                    "Gender: %s\n" +
                    "Age: %d\n" +
                    "Monthly Fee: Rs. %.2f\n" +
                    "Gym Time: %s\n" +
                    "Join Date: %s",
                    memberId, name, gender, age, amount,
                    gymTime, newMember.getJoinDate()
                );
                
                JOptionPane.showMessageDialog(memberForm,
                    message,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh dashboard if needed
                if (dashboardController != null) {
                    dashboardController.refreshDashboard();
                }
                
                // Reset form for next entry
                resetForm();
                
                // Auto-generate new member ID
                autoGenerateMemberId();
                
            } else {
                // Check what caused failure
                Member existingByEmail = gymManager.getMemberByEmail(email);
                if (existingByEmail != null) {
                    JOptionPane.showMessageDialog(memberForm,
                        "Email already registered for member: " + existingByEmail.getName(),
                        "Duplicate Email",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(memberForm,
                        "Member ID already exists!",
                        "Duplicate ID",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(memberForm,
                "Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Helper method to parse time (e.g., "05:00 AM")
    private LocalTime parseTime(String timeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return LocalTime.parse(timeStr.toUpperCase(), formatter);
        } catch (DateTimeParseException e) {
            // Try 24-hour format
            try {
                return LocalTime.parse(timeStr);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }
    
    private void resetForm() {
        // Clear all text fields
        memberForm.getTxtName().setText("");
        memberForm.getTxtMotherName().setText("");
        memberForm.getTxtFatherName().setText("");
        memberForm.getTxtMobile().setText("");
        memberForm.getTxtEmail().setText("");
        memberForm.getTxtCitizenship().setText("");
        memberForm.getTxtAge().setText("");
        memberForm.getTxtAmount().setText("5000");
        memberForm.getTxtGymTime().setText("05:00 AM to 11:00 AM");
        
        // Reset combobox to default
        memberForm.getCboGender().setSelectedIndex(0);
        
        // Focus on name field
        memberForm.getTxtName().requestFocus();
    }
}