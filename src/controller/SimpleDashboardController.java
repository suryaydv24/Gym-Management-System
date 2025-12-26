package controller;

import view.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleDashboardController {
    private DashboardFrame view;
    
    public SimpleDashboardController(DashboardFrame view) {
        this.view = view;
        setupListeners();
    }
    
    private void setupListeners() {
        try {
            // Get menu fields
            java.lang.reflect.Field menu1 = DashboardFrame.class.getDeclaredField("jMenu1");
            menu1.setAccessible(true);
            JMenu newMemberMenu = (JMenu) menu1.get(view);
            
            java.lang.reflect.Field menu2 = DashboardFrame.class.getDeclaredField("jMenu2");
            menu2.setAccessible(true);
            JMenu updateMenu = (JMenu) menu2.get(view);
            
            java.lang.reflect.Field menu3 = DashboardFrame.class.getDeclaredField("jMenu3");
            menu3.setAccessible(true);
            JMenu listMenu = (JMenu) menu3.get(view);
            
            java.lang.reflect.Field menu4 = DashboardFrame.class.getDeclaredField("jMenu4");
            menu4.setAccessible(true);
            JMenu paymentMenu = (JMenu) menu4.get(view);
            
            java.lang.reflect.Field menu5 = DashboardFrame.class.getDeclaredField("jMenu5");
            menu5.setAccessible(true);
            JMenu logoutMenu = (JMenu) menu5.get(view);
            
            java.lang.reflect.Field menu6 = DashboardFrame.class.getDeclaredField("jMenu6");
            menu6.setAccessible(true);
            JMenu exitMenu = (JMenu) menu6.get(view);
            
            // New Member
            newMemberMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    NewMember frame = new NewMember();
                    new SimpleMemberController(frame);
                    frame.setVisible(true);
                }
            });
            
            // List Members
            listMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ListOfMembers frame = new ListOfMembers();
                    new SimpleListController(frame);
                    frame.setVisible(true);
                }
            });
            
            // Logout
            logoutMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    view.dispose();
                    LoginFrame login = new LoginFrame();
                    new SimpleLoginController(login);
                    login.setVisible(true);
                }
            });
            
            // Exit
            exitMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}