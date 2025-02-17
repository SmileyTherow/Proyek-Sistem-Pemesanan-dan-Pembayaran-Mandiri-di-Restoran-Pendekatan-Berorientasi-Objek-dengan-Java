import javax.swing.*;
import java.awt.*;
import java.util.List;

public class halamanPenutup extends JFrame {
    public halamanPenutup() {
        
        // Mengatur judul jendela dan operasi penutupan
        setTitle("Terima Kasih");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Membuat panel latar belakang dengan gambar
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("images/background(4).jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        
        // Membuat panel untuk teks
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Label "TERIMA KASIH"
        JLabel labelTerimaKasih = new JLabel("TERIMA KASIH", SwingConstants.CENTER);
        labelTerimaKasih.setFont(new Font("Serif", Font.BOLD, 64));
        labelTerimaKasih.setForeground(Color.WHITE);
        labelTerimaKasih.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Label keterangan
        JLabel labelKeteranganTengah = new JLabel("Kami berharap Anda menikmati layanan kami!", SwingConstants.CENTER);
        labelKeteranganTengah.setFont(new Font("SansSerif", Font.ITALIC, 20));
        labelKeteranganTengah.setForeground(Color.LIGHT_GRAY);
        labelKeteranganTengah.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Menambahkan komponen ke panel teks
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(labelTerimaKasih);
        textPanel.add(Box.createVerticalStrut(20));
        textPanel.add(labelKeteranganTengah);
        textPanel.add(Box.createVerticalGlue());
        
        // Membuat tombol "KELUAR"
        JButton buttonTutupAplikasi = new JButton("SELESAI");
        buttonTutupAplikasi.setFont(new Font("Arial", Font.BOLD, 24));
        buttonTutupAplikasi.setPreferredSize(new Dimension(300, 60));
        buttonTutupAplikasi.addActionListener(e -> {
            System.exit(0);
        });
        
        // Membuat panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(buttonTutupAplikasi);
        backgroundPanel.add(textPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);
        
        setVisible(true);
    }
}