import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateMenuExcel {
    private static final String FILE_PATH = "menu_pesanan.csv";

    public static void main(String[] args) {
        createMenuExcel();
    }

    public static void createMenuExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Menu");

            // Daftar menu lengkap
            String[] menuItems = {
                "Nasi Goreng", "Mie Goreng", "Ayam Bakar", "Rendang", "Soto Ayam", "Bakso", "Ikan Bakar", "Sate Ayam", "Spageti", //menu makanan
                "Cream Soup Jagung", "Cupcakes", "Es Teler", "Ice Cream", "Mochi", "Panekuk", "Puding Buah", "Salad Buah", "Tiramisu", //menu dessert
                "Es Teh Manis", "Jus Jeruk", "Kopi Hitam", "Teh Manis Hangat", "Jus Mangga", "Jus Apel", "Soda Gembira", "Es Cendol", "Es Kelapa Muda" // meu minuman
            };

            // Membuat header
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nama Menu");
            headerRow.createCell(1).setCellValue("Jumlah Pesanan");

            // Mengisi data menu
            for (int i = 0; i < menuItems.length; i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(menuItems[i]);
                row.createCell(1).setCellValue(0); // Inisialisasi jumlah pesanan ke 0
            }

            // Menulis data ke file Excel
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }

            System.out.println("File Excel berhasil dibuat: " + FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}