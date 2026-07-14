package src;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
public class CreateProfile {
    public CreateProfile() {

        JFrame frame = new JFrame("Create Profile");
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5,2,10,10));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(e -> {
            try{

                Connection con = DBConnection.getConnection();

                String sql = "INSERT INTO admin(USER_ID, NAME, PASSWORD, CONTACT) VALUES(?, ?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, userField.getText());
                pst.setString(2, nameField.getText());
                pst.setString(3, new String(passwordField.getPassword()));
                pst.setString(4, contactField.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(frame,
                        "Profile created successfully!");

                userField.setText("");
                nameField.setText("");
                passwordField.setText("");
                contactField.setText("");

                pst.close();
                con.close();

            }catch(Exception ex){

                ex.printStackTrace();

            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LoginPage();
            frame.dispose();
        });

        frame.add(userLabel);
        frame.add(userField);

        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(passwordLabel);
        frame.add(passwordField);

        frame.add(contactLabel);
        frame.add(contactField);

        frame.add(createButton);
        frame.add(backButton);

        frame.setVisible(true);

    }
}
