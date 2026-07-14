package src;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class History {
    JTable table;
    DefaultTableModel model;

    public History() {
        JFrame history = new JFrame();
        history.setSize(420,420);
        history.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        history.setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{
                        "Borrow ID",
                        "Book ID",
                        "Member",
                        "Borrow Date",
                        "Return Date",
                        "Status"
                }, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton fetchButton = new JButton("Fetch");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(fetchButton);
        buttonPanel.add(backButton);

        history.add(scrollPane, BorderLayout.CENTER);
        history.add(buttonPanel, BorderLayout.SOUTH);

        fetchButton.addActionListener(e -> {
            model.setRowCount(0);

            try {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM borrowed_books";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while(rs.next()) {
                    String status;

                    if(rs.getDate("RETURN_DATE") == null) {
                        status = "Borrowed";
                    } else {
                        status = "Returned";
                    }

                    model.addRow(new Object[]{
                        rs.getInt("BORROW_ID"),
                    rs.getInt("BOOK_ID"),
                    rs.getString("MEMBER_NAME"),
                    rs.getDate("BORROW_DATE"),
                    rs.getDate("RETURN_DATE"),
                    status
                    });
                    
                }
                rs.close();
                pst.close();
                con.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        backButton.addActionListener(e -> {
            new Dashboard();
            history.dispose();
        });

        history.setVisible(true);
    }
}
