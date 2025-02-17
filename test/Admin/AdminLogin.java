import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton showPasswordButton;

    public AdminLogin() {
        setTitle("Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        showPasswordButton = new JButton("Show");

        showPasswordButton.addActionListener(e -> {
            if (showPasswordButton.getText().equals("Show")) {
                passwordField.setEchoChar((char) 0);
                showPasswordButton.setText("Hide");
            } else {
                passwordField.setEchoChar('*');
                showPasswordButton.setText("Show");
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(new JLabel()); // empty cell
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(showPasswordButton);
        panel.add(new JLabel()); // empty cell
        panel.add(new JLabel()); // empty cell
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if ("saya".equals(username) && "admin1234".equals(password)) {
                new AdminMainPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(AdminLogin.this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            AdminLogin login = new AdminLogin();
            login.setVisible(true);
            synchronized (login) {
                try {
                    login.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}