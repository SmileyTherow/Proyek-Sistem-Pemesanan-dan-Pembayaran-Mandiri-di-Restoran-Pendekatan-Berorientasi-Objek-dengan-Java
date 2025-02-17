import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Pembayaran extends JFrame {
    private Map<String, Integer> pesananMakanan;
    private Map<String, Integer> pesananMinuman;
    private Map<String, Integer> pesananDessert;
    private String tempatDuduk;
    private int totalHarga;
    private static final Map<String, Integer> hargaMakanan = new HashMap<>();
    private static final Map<String, Integer> hargaMinuman = new HashMap<>();
    private static final Map<String, Integer> hargaDessert = new HashMap<>();
    
    // Inisialisasi harga makanan, minuman, dan dessert
    static {
        hargaMakanan.put("Nasi Goreng", 25000);
        hargaMakanan.put("Mie Goreng", 23000);
        hargaMakanan.put("Ayam Bakar", 30000);
        hargaMakanan.put("Rendang", 35000);
        hargaMakanan.put("Soto Ayam", 22000);
        hargaMakanan.put("Bakso", 20000);
        hargaMakanan.put("Ikan Bakar", 40000);
        hargaMakanan.put("Sate Ayam", 25000);
        hargaMakanan.put("Spageti", 30000);

        hargaMinuman.put("Es Teh Manis", 8000);
        hargaMinuman.put("Jus Jeruk", 15000);
        hargaMinuman.put("Kopi Hitam", 12000);
        hargaMinuman.put("Teh Manis Hangat", 7000);
        hargaMinuman.put("Jus Mangga", 18000);
        hargaMinuman.put("Jus Apel", 18000);
        hargaMinuman.put("Soda Gembira", 20000);
        hargaMinuman.put("Es Cendol", 15000);
        hargaMinuman.put("Es Kelapa Muda", 18000);

        hargaDessert.put("Cream Soup Jagung", 25000);
        hargaDessert.put("Cupcakes", 15000);
        hargaDessert.put("Es Teler", 20000);
        hargaDessert.put("Ice Cream", 18000);
        hargaDessert.put("Mochi", 12000);
        hargaDessert.put("Panekuk", 30000);
        hargaDessert.put("Puding Buah", 25000);
        hargaDessert.put("Salad Buah", 28000);
        hargaDessert.put("Tiramisu", 35000);
    }
    
    public Pembayaran(Map<String, Integer> pesananMakanan, Map<String, Integer> pesananMinuman, Map<String, Integer> pesananDessert, String tempatDuduk) {
        this.pesananMakanan = pesananMakanan;
        this.pesananMinuman = pesananMinuman;
        this.pesananDessert = pesananDessert;
        this.tempatDuduk = tempatDuduk;
        this.totalHarga = 0;
        
        // Inisialisasi harga makanan, minuman, dan dessert
        System.out.println("Pesanan Makanan: " + pesananMakanan);
        System.out.println("Pesanan Minuman: " + pesananMinuman);
        System.out.println("Pesanan Dessert: " + pesananDessert);
        
        // Pengaturan dasar jendela
        setTitle("Pembayaran");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Panel untuk menampilkan pesanan
        JPanel panelPesanan = new JPanel();
        panelPesanan.setLayout(new BoxLayout(panelPesanan, BoxLayout.Y_AXIS));
        panelPesanan.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(Box.createRigidArea(new Dimension(0, 20)));       
        JLabel labelJudulPesanan = new JLabel("Pesanan Anda:");
        labelJudulPesanan.setFont(new Font("Arial", Font.BOLD, 18));
        labelJudulPesanan.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(labelJudulPesanan);
        panelPesanan.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Menampilkan pesanan makanan, minuman, dan dessert
        tampilkanPesanan(panelPesanan, pesananMakanan, hargaMakanan);
        tampilkanPesanan(panelPesanan, pesananMinuman, hargaMinuman);
        tampilkanPesanan(panelPesanan, pesananDessert, hargaDessert);
        
        // Menampilkan tempat duduk yg dipilih 
        JLabel labelTempatDuduk = new JLabel("Tempat Duduk: " + tempatDuduk);
        labelTempatDuduk.setFont(new Font("Arial", Font.PLAIN, 14));
        labelTempatDuduk.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(labelTempatDuduk);
        
        // Menampilkan Total pesanan yg diilih
        JLabel labelTotalHarga = new JLabel("Total Harga: Rp " + totalHarga);
        labelTotalHarga.setFont(new Font("Arial", Font.BOLD, 16));
        labelTotalHarga.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPesanan.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPesanan.add(labelTotalHarga);
        
        // Panel untuk tombol pembayaran
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton buttonCash = new JButton("Cash");
        JButton buttonKartu = new JButton("Kartu");
        JButton buttonQRIS = new JButton("QRIS");
        Dimension buttonSize = new Dimension(150, 50);
        buttonCash.setPreferredSize(buttonSize);
        buttonKartu.setPreferredSize(buttonSize);
        buttonQRIS.setPreferredSize(buttonSize);
        
        // Untuk pembayaran cash
        buttonCash.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Pembayaran Cash");
            
            // Ganti halaman selanjutnya
            new tampilkanResi(pesananMakanan, pesananMinuman, pesananDessert, tempatDuduk, "Cash", totalHarga);
            dispose();
        });
        
        // Untuk pembayaran dengan kartu
        buttonKartu.addActionListener(e -> {
            String[] options = {"BCA", "BNI", "BRI", "BTN", "Mandiri", "Mega", "Panin", "Permata"};
            ImageIcon[] icons = {
                new ImageIcon("images/BCA.jpeg"),
                new ImageIcon("images/BNI.png"),
                new ImageIcon("images/BRI.jpeg"),
                new ImageIcon("images/BTN.jpg"),
                new ImageIcon("images/Mandiri.jpeg"),
                new ImageIcon("images/Mega.jpeg"),
                new ImageIcon("images/Panin.jpeg"),
                new ImageIcon("images/Permata.jpg")
            };
            
            // Membuat panel untuk virtual account
            JPanel panelVA = new JPanel(new GridLayout(4, 2, 10, 10));
            ButtonGroup bg = new ButtonGroup();
            JRadioButton[] radioButtons = new JRadioButton[options.length];
            
            // Menambahkan opsi virtual account ke panel
            for (int i = 0; i < options.length; i++) {
                radioButtons[i] = new JRadioButton(options[i]);
                radioButtons[i].setIcon(new ImageIcon(
                    icons[i].getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)
                ));
                radioButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
                bg.add(radioButtons[i]);
                panelVA.add(radioButtons[i]);
                radioButtons[i].addActionListener(event -> {
                    String selectedVA = ((JRadioButton) event.getSource()).getText();
                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(panelVA);
                    dialog.dispose();
                    String noRek = JOptionPane.showInputDialog(this, "Masukkan Nomor Rekening untuk " + selectedVA + ":");
                    if (noRek != null && noRek.matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "Pembayaran dengan Kartu berhasil!");
                        new tampilkanResi(pesananMakanan, pesananMinuman, pesananDessert, tempatDuduk, "Kartu - " + selectedVA, totalHarga);
                        dispose();
                    } else if (noRek != null) {
                        JOptionPane.showMessageDialog(this, "Nomor rekening tidak valid!");
                    }
                });
            }
            
            // Menampilkan untuk memilih virtual account
            JDialog dialog = new JDialog(this, "Pilih Virtual Account", true);
            dialog.getContentPane().add(panelVA);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });
        
        // Untuk pembayaran dengan QRIS
        buttonQRIS.addActionListener(e -> {
            JDialog qrDialog = new JDialog(this, "QR Code", true);
            qrDialog.setSize(400, 450);
            qrDialog.setLayout(new BorderLayout());
            qrDialog.setLocationRelativeTo(this);
            ImageIcon qrCodeImage = new ImageIcon("images/qr_kode.png");
            JLabel qrCodeLabel = new JLabel();
            qrCodeLabel.setIcon(new ImageIcon(qrCodeImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
            qrCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            qrDialog.add(qrCodeLabel, BorderLayout.CENTER);
            JButton buttonCloseQR = new JButton("Tutup");
            buttonCloseQR.setPreferredSize(new Dimension(100, 40));
            buttonCloseQR.addActionListener(close -> {
                qrDialog.dispose();
                new tampilkanResi(pesananMakanan, pesananMinuman, pesananDessert, tempatDuduk, "QRIS", totalHarga);
                dispose();
            });
            qrDialog.add(buttonCloseQR, BorderLayout.SOUTH);
            qrDialog.setVisible(true);
        });
        
        panelTombol.add(buttonCash);
        panelTombol.add(buttonKartu);
        panelTombol.add(buttonQRIS);
        
        // Tombol bantuan
        JButton buttonBantuan = new JButton("?");
        buttonBantuan.setPreferredSize(new Dimension(45, 45));
        buttonBantuan.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Bantuan Pembayaran:\n\n" +
                    "1. Pilih metode pembayaran (Cash, Kartu, QRIS).\n" +
                    "2. Jika memilih Kartu, pilih Virtual Account dan masukkan nomor rekening.\n" +
                    "3. Jika memilih QRIS, pindai kode QR untuk melakukan pembayaran.\n" +
                    "4. Setelah pembayaran berhasil, resi akan ditampilkan.");
        });
        JPanel panelBantuan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBantuan.add(buttonBantuan);
        add(panelPesanan, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);
        add(panelBantuan, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    // Menampilkan pesanan
    private void tampilkanPesanan(JPanel panelPesanan, Map<String, Integer> pesanan, Map<String, Integer> harga) {
        for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
            String item = entry.getKey();
            int jumlah = entry.getValue();
            int hargaItem = harga.get(item);
            int totalHargaItem = hargaItem * jumlah;
            totalHarga += totalHargaItem;
            
            // Label untuk menampilkan setiap item yg dipesanan
            JLabel labelItem = new JLabel(item + " - " + jumlah + " x Rp " + hargaItem + " = Rp " + totalHargaItem);
            labelItem.setFont(new Font("Arial", Font.PLAIN, 14));
            labelItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelPesanan.add(labelItem);
        }
    }
}