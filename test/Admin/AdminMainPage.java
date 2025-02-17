import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public AdminMainPage() {
        setTitle("Admin Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLayout(new BorderLayout());

        // Panel navigasi
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton stokBarangButton = new JButton("Stok Barang");
        JButton barangMasukButton = new JButton("Barang Masuk");
        JButton barangKeluarButton = new JButton("Barang Keluar");
        JButton logoutButton = new JButton("Logout");

        stokBarangButton.addActionListener(new NavButtonListener("Stok Barang"));
        barangMasukButton.addActionListener(new NavButtonListener("Barang Masuk"));
        barangKeluarButton.addActionListener(new NavButtonListener("Barang Keluar"));
        logoutButton.addActionListener(e -> {
            // Notify the waiting login frame
            synchronized (AdminLogin.class) {
                AdminLogin.class.notify();
            }
            dispose();
        });

        navPanel.add(stokBarangButton);
        navPanel.add(barangMasukButton);
        navPanel.add(barangKeluarButton);
        navPanel.add(Box.createVerticalGlue());
        navPanel.add(logoutButton);

        // Panel konten
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(new StokBarangPanel(), "Stok Barang");
        cardPanel.add(new BarangMasukPanel(), "Barang Masuk");
        cardPanel.add(new BarangKeluarPanel(), "Barang Keluar");

        add(navPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class NavButtonListener implements ActionListener {
        private String cardName;

        public NavButtonListener(String cardName) {
            this.cardName = cardName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, cardName);
        }
    }
}