package controller;

import model.*;
import view.ListOfMembers;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SimpleListController {
    private ListOfMembers view;
    private DefaultTableModel tableModel;
    
    public SimpleListController(ListOfMembers view) {
        this.view = view;
        setupTable();
        loadMembers();
        setupListeners();
    }
    
    private void setupTable() {
        try {
            // Get table
            java.lang.reflect.Field tableField = ListOfMembers.class.getDeclaredField("jTable1");
            tableField.setAccessible(true);
            JTable table = (JTable) tableField.get(view);
            
            // Get scroll pane (optional)
            java.lang.reflect.Field scrollField = ListOfMembers.class.getDeclaredField("jScrollPane1");
            scrollField.setAccessible(true);
            
            // Create table model
            String[] columns = {"Member ID", "Name", "Mobile", "Email", "Gender"};
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
    
    private void loadMembers() {
        // Clear table
        tableModel.setRowCount(0);
        
        // Get GymManager instance
        GymManager gymManager = GymManager.getInstance();
        
        // Get all members
        List<Member> members = gymManager.getAllMembers();
        
        System.out.println("Loading " + members.size() + " members...");
        
        // Add to table
        for (Member member : members) {
            Object[] row = {
                member.getMemberId(),
                member.getName(),
                member.getMobileNumber(),
                member.getEmail(),
                member.getGender()
            };
            tableModel.addRow(row);
        }
        
        // Show message if no members
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No members found. Add some first!");
        }
    }
    
    private void setupListeners() {
        try {
            // Get exit button
            java.lang.reflect.Field exitField = ListOfMembers.class.getDeclaredField("jButton1");
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
}