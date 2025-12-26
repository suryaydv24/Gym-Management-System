import view.*;
import controller.*;

public class Mainmethod {
    public static void main(String[] args) {
        System.out.println("=== GYM MANAGEMENT SYSTEM ===");
        
        // Show what's happening
        java.io.File file = new java.io.File("members.dat");
        if (file.exists()) {
            System.out.println("Found members.dat file");
        } else {
            System.out.println("No members.dat file - will create new");
        }
        
        // Start with login
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            SimpleLoginController controller = new SimpleLoginController(loginFrame);
            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);
        });
    }
}