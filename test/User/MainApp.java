import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        List<String> pesananMakanan = new ArrayList<>();
        List<Integer> jumlahMakanan = new ArrayList<>();
        List<String> pesananMinuman = new ArrayList<>();
        List<Integer> jumlahMinuman = new ArrayList<>();
        new HalamanSelamatDatang(pesananMakanan, jumlahMakanan, pesananMinuman, jumlahMinuman, null);
    }
}