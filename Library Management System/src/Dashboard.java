package src;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.*;

public class Dashboard {
    
    public Dashboard() {
        JFrame dashboard = new JFrame("Dashboard");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setResizable(false);
        dashboard.setSize(420,420);
        dashboard.setLayout(new BorderLayout());

        JPanel buttonDashboard = new JPanel();
        buttonDashboard.setLayout(new GridLayout(3,3,50,50));

        JButton b1 = new JButton("Books Available");
        b1.addActionListener(e -> {
            new BooksAvailable();
            dashboard.dispose();
        });
        buttonDashboard.add(b1);
        JButton b2 = new JButton("Borrow Book");
        b2.addActionListener(e -> {
            new BorrowBook();
            dashboard.dispose();
        });
        buttonDashboard.add(b2);
        JButton b3 = new JButton("Add Books");
        b3.addActionListener(e -> {
            new AddBooks();
            dashboard.dispose();
        });
        buttonDashboard.add(b3);
        JButton b4 = new JButton("Return Book");
        b4.addActionListener(e -> {
            new ReturnBook();
            dashboard.dispose();
        });
        buttonDashboard.add(b4);
        JButton b5 = new JButton("Remove Books");
        b5.addActionListener(e -> {
            
            dashboard.dispose();
            new RemoveBook();
        });
        buttonDashboard.add(b5);
        JButton b6 = new JButton("History");
        b6.addActionListener(e -> {
            new History();
            dashboard.dispose();
        });
        buttonDashboard.add(b6);

        dashboard.add(buttonDashboard, BorderLayout.CENTER);

        JPanel logout = new JPanel();
        JButton b7 = new JButton("Logout");
        b7.addActionListener(e -> {
            new LoginPage();
            dashboard.dispose();
        });
        logout.add(b7);

        dashboard.add(logout, BorderLayout.SOUTH);
        
        
        dashboard.setVisible(true);
    }
}
