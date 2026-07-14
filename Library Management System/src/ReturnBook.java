package src;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

public class ReturnBook {
    
    public ReturnBook() {
        JFrame returnBook = new JFrame("Return Book");
        returnBook.setSize(420,420);
        returnBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        returnBook.setLayout(new BorderLayout());


        JPanel overallPanel = new JPanel(new GridBagLayout());

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new GridLayout(1,2,10,10));

        overallPanel.add(returnPanel);


        JLabel idLabel = new JLabel("Book ID:");
        JTextField idField = new JTextField();

        returnPanel.add(idLabel);
        returnPanel.add(idField);

        returnBook.add(overallPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            try {
                int bookID = Integer.parseInt(idField.getText());
                Connection con = DBConnection.getConnection();

                String sql = "UPDATE borrowed_books SET RETURN_DATE=?" + "WHERE BOOK_ID=? AND RETURN_DATE IS NULL";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                pst.setInt(2, bookID);

                int rows = pst.executeUpdate();

                if(rows > 0) {
                    String update = "UPDATE books SET AVAILABLE = AVAILABLE + 1 WHERE BOOK_ID=?";

                    PreparedStatement updateStmt = con.prepareStatement(update);

                    updateStmt.setInt(1, bookID);

                    updateStmt.executeUpdate();

                    JOptionPane.showMessageDialog(returnBook, "Book returned successfully!");
                    updateStmt.close();
                } else {
                    JOptionPane.showMessageDialog(returnBook, "No borrowed record found!");
                }
                pst.close();
                con.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard();
            returnBook.dispose();
        });


        buttonPanel.add(returnButton);
        buttonPanel.add(backButton);

        returnBook.add(buttonPanel, BorderLayout.SOUTH);

        returnBook.setVisible(true);
    }


}
