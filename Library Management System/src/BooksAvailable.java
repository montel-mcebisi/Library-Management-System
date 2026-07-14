package src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BooksAvailable {
    
    public BooksAvailable() {
        JFrame booksAvailable = new JFrame("Books Available");
        booksAvailable.setSize(420,420);
        booksAvailable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        booksAvailable.setLayout(new BorderLayout());

        String[] columns = {
        "Book ID",
        "Title",
        "Author",
        "ISBN",
        "Category",
        "Publisher",
        "Year",
        "Quantity",
        "Available"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);

                try {
                    Connection con = DBConnection.getConnection();
                    String sql = "SELECT * FROM books";
                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    while(rs.next()) {
                        model.addRow(new Object[]{
                            rs.getInt("BOOK_ID"),
                            rs.getString("TITLE"),
                            rs.getString("AUTHOR"),
                            rs.getString("ISBN"),
                            rs.getString("CATEGORY"),
                            rs.getString("PUBLISHER"),
                            rs.getInt("YEAR_PUBLISHED"),
                            rs.getInt("QUANTITY"),
                            rs.getInt("AVAILABLE")
                            });
                    }
                    rs.close();
                    pst.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            booksAvailable.dispose();
            new Dashboard();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(fetchButton);
        buttonPanel.add(backButton);


        booksAvailable.add(scrollPane, BorderLayout.CENTER);
        booksAvailable.add(buttonPanel, BorderLayout.SOUTH);
        
        booksAvailable.setVisible(true);

    }
    
}
