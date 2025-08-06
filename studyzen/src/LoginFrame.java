package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("StudyZen - Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to StudyZen", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 102, 204));
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 153, 76));
        loginBtn.setForeground(Color.WHITE);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            logLogin(username);
            dispose();
            new DashboardFrame(username);
        });

        panel.add(new JLabel());
        panel.add(loginBtn);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void logLogin(String user) {
        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println(user + " logged in at " + LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
