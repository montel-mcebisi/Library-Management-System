package src;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import java.awt.*;

public class LoginPage {

    public LoginPage() {
        JFrame frame = new JFrame("LOGIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setLayout(new BorderLayout());
        
        JPanel overallPanel = new JPanel(new GridBagLayout());
        JPanel credentials = new JPanel();
        credentials.setLayout(new GridLayout(2, 2, 10, 10));
        overallPanel.add(credentials);

        frame.add(overallPanel, BorderLayout.CENTER);

        JLabel username = new JLabel("Username: ");
        JTextField usernameField = new JTextField(15);
        usernameField.setSize(50, 50);

        JLabel password = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);

        credentials.add(username);
        credentials.add(usernameField);

        
        credentials.add(password);
        credentials.add(passwordField);

        JButton button = new JButton("LOGIN");
        button.addActionListener(e -> {
            String userID = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            try {
                
                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM admin WHERE USER_ID = ? AND PASSWORD = ?";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, userID);
                pst.setString(2, enteredPassword);

                ResultSet rs = pst.executeQuery();

                if(rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful");

                    frame.dispose(); 
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
                rs.close();
                pst.close();
                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });     

        JButton signup = new JButton("Create Profile");
        signup.addActionListener(e -> {
            new CreateProfile();
            frame.dispose();
            });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        buttonPanel.add(signup);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}