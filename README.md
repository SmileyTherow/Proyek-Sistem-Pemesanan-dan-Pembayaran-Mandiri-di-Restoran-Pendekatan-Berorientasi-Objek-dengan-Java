# Sistem Pemesanan dan Pembayaran Mandiri di Restoran

## Deskripsi Proyek

Aplikasi Sistem Pemesanan dan Pembayaran Mandiri yang dirancang untuk memudahkan proses pemesanan makanan dan pembayaran di restoran. Aplikasi ini menggunakan bahasa pemrograman Java dengan pendekatan Object-Oriented Programming (OOP), yang memungkinkan pengelolaan objek secara lebih efisien dan terstruktur.

## Fitur Utama

- **Pemilihan Menu**: Pengguna dapat memilih makanan dan minuman dari daftar menu yang tersedia
- **Pemesanan**: Pengguna dapat menambahkan item ke dalam pesanan dan melihat rincian total harga
- **Pembayaran**: Sistem mendukung berbagai metode pembayaran, termasuk pembayaran menggunakan cash dan card
- **Panel Admin**: Sistem manajemen untuk administrator dengan fitur-fitur khusus
- **Laporan Transaksi**: Kemampuan untuk menghasilkan dan menyimpan laporan transaksi

## Struktur OOP

Aplikasi dibangun dengan konsep Object-Oriented Programming yang memanfaatkan kelas dan objek untuk setiap komponen dalam sistem:

### Kelas Utama

```java
// Contoh struktur kelas
public class MenuItem {
    private String nama;
    private double harga;
    private String kategori;
    
    // Constructor, getter, setter methods
}

public class Order {
    private List<MenuItem> items;
    private double totalHarga;
    
    // Methods untuk mengelola pesanan
}

public class Payment {
    private String metodePembayaran;
    private double jumlahBayar;
    
    // Methods untuk proses pembayaran
}
```

## Teknologi yang Digunakan

- **Java**: Bahasa pemrograman utama
- **Apache POI**: Library untuk membaca dan menulis data dalam format Excel
- **Swing**: Untuk membangun GUI (Graphical User Interface)
- **NetBeans**: IDE pengembangan

## Persyaratan Sistem

- Java Development Kit (JDK) 8 atau lebih baru
- NetBeans IDE (opsional, dapat menggunakan IDE lain)
- Apache POI library

## Instalasi dan Pengaturan

### 1. Clone Repository
```bash
git clone <repository-url>
cd MenuMakananApp
```

### 2. Import ke NetBeans
1. Buka NetBeans IDE
2. File → Open Project
3. Pilih folder proyek MenuMakananApp
4. Klik "Open Project"

### 3. Setup Dependencies
Pastikan library Apache POI tersedia di folder `/libs` atau konfigurasikan melalui Maven:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.0.0</version>
    </dependency>
</dependencies>
```

## Cara Menjalankan

### Melalui NetBeans
1. Buka proyek di NetBeans
2. Klik kanan pada proyek → Run
3. Atau tekan F6

### Melalui Command Line
```bash
# Compile
javac -cp "libs/*" src/**/*.java -d build/

# Run
java -cp "build:libs/*" MainClass
```

### Melalui JAR File
```bash
# Build JAR
ant jar

# Run JAR
java -jar dist/MenuMakananApp.jar
```

## Struktur Direktori

```
MenuMakananApp/
├── src/                    # Source code utama
│   ├── model/             # Kelas model (MenuItem, Order, Payment)
│   ├── view/              # GUI components
│   ├── controller/        # Logic controllers
│   └── Main.java          # Entry point aplikasi
├── test/                  # Test files
│   └── Admin/            # Admin related tests
├── libs/                  # External libraries
├── build/                 # Compiled files
├── dist/                  # Distribution files
├── nbproject/            # NetBeans project files
└── README.md
```

## Penggunaan Aplikasi

### Untuk Pelanggan
1. **Pilih Menu**: Browse dan pilih makanan/minuman dari menu yang tersedia
2. **Tambah ke Pesanan**: Klik item untuk menambahkan ke keranjang pesanan
3. **Review Pesanan**: Periksa item yang dipilih dan total harga
4. **Pilih Pembayaran**: Pilih metode pembayaran (Cash/Card)
5. **Konfirmasi**: Selesaikan transaksi

### Untuk Admin
1. **Login Admin**: Masuk menggunakan kredensial administrator
2. **Kelola Stok**: Monitor dan update stok barang
3. **Barang Masuk/Keluar**: Catat pergerakan inventory
4. **Laporan**: Lihat dan export laporan transaksi

## Fitur Admin Panel

- **AdminLogin.java**: Sistem login untuk administrator
- **AdminMainPage.java**: Dashboard utama admin
- **StokBarangPanel.java**: Manajemen stok barang
- **BarangMasukPanel.java**: Pencatatan barang masuk
- **BarangKeluarPanel.java**: Pencatatan barang keluar

## Kontribusi

Proyek ini dibuat sebagai bagian dari tugas Praktikum Pemrograman Berorientasi Objek. Untuk kontribusi:

1. Fork repository
2. Buat feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit perubahan (`git commit -m 'Add some AmazingFeature'`)
4. Push ke branch (`git push origin feature/AmazingFeature`)
5. Buka Pull Request

## Build Commands

```bash
# Clean build
ant clean

# Compile
ant compile

# Build JAR
ant jar

# Run tests
ant test

# Generate Javadoc
ant javadoc
```

## Troubleshooting

### Common Issues

1. **ClassNotFoundException**: Pastikan semua library di folder libs
2. **Build Failed**: Periksa Java path dan version
3. **GUI tidak muncul**: Pastikan Swing dependencies tersedia

### Log Files
Check build logs di `build/` directory untuk detail error.

## License

Proyek ini dibuat untuk keperluan edukasi dalam mata kuliah Pemrograman Berorientasi Objek.

## Author

Proyek Praktikum Pemrograman Berorientasi Objek

## Catatan Penting

- Pastikan semua file yang dibutuhkan, seperti Apache POI (untuk file Excel), sudah terinstal atau tersedia di folder `/libs`
- Aplikasi ini dirancang untuk lingkungan pembelajaran dan dapat dikembangkan lebih lanjut sesuai kebutuhan
- Untuk deployment production, pertimbangkan aspek keamanan dan optimasi performa

---

*Dibuat sebagai bagian dari tugas Praktikum Pemrograman Berorientasi Objek*
