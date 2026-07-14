package src;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class BorrowBook {
    
    public BorrowBook() {
        JFrame borrowBook = new JFrame("Borrow Book");
        borrowBook.setSize(420,420);
        borrowBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowBook.setLayout(new BorderLayout());

        JPanel overallPanel = new JPanel(new GridBagLayout());
        JPanel borrowPanel = new JPanel();
        overallPanel.add(borrowPanel);
        borrowPanel.setLayout(new GridLayout(2,2,10,10));

        borrowBook.add(overallPanel, BorderLayout.CENTER);

        JLabel idLabel = new JLabel("Book ID:");
        JTextField idField = new JTextField();

        JLabel memberLabel = new JLabel("Member Name:");
        JTextField memberField = new JTextField();

        borrowPanel.add(idLabel);
        borrowPanel.add(idField);
        
        borrowPanel.add(memberLabel);
        borrowPanel.add(memberField);

        JPanel buttonPanel = new JPanel();

        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(e -> {
            try {
                int bookID = Integer.parseInt(idField.getText());
                String member = memberField.getText();
                Connection con = DBConnection.getConnection();
                String check = "SELECT AVAILABLE FROM books WHERE BOOK_ID=?";
                PreparedStatement checkStmt = con.prepareStatement(check);
                checkStmt.setInt(1, bookID);
                ResultSet rs = checkStmt.executeQuery();

                if(rs.next()) {
                    int available = rs.getInt("AVAILABLE");

                    if(available > 0) {
                        String insert = "INSERT INTO borrowed_books (BOOK_ID, MEMBER_NAME, BORROW_DATE) VALUES(?,?,?)";

                        PreparedStatement pst = con.prepareStatement(insert);

                        pst.setInt(1, bookID);
                        pst.setString(2, member);
                        pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

                        pst.executeUpdate();

                        String update = "UPDATE books SET AVAILABLE = AVAILABLE - 1 WHERE BOOK_ID=?";

                        PreparedStatement updateStmt = con.prepareStatement(update);

                        updateStmt.setInt(1, bookID);
                        updateStmt.executeUpdate();

                        JOptionPane.showMessageDialog(borrowBook, "Book borrowed successfully!");

                        updateStmt.close();
                        pst.close();
                    } else {
                        JOptionPane.showMessageDialog(borrowBook, "Book is currently unavailable");
                    }
                } else {
                    JOptionPane.showMessageDialog(borrowBook, "Book ID not found.");
                }
                rs.close();
                checkStmt.close();
                con.close();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard();
            borrowBook.dispose();
        });

        buttonPanel.add(borrowButton);
        buttonPanel.add(backButton);

        borrowBook.add(buttonPanel, BorderLayout.SOUTH);

        borrowBook.setVisible(true);
    }
}
