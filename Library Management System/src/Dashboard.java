package src;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Dashboard {
    
    public Dashboard() {
        JFrame dashboard = new JFrame("Dashboard");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setResizable(false);
        dashboard.setSize(420,420);
        dashboard.setLayout(new GridLayout(4,3,50,50));

        JButton b1 = new JButton("Books Available");
        dashboard.add(b1);
        JButton b2 = new JButton("Staff Details");
        dashboard.add(b2);
        JButton b3 = new JButton("Add Books");
        dashboard.add(b3);
        JButton b4 = new JButton("Add Staff");
        dashboard.add(b4);
        JButton b5 = new JButton("Remove Books");
        dashboard.add(b5);
        JButton b6 = new JButton("Remove Staff");
        dashboard.add(b6);

        JButton b7 = new JButton("Edit Admin");
        dashboard.add(b7);
        
        
        dashboard.setVisible(true);
    }
}
