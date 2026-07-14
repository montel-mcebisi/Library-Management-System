package src;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class RemoveBook {
    
    public RemoveBook() {
        JFrame removeBook = new JFrame("Remove Book");
        removeBook.setSize(420,420);
        removeBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeBook.setLayout(new BorderLayout());

        JPanel bookPanel = new JPanel(new GridBagLayout());

        /*bookPanel.setLayout(new GridLayout(1,2,10,10));*/

        removeBook.add(bookPanel, BorderLayout.CENTER);

        JLabel idLabel = new JLabel("Book ID: ");
        JTextField idField = new JTextField(15);
        

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                Connection con = DBConnection.getConnection();

                String sql = "DELETE FROM books WHERE BOOK_ID=?";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setInt(1, id);

                int rows = pst.executeUpdate();

                if(rows > 0) {
                    JOptionPane.showMessageDialog(removeBook, "Book removed successfully!");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(removeBook, "Book ID not found!");
                }
                pst.close();
                con.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            
            removeBook.dispose();
            new Dashboard();
        });

        bookPanel.add(idLabel);
        bookPanel.add(idField);

        JPanel buttonPanel = new JPanel();

        removeBook.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);

        removeBook.setVisible(true);
    }
}
