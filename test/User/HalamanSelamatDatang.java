import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HalamanSelamatDatang extends JFrame {
    
    // Set Halaman
    public HalamanSelamatDatang(List<String> pesananMakanan, List<Integer> jumlahMakanan, List<String> pesananMinuman, List<Integer> jumlahMinuman, Object par4) {
        setTitle("Selamat Datang di Vending Machine Restoran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Panel latar belakang
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("images/background(1).jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        
        // Membuat panel untuk teks
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelSelamatDatang = new JLabel("SELAMAT DATANG", SwingConstants.CENTER);
        labelSelamatDatang.setFont(new Font("Serif", Font.BOLD, 64));
        labelSelamatDatang.setForeground(Color.WHITE);
        labelSelamatDatang.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Label keterangan
        JLabel labelKeteranganTengah = new JLabel("Nikmati pengalaman pemesanan yang mudah dan cepat", SwingConstants.CENTER);
        labelKeteranganTengah.setFont(new Font("SansSerif", Font.ITALIC, 20));
        labelKeteranganTengah.setForeground(Color.LIGHT_GRAY);
        labelKeteranganTengah.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(labelSelamatDatang);
        textPanel.add(Box.createVerticalStrut(20));
        textPanel.add(labelKeteranganTengah);
        textPanel.add(Box.createVerticalGlue());
        
        // Tombol "MULAI PESANAN"
        JButton buttonMulaiPesanan = new JButton("MULAI PESANAN");
        buttonMulaiPesanan.setFont(new Font("Arial", Font.BOLD, 24));
        buttonMulaiPesanan.setPreferredSize(new Dimension(300, 60));
        buttonMulaiPesanan.addActionListener(e -> {
            
            // Ganti halaman selanjutnya
            new MenuMakanan(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, new ArrayList<>(), new ArrayList<>());
            dispose();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(buttonMulaiPesanan);
        backgroundPanel.add(textPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);
        
        setVisible(true);
        
    }
}