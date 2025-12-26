package controller;

import model.GymManager;
import model.Member;
import view.ListOfMembers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListOfMembersController {
    private ListOfMembers view;
    private GymManager gymManager;
    private DefaultTableModel tableModel;
    private JTable table;

    public ListOfMembersController(ListOfMembers view) {
        this.view = view;
        this.gymManager = GymManager.getInstance(); // Use singleton
        initializeTable();
        attachListeners();
        loadMembersData();
    }

    private void initializeTable() {
        try {
            // Get the table and table model using reflection
            Class<?> clazz = view.getClass();
            
            // Get table
            java.lang.reflect.Field tableField = clazz.getDeclaredField("jTable1");
            tableField.setAccessible(true);
            table = (JTable) tableField.get(view);
            
            // Get scroll pane
            java.lang.reflect.Field scrollField = clazz.getDeclaredField("jScrollPane1");
            scrollField.setAccessible(true);
            
            // Create table model with your columns
            String[] columns = {"Member ID", "Name", "Mobile Number", "Email", "Gender", 
                               "Father Name", "Mother Name", "Gym Time", "Citizenship", "Age", "Amount"};
            tableModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(tableModel);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachListeners() {
        try {
            Class<?> clazz = view.getClass();
            
            // Get Exit Button
            java.lang.reflect.Field exitField = clazz.getDeclaredField("jButton1");
            exitField.setAccessible(true);
            JButton exitButton = (JButton) exitField.get(view);
            
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

    private void loadMembersData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Get all members from shared GymManager
        List<Member> members = gymManager.getAllMembers();
        
        // Add each member to the table
        for (Member member : members) {
            Object[] rowData = {
                member.getMemberId(),
                member.getName(),
                member.getMobileNumber(),
                member.getEmail(),
                member.getGender(),
                member.getFatherName(),
                member.getMotherName(),
                member.getGymTime(),
                member.getCitizenshipNumber(),
                member.getAge(),
                member.getAmount()
            };
            tableModel.addRow(rowData);
        }
        
        // Show message if no members
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No members found. Add some members first!");
        }
    }
    
    // Call this method to refresh the table
    public void refreshTable() {
        loadMembersData();
    }
}