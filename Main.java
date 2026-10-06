import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu[] daftarMenu = {
                new Menu("Nasi Goreng", 25000, "Makanan"),
                new Menu("Nasi Padang", 30000, "Makanan"),
                new Menu("Mie Ayam", 20000, "Makanan"),
                new Menu("Ayam Geprek", 22000, "Makanan"),
                new Menu("Es Teh", 5000, "Minuman"),
                new Menu("Jus Jeruk", 10000, "Minuman"),
                new Menu("Kopi", 8000, "Minuman"),
                new Menu("Es Campur", 15000, "Minuman")
        };

        tampilkanMenu(daftarMenu);
        int jumlahPesanan = 0;
        double totalPesanan = 0.0;
        String lanjut = "y";
        do {
            jumlahPesanan++;
            System.out.println("\nPesanan ke-" + jumlahPesanan);

            String pesanan = masukkanPesanan(input);
            Menu menuTerpilih = mencariPesanan(daftarMenu, pesanan);

            if (menuTerpilih != null) {
                System.out.println("===================================");
                System.out.println("Menu ditemukan!");
                System.out.println("Nama     : " + menuTerpilih.getNama());
                System.out.println("Harga    : Rp." + menuTerpilih.getHarga());
                System.out.println("Kategori : " + menuTerpilih.getKategori());

                int jumlah = tentukanJumlahPesanan(input);

                double subtotal = hitungSubtotal(menuTerpilih, jumlah);

                System.out.println("Jumlah   : " + jumlah);
                System.out.println("Subtotal : Rp." + subtotal);

                totalPesanan += subtotal;
            } else {
                System.out.println(
                        "Pesanan tidak dapat diproses karena menu tidak ditemukan.");
            }

            if (jumlahPesanan < 4) {
                System.out.print("\nApakah ingin memesan lagi? (y/n): ");
                lanjut = input.nextLine();
            } else {
                lanjut = "n";
                System.out.println("\nMaksimal 4 menu telah dipesan.");
            }
        } while (lanjut.equalsIgnoreCase("y"));

        System.out.println("\n===================================");
        System.out.println("Total Pesanan : Rp." + totalPesanan);
        System.out.println("===================================");
        input.close();
    }

    private static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("===================================");
        System.out.println("Daftar Menu:");
        System.out.println("===================================");
        for (Menu menu : daftarMenu) {
            System.out.println(menu);
        }
        System.out.println("===================================");
    }

    private static String masukkanPesanan(Scanner input) {
        System.out.print("Masukkan nama menu yang akan dipesan: ");
        return input.nextLine();
    }

    private static Menu mencariPesanan(Menu[] daftarMenu, String pesanan) {
        boolean menuDitemukan = false;
        Menu menuDitemukanObj = null;
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(pesanan)) {
                menuDitemukan = true;
                menuDitemukanObj = menu;
                break;
            }
        }

        if (!menuDitemukan) {
            System.out.println("Menu tidak ditemukan!");
        }

        return menuDitemukanObj;
    }

    private static int tentukanJumlahPesanan(Scanner input) {
        System.out.print("Masukkan jumlah pesanan: ");
        return Integer.parseInt(input.nextLine());
    }

    private static double hitungSubtotal(Menu menu, int jumlahPesanan) {
        return menu.getHarga() * jumlahPesanan;
    }
}
