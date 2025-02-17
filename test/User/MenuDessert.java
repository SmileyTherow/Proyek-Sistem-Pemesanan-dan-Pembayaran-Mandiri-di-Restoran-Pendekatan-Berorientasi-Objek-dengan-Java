import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuDessert extends JFrame {
    private List<String> pesananMakanan;
    private List<Integer> jumlahMakanan;
    private List<String> pesananMinuman;
    private List<Integer> jumlahMinuman;
    private List<String> pesananDessert;
    private List<Integer> jumlahDessert;
    
    public MenuDessert(List<String> pesananMakanan, List<Integer> jumlahMakanan, List<String> pesananMinuman, List<Integer> jumlahMinuman, List<String> pesananDessert, List<Integer> jumlahDessert) {
        this.pesananMakanan = pesananMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.pesananMinuman = pesananMinuman;
        this.jumlahMinuman = jumlahMinuman;
        this.pesananDessert = pesananDessert;
        this.jumlahDessert = jumlahDessert;

        // Mengatur judul jendela dan ukuran dasar
        setTitle("Menu Dessert");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Panel judul
        JPanel panelJudul = new JPanel(new BorderLayout());
        JLabel labelJudul = new JLabel("Menu Dessert", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Serif", Font.BOLD, 24));
        JSeparator separator = new JSeparator();
        panelJudul.add(labelJudul, BorderLayout.CENTER);
        panelJudul.add(separator, BorderLayout.SOUTH);
        
        // Panel menu dessert
        JPanel panelDessert = new JPanel();
        panelDessert.setLayout(new GridLayout(3, 3, 10, 10));
        String[] dessert = {"Cream Soup Jagung", "Cupcakes", "Es Teler", "Ice Cream", "Mochi", "Panekuk", "Puding Buah", "Salad Buah", "Tiramisu"};
        String[] gambarDessert = {"sopCreamJagung.jpeg", "Cupcakes.png", "Es_Teler.jpeg", "esCream.jpeg", "mochi.jpeg", "panekuk.png", "pudingBuah.jpeg", "saladBuah.jpeg", "Tiramisu.png"};
        String[] hargaDessert = {"25000", "15000", "20000", "18000", "12000", "30000", "25000", "28000", "35000"};
        
        // Menambahkan makanan ke panel menu makanan
        for (int i = 0; i < dessert.length; i++) {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel labelNama = new JLabel(dessert[i], SwingConstants.CENTER);
            JLabel labelHarga = new JLabel("Rp " + hargaDessert[i], SwingConstants.CENTER);
            labelHarga.setForeground(Color.GRAY);
            JLabel labelGambar = new JLabel(new ImageIcon("images/" + gambarDessert[i]));
            labelGambar.setHorizontalAlignment(SwingConstants.CENTER);
            JButton buttonPilih = new JButton("Pilih");
            int index = i;
            buttonPilih.addActionListener(e -> {
                tambahPesanan(pesananDessert, jumlahDessert, dessert[index]);
                JOptionPane.showMessageDialog(this, dessert[index] + " ditambahkan ke pesanan.");
            });
            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(labelNama);
            textPanel.add(labelHarga);
            panel.add(textPanel, BorderLayout.NORTH);
            panel.add(labelGambar, BorderLayout.CENTER);
            panel.add(buttonPilih, BorderLayout.SOUTH);
            panelDessert.add(panel);
        }

        // Panel tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton lanjutButton = new JButton("Lanjut ke Minuman");
        lanjutButton.setPreferredSize(new Dimension(200, 50));
        lanjutButton.addActionListener(e -> {
            
            // Ganti halaman selanjutya
            new MenuMinuman(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, pesananDessert, jumlahDessert);
            dispose();
        });
        JButton pesananButton = new JButton("Lihat Pesanan");
        pesananButton.setPreferredSize(new Dimension(200, 50));
        pesananButton.addActionListener(e -> {
            
            // Menampilkan pesanan yang telah dipilih
            StringBuilder pesananMessage = new StringBuilder("Pesanan Anda:\n\n");
            tampilkanPesanan(pesananMessage, pesananMakanan, jumlahMakanan);
            tampilkanPesanan(pesananMessage, pesananMinuman, jumlahMinuman);
            tampilkanPesanan(pesananMessage, pesananDessert, jumlahDessert);
            JOptionPane.showMessageDialog(this, pesananMessage.toString(), "Pesanan Anda", JOptionPane.INFORMATION_MESSAGE);
        });
        
        panelTombol.add(lanjutButton);
        panelTombol.add(pesananButton);
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.add(panelTombol, BorderLayout.CENTER);

        // Panel tombol bantuan
        JPanel panelHelp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton helpButton = new JButton("?");
        helpButton.setPreferredSize(new Dimension(50, 50));
        helpButton.addActionListener(e -> {
            String helpMessage = "Cara menggunakan aplikasi:\n\n"
                    + "1. Pilih dessert yang ingin Anda pesan dengan menekan tombol 'Pilih'.\n"
                    + "2. Setelah memilih dessert, Anda dapat melanjutkan ke menu minuman dengan menekan tombol 'Lanjut ke Minuman'.\n"
                    + "3. Jika ingin melihat menu yang telah dipesan tekan tombol 'Lihat Pesanan'. \n "
                    + "4. Jika membutuhkan bantuan lebih lanjut, silakan hubungi layanan pelanggan.";
            JOptionPane.showMessageDialog(this, helpMessage, "Bantuan", JOptionPane.INFORMATION_MESSAGE);
        });
        panelHelp.add(helpButton);
        panelBawah.add(panelHelp, BorderLayout.EAST);

        add(panelJudul, BorderLayout.NORTH);
        add(panelDessert, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    // Menambah pesanan ke list
    private void tambahPesanan(List<String> pesanan, List<Integer> jumlah, String item) {
        int index = pesanan.indexOf(item);
        if (index != -1) {
            jumlah.set(index, jumlah.get(index) + 1);
        } else {
            pesanan.add(item);
            jumlah.add(1);
        }
    }
    
    // Menampilkan pesanan
    private void tampilkanPesanan(StringBuilder pesananMessage, List<String> pesanan, List<Integer> jumlah) {
        Map<String, Integer> pesananMap = new HashMap<>();
        for (int i = 0; i < pesanan.size(); i++) {
            pesananMap.put(pesanan.get(i), jumlah.get(i));
        }
        for (Map.Entry<String, Integer> entry : pesananMap.entrySet()) {
            pesananMessage.append(entry.getKey()).append(" x").append(entry.getValue()).append("\n");
        }
    }
}