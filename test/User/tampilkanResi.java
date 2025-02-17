import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.io.*;
import java.util.HashMap;

public class tampilkanResi extends JFrame {
    private static final String FILE_PATH = "menu_pesanan.csv";
    private static final String NOTEPAD_FILE_PATH = "menu_pesanan.txt";

    public tampilkanResi(Map<String, Integer> pesananMakanan, Map<String, Integer> pesananMinuman, Map<String, Integer> pesananDessert, String tempatDuduk, String metodePembayaran, int totalHarga) {
        // Pengaturan dasar jendela
        setTitle("Tampilkan Resi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Membuat jendela menjadi penuh
        setUndecorated(true); // Menghapus border jendela
        
        // Membuat JTextArea untuk menampilkan resi
        JTextArea textAreaResi = new JTextArea();
        textAreaResi.setEditable(false);
        textAreaResi.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Mengatur font
        textAreaResi.setMargin(new Insets(10, 10, 10, 10)); // Margin di sekitar teks
        textAreaResi.setBackground(Color.WHITE); // Warna latar belakang
        textAreaResi.setForeground(Color.BLACK); // Warna teks
        
        // Membuat string builder untuk membentuk resi
        StringBuilder resi = new StringBuilder();
        resi.append("                Resi Pesanan\n");
        resi.append("=============================================\n");
        
        // Menambahkan tanggal dan waktu ke resi
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String tanggalWaktu = sdf.format(new Date());
        resi.append(String.format("Tanggal dan Waktu: %s\n", tanggalWaktu));
        resi.append("=============================================\n");
        
        // Menampilkan pesanan makanan
        resi.append(String.format("%-20s%5s%15s\n", "Makanan", "Jumlah", "Harga"));
        resi.append("---------------------------------------------\n");
        for (Map.Entry<String, Integer> entry : pesananMakanan.entrySet()) {
            int hargaPerItem = getHargaMakanan(entry.getKey());
            int totalHargaItem = hargaPerItem * entry.getValue();
            resi.append(String.format("%-20s%5d%15d\n", entry.getKey(), entry.getValue(), totalHargaItem));
        }
        resi.append("=============================================\n");
        
        // Menampilkan pesanan minuman
        resi.append(String.format("%-20s%5s%15s\n", "Minuman", "Jumlah", "Harga"));
        resi.append("---------------------------------------------\n");
        for (Map.Entry<String, Integer> entry : pesananMinuman.entrySet()) {
            int hargaPerItem = getHargaMinuman(entry.getKey());
            int totalHargaItem = hargaPerItem * entry.getValue();
            resi.append(String.format("%-20s%5d%15d\n", entry.getKey(), entry.getValue(), totalHargaItem));
        }
        resi.append("=============================================\n");
        
        // Menampilkan pesanan dessert
        resi.append(String.format("%-20s%5s%15s\n", "Dessert", "Jumlah", "Harga"));
        resi.append("---------------------------------------------\n");
        for (Map.Entry<String, Integer> entry : pesananDessert.entrySet()) {
            int hargaPerItem = getHargaDessert(entry.getKey());
            int totalHargaItem = hargaPerItem * entry.getValue();
            resi.append(String.format("%-20s%5d%15d\n", entry.getKey(), entry.getValue(), totalHargaItem));
        }
        resi.append("=============================================\n");
        
        // Menambahkan tempat duduk dan metode pembayaran
        resi.append(String.format("Tempat Duduk: %s\n", tempatDuduk));
        resi.append(String.format("Metode Pembayaran: %s\n", metodePembayaran));
        resi.append("=============================================\n");
        resi.append(String.format("Total Harga: Rp %d\n", totalHarga));

        textAreaResi.setText(resi.toString());
        
        // Menambahkan JTextArea ke panel tengah
        JPanel panelCenter = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelCenter.add(new JScrollPane(textAreaResi), gbc);
        add(panelCenter, BorderLayout.CENTER);
        
        // Membuat tombol tutup
        JButton buttonTutup = new JButton("Cetak Resi");
        buttonTutup.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonTutup.addActionListener(e -> {
            // Ganti halaman selanjutnya
            new halamanPenutup();
            dispose();
        });
        
        JPanel panelBawah = new JPanel();
        panelBawah.add(buttonTutup);
        add(panelBawah, BorderLayout.SOUTH);
        
        setVisible(true);

        // Tulis pesanan ke file CSV setelah menampilkan resi
        simpanPesananKeCSV(pesananMakanan, pesananMinuman, pesananDessert);
        // Tulis pesanan ke file Notepad setelah menampilkan resi
        tambahPesananKeNotepad(pesananMakanan, pesananMinuman, pesananDessert);
    }

    // Metode untuk mendapatkan harga makanan berdasarkan item
    private int getHargaMakanan(String item) {
        switch (item) {
            case "Nasi Goreng": return 25000;
            case "Mie Goreng": return 23000;
            case "Ayam Bakar": return 30000;
            case "Rendang": return 35000;
            case "Soto Ayam": return 22000;
            case "Bakso": return 20000;
            case "Ikan Bakar": return 40000;
            case "Sate Ayam": return 25000;
            case "Spageti": return 30000;
            default: return 0;
        }
    }
    
    // Metode untuk mendapatkan harga minuman berdasarkan item
    private int getHargaMinuman(String item) {
        switch (item) {
            case "Es Teh Manis": return 8000;
            case "Jus Jeruk": return 15000;
            case "Kopi Hitam": return 12000;
            case "Teh Manis Hangat": return 7000;
            case "Jus Mangga": return 18000;
            case "Jus Apel": return 18000;
            case "Soda Gembira": return 20000;
            case "Es Cendol": return 15000;
            case "Es Kelapa Muda": return 18000;
            default: return 0;
        }
    }
    
    // Metode untuk mendapatkan harga dessert berdasarkan item
    private int getHargaDessert(String item) {
        switch (item) {
            case "Cream Soup Jagung": return 25000;
            case "Cupcakes": return 15000;
            case "Es Teler": return 20000;
            case "Ice Cream": return 18000;
            case "Mochi": return 12000;
            case "Panekuk": return 30000;
            case "Puding Buah": return 25000;
            case "Salad Buah": return 28000;
            case "Tiramisu": return 35000;
            default: return 0;
        }
    }

    // Metode untuk menyimpan pesanan ke file CSV dengan header di atas
    private void simpanPesananKeCSV(Map<String, Integer> pesananMakanan, Map<String, Integer> pesananMinuman, Map<String, Integer> pesananDessert) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tanggal = sdf.format(new Date());

        // Muat data yang ada dari file CSV
        Map<String, Integer> allOrdersMakanan = new HashMap<>();
        Map<String, Integer> allOrdersMinuman = new HashMap<>();
        Map<String, Integer> allOrdersDessert = new HashMap<>();
        boolean dateExists = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            String currentSection = "";
            while ((line = br.readLine()) != null) {
                if (line.equals("Tanggal: " + tanggal)) {
                    dateExists = true;
                } else if (line.equals("Makanan:")) {
                    currentSection = "Makanan";
                } else if (line.equals("Minuman:")) {
                    currentSection = "Minuman";
                } else if (line.equals("Dessert:")) {
                    currentSection = "Dessert";
                } else if (line.isEmpty()) {
                    currentSection = "";
                } else {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        String item = values[0];
                        int quantity = Integer.parseInt(values[1]);
                        switch (currentSection) {
                            case "Makanan":
                                allOrdersMakanan.put(item, allOrdersMakanan.getOrDefault(item, 0) + quantity);
                                break;
                            case "Minuman":
                                allOrdersMinuman.put(item, allOrdersMinuman.getOrDefault(item, 0) + quantity);
                                break;
                            case "Dessert":
                                allOrdersDessert.put(item, allOrdersDessert.getOrDefault(item, 0) + quantity);
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Jika file tidak ada, lanjutkan tanpa kesalahan
        }

        // Tambahkan data baru
        updateOrderMap(allOrdersMakanan, pesananMakanan);
        updateOrderMap(allOrdersMinuman, pesananMinuman);
        updateOrderMap(allOrdersDessert, pesananDessert);

        // Tulis kembali data ke file CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("Tanggal: " + tanggal + "\n");

            writer.write("Makanan:\n");
            for (Map.Entry<String, Integer> entry : allOrdersMakanan.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.write("Minuman:\n");
            for (Map.Entry<String, Integer> entry : allOrdersMinuman.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.write("Dessert:\n");
            for (Map.Entry<String, Integer> entry : allOrdersDessert.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.flush();
            System.out.println("Pesanan berhasil disimpan ke file CSV: " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menyimpan pesanan ke file CSV.");
        }
    }

    // Metode untuk menambah pesanan ke file Notepad
    private void tambahPesananKeNotepad(Map<String, Integer> pesananMakanan, Map<String, Integer> pesananMinuman, Map<String, Integer> pesananDessert) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String tanggal = sdf.format(new Date());

        // Muat data yang ada dari file Notepad
        Map<String, Integer> allOrdersMakanan = new HashMap<>();
        Map<String, Integer> allOrdersMinuman = new HashMap<>();
        Map<String, Integer> allOrdersDessert = new HashMap<>();
        StringBuilder existingContent = new StringBuilder();
        boolean dateExists = false;

        try (BufferedReader br = new BufferedReader(new FileReader(NOTEPAD_FILE_PATH))) {
            String line;
            String currentSection = "";
            while ((line = br.readLine()) != null) {
                existingContent.append(line).append("\n");
                if (line.equals("Tanggal: " + tanggal)) {
                    dateExists = true;
                } else if (line.equals("Makanan:")) {
                    currentSection = "Makanan";
                } else if (line.equals("Minuman:")) {
                    currentSection = "Minuman";
                } else if (line.equals("Dessert:")) {
                    currentSection = "Dessert";
                } else if (line.isEmpty()) {
                    currentSection = "";
                } else {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        String item = values[0];
                        int quantity = Integer.parseInt(values[1]);
                        switch (currentSection) {
                            case "Makanan":
                                allOrdersMakanan.put(item, allOrdersMakanan.getOrDefault(item, 0) + quantity);
                                break;
                            case "Minuman":
                                allOrdersMinuman.put(item, allOrdersMinuman.getOrDefault(item, 0) + quantity);
                                break;
                            case "Dessert":
                                allOrdersDessert.put(item, allOrdersDessert.getOrDefault(item, 0) + quantity);
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Jika file tidak ada, lanjutkan tanpa kesalahan
        }

        // Tambahkan data baru
        updateOrderMap(allOrdersMakanan, pesananMakanan);
        updateOrderMap(allOrdersMinuman, pesananMinuman);
        updateOrderMap(allOrdersDessert, pesananDessert);

        // Tulis kembali data ke file Notepad
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTEPAD_FILE_PATH))) {
            writer.write("Tanggal: " + tanggal + "\n\n");

            writer.write("Makanan:\n");
            for (Map.Entry<String, Integer> entry : allOrdersMakanan.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.write("Minuman:\n");
            for (Map.Entry<String, Integer> entry : allOrdersMinuman.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.write("Dessert:\n");
            for (Map.Entry<String, Integer> entry : allOrdersDessert.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.write("\n");

            writer.flush();
            System.out.println("Pesanan berhasil disimpan ke file Notepad: " + NOTEPAD_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan saat menyimpan pesanan ke file Notepad.");
        }
    }

    // Metode untuk memperbarui peta pesanan
    private void updateOrderMap(Map<String, Integer> allOrders, Map<String, Integer> newOrders) {
        for (Map.Entry<String, Integer> entry : newOrders.entrySet()) {
            allOrders.put(entry.getKey(), allOrders.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }
}