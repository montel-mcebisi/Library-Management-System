package src;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage {

    public LoginPage() {
        JFrame frame = new JFrame("LOGIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420,420);
        frame.setLayout(new GridLayout(3, 2, 10, 10));
        


        JLabel username = new JLabel("Username: ");
        JTextField usernameField = new JTextField(15);
        usernameField.setSize(50, 50);

        JLabel password = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);

        frame.add(username);
        frame.add(usernameField);

        
        frame.add(password);
        frame.add(passwordField);

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
        frame.add(button);

        frame.setVisible(true);

    }
}