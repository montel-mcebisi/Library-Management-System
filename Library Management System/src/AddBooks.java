package src;
import java.awt.*;
import java.sql.*;
import javax.swing.*;


public class AddBooks {
    
    public AddBooks() {
        JFrame addBooks = new JFrame("Add Books");
        addBooks.setSize(420,420);
        addBooks.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addBooks.setLayout(new GridLayout(9,2,10,10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();

        JLabel isbnLabel = new JLabel("ISBN");
        JTextField isbnField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel publisherLabel = new JLabel("Publisher:");
        JTextField publisherField = new JTextField();

        JLabel yearLabel = new JLabel("Year Published:");
        JTextField yearField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String category = categoryField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO books (TITLE, AUTHOR, ISBN, CATEGORY, PUBLISHER, YEAR_PUBLISHED, QUANTITY, AVAILABLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, title);
                pst.setString(2, author);
                pst.setString(3, isbn);
                pst.setString(4, category);
                pst.setString(5, publisher);
                pst.setInt(6, year);
                pst.setInt(7, quantity);
                pst.setInt(8, quantity);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(addBooks, "Books added successfully!");

                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
                categoryField.setText("");
                publisherField.setText("");
                yearField.setText("");
                quantityField.setText("");
                titleField.requestFocus();

                pst.close();
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard();
            addBooks.dispose();
        });

        addBooks.add(titleLabel);
        addBooks.add(titleField);

        addBooks.add(authorLabel);
        addBooks.add(authorField);

        addBooks.add(isbnLabel);
        addBooks.add(isbnField);

        addBooks.add(categoryLabel);
        addBooks.add(categoryField);

        addBooks.add(publisherLabel);
        addBooks.add(publisherField);

        addBooks.add(yearLabel);
        addBooks.add(yearField);

        addBooks.add(quantityLabel);
        addBooks.add(quantityField);

        addBooks.add(addButton);
        addBooks.add(backButton);

        
        addBooks.setVisible(true);

    }
}
