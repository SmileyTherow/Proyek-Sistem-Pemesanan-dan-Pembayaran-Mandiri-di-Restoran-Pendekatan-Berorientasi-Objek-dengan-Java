import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KelolaPesanan extends JFrame {
    private List<String> pesananMakanan;
    private List<Integer> jumlahMakanan;
    private List<String> pesananMinuman;
    private List<Integer> jumlahMinuman;
    private List<String> pesananDessert;
    private List<Integer> jumlahDessert;
    private String tempatDuduk;
    private JPanel panelPesanan;
    public KelolaPesanan(List<String> pesananMakanan, List<Integer> jumlahMakanan, List<String> pesananMinuman, List<Integer> jumlahMinuman, List<String> pesananDessert, List<Integer> jumlahDessert, String tempatDuduk) {
        this.pesananMakanan = pesananMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.pesananMinuman = pesananMinuman;
        this.jumlahMinuman = jumlahMinuman;
        this.pesananDessert = pesananDessert;
        this.jumlahDessert = jumlahDessert;
        this.tempatDuduk = tempatDuduk;
        
        // Pengaturan dasar jendela
        setTitle("Kelola Pesanan");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Panel untuk menampilkan pesanan
        panelPesanan = new JPanel();
        panelPesanan.setLayout(new BoxLayout(panelPesanan, BoxLayout.Y_AXIS));
        panelPesanan.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(Box.createRigidArea(new Dimension(0, 20)));
        updatePanelPesanan();
        
        // Panel tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton buttonKembaliMakanan = new JButton("Kembali ke Menu Makanan");
        JButton buttonKembaliMinuman = new JButton("Kembali ke Menu Minuman");
        JButton buttonLanjutPembayaran = new JButton("Lanjut ke Pembayaran");
        
        // Untuk tombol kembali ke halaman menu makanan
        buttonKembaliMakanan.addActionListener(e -> {
            new MenuMakanan(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, pesananDessert, jumlahDessert);
            dispose();
        });
        
        // Untuk tombol kembali ke halaman menu minuman
        buttonKembaliMinuman.addActionListener(e -> {
            new MenuMinuman(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, pesananDessert, jumlahDessert);
            dispose();
        });
        
        // Untuk tombol lanjut ke halaman pembayaran
        buttonLanjutPembayaran.addActionListener(e -> {
            Map<String, Integer> pesananMakananMap = convertToMap(pesananMakanan, jumlahMakanan);
            Map<String, Integer> pesananMinumanMap = convertToMap(pesananMinuman, jumlahMinuman);
            Map<String, Integer> pesananDessertMap = convertToMap(pesananDessert, jumlahDessert);
            new Pembayaran(pesananMakananMap, pesananMinumanMap, pesananDessertMap, tempatDuduk);
            dispose();
        });
        
        panelTombol.add(buttonKembaliMakanan);
        panelTombol.add(buttonKembaliMinuman);
        panelTombol.add(buttonLanjutPembayaran);
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.add(panelTombol, BorderLayout.CENTER);
        
        // Tombol bantuan
        JButton buttonBantuan = new JButton("?");
        buttonBantuan.setPreferredSize(new Dimension(45, 45));
        buttonBantuan.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Bantuan Kelola Pesanan:\n\n" +
                    "1. Kembali ke menu makanan untuk menambah atau mengubah pesanan makanan.\n" +
                    "2. Kembali ke menu minuman untuk menambah atau mengubah pesanan minuman.\n" +
                    "3. Apabila ingin mengurangi pesanan yang telah dipilih tekan tombol (-).\n" +
                    "4. Kalau ingin menghapus pesanan yang telah dipilih tekan tombol (x).\n" +
                    "5. Lanjut ke pembayaran setelah semua pesanan ditambahkan.");
        });
        JPanel panelBantuan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBantuan.add(buttonBantuan);
        panelBawah.add(panelBantuan, BorderLayout.SOUTH);
        add(panelPesanan, BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    // Metode untuk memperbarui panel pesanan
    private void updatePanelPesanan() {
        panelPesanan.removeAll();
        JLabel labelJudulPesanan = new JLabel("Pesanan Anda:");
        labelJudulPesanan.setFont(new Font("Arial", Font.BOLD, 18));
        labelJudulPesanan.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(labelJudulPesanan);
        panelPesanan.add(Box.createRigidArea(new Dimension(0, 10)));
        addPesananToPanel(pesananMakanan, jumlahMakanan, "Makanan");
        addPesananToPanel(pesananMinuman, jumlahMinuman, "Minuman");
        addPesananToPanel(pesananDessert, jumlahDessert, "Dessert");
        JLabel labelTempatDuduk = new JLabel("Tempat Duduk: " + tempatDuduk);
        labelTempatDuduk.setFont(new Font("Arial", Font.PLAIN, 14));
        labelTempatDuduk.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(labelTempatDuduk);
        panelPesanan.revalidate();
        panelPesanan.repaint();
    }
    
    // Metode untuk menambahkan pesanan ke panel
    private void addPesananToPanel(List<String> pesanan, List<Integer> jumlah, String jenisPesanan) {
        Map<String, Integer> pesananMap = new HashMap<>();
        for (int i = 0; i < pesanan.size(); i++) {
            pesananMap.put(pesanan.get(i), pesananMap.getOrDefault(pesanan.get(i), 0) + jumlah.get(i));
        }
        for (Map.Entry<String, Integer> entry : pesananMap.entrySet()) {
            JPanel panelItem = new JPanel();
            panelItem.setLayout(new BoxLayout(panelItem, BoxLayout.X_AXIS));
            JLabel labelPesanan = new JLabel(entry.getKey() + " - " + entry.getValue() + " pcs");
            labelPesanan.setFont(new Font("Arial", Font.PLAIN, 14));
            panelItem.add(labelPesanan);
            
            // Tombol untuk mengurangi pesanan
            JButton buttonKurangi = new JButton("-");
            JButton buttonHapus = new JButton("x");
            buttonKurangi.setPreferredSize(new Dimension(50, 30));
            buttonHapus.setPreferredSize(new Dimension(80, 30));
            buttonKurangi.addActionListener(e -> {
                if (entry.getValue() > 1) {
                    pesananMap.put(entry.getKey(), entry.getValue() - 1);
                } else {
                    pesananMap.remove(entry.getKey());
                }
                refreshPesananLists(pesananMap, jenisPesanan);
                updatePanelPesanan();
            });
            
            // Tombol untuk menghapus pesanan
            buttonHapus.addActionListener(e -> {
                pesananMap.remove(entry.getKey());
                refreshPesananLists(pesananMap, jenisPesanan);
                updatePanelPesanan();
            });
            panelItem.add(Box.createRigidArea(new Dimension(5, 0)));
            panelItem.add(buttonKurangi);
            panelItem.add(Box.createRigidArea(new Dimension(5, 0)));
            panelItem.add(buttonHapus);
            panelPesanan.add(panelItem);
        }
    }
    
    // Memperbarui list pesanan
    private void refreshPesananLists(Map<String, Integer> pesananMap, String jenisPesanan) {
        if (jenisPesanan.equals("Makanan")) {
            pesananMakanan.clear();
            jumlahMakanan.clear();
            for (Map.Entry<String, Integer> entry : pesananMap.entrySet()) {
                pesananMakanan.add(entry.getKey());
                jumlahMakanan.add(entry.getValue());
            }
        } else if (jenisPesanan.equals("Minuman")) {
            pesananMinuman.clear();
            jumlahMinuman.clear();
            for (Map.Entry<String, Integer> entry : pesananMap.entrySet()) {
                pesananMinuman.add(entry.getKey());
                jumlahMinuman.add(entry.getValue());
            }
        } else if (jenisPesanan.equals("Dessert")) {
            pesananDessert.clear();
            jumlahDessert.clear();
            for (Map.Entry<String, Integer> entry : pesananMap.entrySet()) {
                pesananDessert.add(entry.getKey());
                jumlahDessert.add(entry.getValue());
            }
        }
    }
    
    // Metode untuk mengonversi list ke map
    private Map<String, Integer> convertToMap(List<String> items, List<Integer> quantities) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            map.put(items.get(i), quantities.get(i));
        }
        return map;
    }
}