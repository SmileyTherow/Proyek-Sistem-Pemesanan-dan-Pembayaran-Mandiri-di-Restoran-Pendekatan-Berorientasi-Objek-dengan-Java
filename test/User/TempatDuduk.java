import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TempatDuduk extends JFrame {
    private List<String> pesananMakanan;
    private List<Integer> jumlahMakanan;
    private List<String> pesananMinuman;
    private List<Integer> jumlahMinuman;
    private List<String> pesananDessert;
    private List<Integer> jumlahDessert;
    private String tempatDuduk = null;
    private boolean[][] tempatDudukTersedia = new boolean[10][4];
    private String pilihanTempat = null;
    private boolean tempatDudukSudahDipilih = false;
    private Map<String, Integer> kapasitasMeja = new HashMap<>();
    private String lokasiPilihan = null;
    
    public TempatDuduk(List<String> pesananMakanan, List<Integer> jumlahMakanan, List<String> pesananMinuman, List<Integer> jumlahMinuman, List<String> pesananDessert, List<Integer> jumlahDessert) {
        this.pesananMakanan = pesananMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.pesananMinuman = pesananMinuman;
        this.jumlahMinuman = jumlahMinuman;
        this.pesananDessert = pesananDessert;
        this.jumlahDessert = jumlahDessert;
        
        // Inisialisasi tempat duduk tersedia dan kapasitas meja
        for (int i = 0; i < tempatDudukTersedia.length; i++) {
            for (int j = 0; j < tempatDudukTersedia[i].length; j++) {
                tempatDudukTersedia[i][j] = true;
                kapasitasMeja.put("Meja " + (i + 1) + "-" + (j + 1), (i + j) % 3 == 0 ? 2 : ((i + j) % 3 == 1 ? 4 : 6));
            }
        }
        
        // Pengaturan dasar jendela
        setTitle("Pilih Tempat Duduk");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        // Panel judul
        JPanel panelJudul = new JPanel(new BorderLayout());
        JLabel labelJudul = new JLabel("Pilih Tempat Duduk", SwingConstants.CENTER);
        labelJudul.setFont(new Font("Serif", Font.BOLD, 24));
        JSeparator separator = new JSeparator();
        panelJudul.add(labelJudul, BorderLayout.CENTER);
        panelJudul.add(separator, BorderLayout.SOUTH);
        
        // Panel tempat duduk
        JPanel panelTempatDuduk = new JPanel(new GridLayout(10, 4, 10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                String meja = "Meja " + (i + 1) + "-" + (j + 1);
                JButton buttonTempatDuduk = new JButton(meja + " (" + kapasitasMeja.get(meja) + " orang)");
                buttonTempatDuduk.setBackground(Color.GREEN);
                int row = i, col = j;
                buttonTempatDuduk.addActionListener(e -> {
                    if (tempatDudukTersedia[row][col] && !tempatDudukSudahDipilih) {
                        tempatDuduk = meja + " (" + (lokasiPilihan != null ? lokasiPilihan : "Lokasi") + ")";
                        buttonTempatDuduk.setBackground(Color.RED);
                        tempatDudukTersedia[row][col] = false;
                        tempatDudukSudahDipilih = true;
                        JOptionPane.showMessageDialog(this, "Tempat duduk " + tempatDuduk + " telah dipilih.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Tempat duduk ini sudah dipilih.");
                    }
                });
                panelTempatDuduk.add(buttonTempatDuduk);
            }
        }
        
        // Panel filter
        JPanel panelFilter = new JPanel(new FlowLayout());
        JComboBox<String> filterLokasi = new JComboBox<>(new String[]{"Pilih Lokasi", "Indoor", "Outdoor"});
        JComboBox<String> filterKapasitas = new JComboBox<>(new String[]{"Semua", "2 Orang", "4 Orang", "6 Orang"});
        panelFilter.add(new JLabel("Lokasi:"));
        panelFilter.add(filterLokasi);
        panelFilter.add(new JLabel("Kapasitas:"));
        panelFilter.add(filterKapasitas);
        
        // filter lokasi
        filterLokasi.addActionListener(e -> {
            lokasiPilihan = filterLokasi.getSelectedIndex() > 0 ? (String) filterLokasi.getSelectedItem() : null;
            applyFilters(filterLokasi, filterKapasitas, panelTempatDuduk);
        });
        
        // filter kapasitas
        filterKapasitas.addActionListener(e -> applyFilters(filterLokasi, filterKapasitas, panelTempatDuduk));
        
        // Panel tombol
        JPanel panelTombol = new JPanel(new BorderLayout());
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton buttonLanjut = new JButton("Lanjut ke Kelola Pesanan");
        buttonLanjut.setPreferredSize(new Dimension(200, 50));
        buttonLanjut.addActionListener(e -> {
            if (lokasiPilihan == null || "Pilih Lokasi".equals(lokasiPilihan)) {
                JOptionPane.showMessageDialog(this, "Silakan pilih lokasi (Indoor atau Outdoor) terlebih dahulu.");
            } else if (tempatDuduk != null) {
                
                 // Ganti halaman selanjutnya
                new KelolaPesanan(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, pesananDessert, jumlahDessert, tempatDuduk);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Silakan pilih tempat duduk terlebih dahulu.");
            }
        });
        panelTombol.add(buttonLanjut, BorderLayout.CENTER);
        
        // Panel tombol bantuan
        JPanel panelHelp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton helpButton = new JButton("?");
        helpButton.setPreferredSize(new Dimension(50, 50));
        helpButton.addActionListener(e -> {
            String helpMessage = "Cara menggunakan aplikasi:\n\n"
                    + "1. Pilih apakah Anda ingin duduk Indoor atau Outdoor.\n"
                    + "2. Pilih tempat duduk yang tersedia dengan menekan tombol 'Meja'.\n"
                    + "3. Setelah memilih tempat duduk, tekan tombol 'Lanjut ke Kelola Pesanan'.\n"
                    + "4. Jika membutuhkan bantuan lebih lanjut, silakan hubungi layanan pelanggan.";
            JOptionPane.showMessageDialog(this, helpMessage, "Bantuan", JOptionPane.INFORMATION_MESSAGE);
        });
        panelHelp.add(helpButton);
        panelTombol.add(panelHelp, BorderLayout.EAST);
        panelTombol.add(helpButton, BorderLayout.LINE_END);
        add(panelJudul, BorderLayout.NORTH);
        add(panelFilter, BorderLayout.WEST);
        add(panelTempatDuduk, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    // Menerapkan filter
    private void applyFilters(JComboBox<String> filterLokasi, JComboBox<String> filterKapasitas, JPanel panelTempatDuduk) {
        String lokasi = (String) filterLokasi.getSelectedItem();
        String kapasitas = (String) filterKapasitas.getSelectedItem();
        panelTempatDuduk.removeAll();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                String meja = "Meja " + (i + 1) + "-" + (j + 1);
                int seatCapacity = kapasitasMeja.get(meja);
                boolean match = true;
                if (!kapasitas.equals("Semua")) {
                    int kapasitasInt = Integer.parseInt(kapasitas.split(" ")[0]);
                    match = seatCapacity == kapasitasInt;
                }
                if (match) {
                    JButton buttonTempatDuduk = new JButton(meja + " (" + seatCapacity + " orang)");
                    if (!tempatDudukTersedia[i][j]) {
                        buttonTempatDuduk.setBackground(Color.RED);
                    } else {
                        buttonTempatDuduk.setBackground(Color.GREEN);
                    }
                    int row = i, col = j;
                    buttonTempatDuduk.addActionListener(e -> {
                        if (tempatDudukTersedia[row][col] && !tempatDudukSudahDipilih) {
                            tempatDuduk = meja + " (" + (lokasiPilihan != null ? lokasiPilihan : "Lokasi") + ")";
                            buttonTempatDuduk.setBackground(Color.RED);
                            tempatDudukTersedia[row][col] = false;
                            tempatDudukSudahDipilih = true;
                            JOptionPane.showMessageDialog(this, "Tempat duduk " + tempatDuduk + " telah dipilih.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Tempat duduk ini sudah dipilih.");
                        }
                    });
                    panelTempatDuduk.add(buttonTempatDuduk);
                }
            }
        }
        panelTempatDuduk.revalidate();
        panelTempatDuduk.repaint();
    }
}