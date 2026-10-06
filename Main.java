import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Menyimpan daftar menu restoran
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

        // 2. Menampilkan daftar menu
        tampilkanMenu(daftarMenu);

        // 3. Menyediakan array untuk menyimpan maksimal 4 pesanan
        Pesanan[] daftarPesanan = new Pesanan[4];

        int jumlahPesanan = 0;
        String lanjut;

        // 4. Proses pemesanan
        do {
            System.out.println("\nPesanan ke-" + jumlahPesanan);

            // Memasukkan nama menu
            String namaPesanan = masukkanPesanan(input);

            // Mencari menu
            Menu menuTerpilih = mencariPesanan(daftarMenu, namaPesanan);

            if (menuTerpilih != null) {
                // Menampilkan informasi menu yang dipilih
                System.out.println("-----------------------------------");
                System.out.println("Menu ditemukan!");
                System.out.println("Nama     : " + menuTerpilih.getNama());
                System.out.println("Harga    : Rp." + menuTerpilih.getHarga());
                System.out.println("Kategori : " + menuTerpilih.getKategori());

                // Memasukkan jumlah
                int jumlah = tentukanJumlahPesanan(input);

                // Membuat object Pesanan
                Pesanan pesananBaru = new Pesanan(menuTerpilih, jumlah);

                // Menyimpan pesanan ke array
                daftarPesanan[jumlahPesanan] = pesananBaru;
                jumlahPesanan++;

                // Menampilkan subtotal
                System.out.println("Jumlah   : " + jumlah);
                System.out.println("Subtotal : Rp." + pesananBaru.getSubtotal());

            } else {
                System.out.println("Pesanan tidak dapat diproses karena menu tidak ditemukan.");
            }

            // Menanyakan apakah ingin menambah pesanan
            if (jumlahPesanan < 4) {
                System.out.print("\nApakah ingin memesan lagi? (y/n): ");
                lanjut = input.nextLine();

            } else {
                lanjut = "n";
                System.out.println("\nMaksimal 4 menu telah dipesan.");
            }

        } while (lanjut.equalsIgnoreCase("y"));

        // 5. Menampilkan daftar pesanan
        System.out.println("\n===================================");
        System.out.println("           DAFTAR PESANAN");
        System.out.println("===================================");

        for (int i = 0; i < jumlahPesanan; i++) {

            Pesanan pesanan = daftarPesanan[i];

            if (pesanan != null) {
                System.out.println(
                        pesanan.getMenu().getNama()
                                + " - "
                                + pesanan.getJumlah()
                                + " x Rp."
                                + pesanan.getMenu().getHarga()
                                + " = Rp."
                                + pesanan.getSubtotal());
            }
        }
        
        // 6. Menghitung total pesanan
        double totalPesanan = hitungTotalPesanan(daftarPesanan, jumlahPesanan);
        double diskon = hitungDiskon(totalPesanan);
        double totalSetelahDiskon = totalPesanan - diskon;
        double totalSetelahPromo = hitungTotalSetelahPromo(daftarPesanan, jumlahPesanan, totalSetelahDiskon,
                totalPesanan);
        double pajak = hitungPajak(totalSetelahPromo);
        double serviceCharge = hitungServiceCharge();
        double totalBayar = hitungTotalBayar(totalSetelahPromo, pajak, serviceCharge);

        System.out.println("-----------------------------------");
        System.out.println("Total Pesanan       : Rp." + totalPesanan);
        System.out.println("Diskon              : Rp." + diskon);
        System.out.println("Total Setelah Diskon: Rp." + totalSetelahDiskon);
        System.out.println("Total Setelah Promo : Rp." + totalSetelahPromo);
        System.out.println("Pajak 10%           : Rp." + pajak);
        System.out.println("Service Charge      : Rp." + serviceCharge);
        System.out.println("===================================");
        System.out.println("Total Bayar         : Rp." + totalBayar);

        input.close();
    }

    private static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("===================================");
        System.out.println("          DAFTAR MENU RESTORAN");
        System.out.println("===================================");

        for (Menu menu : daftarMenu) {
            System.out.println(menu);
        }

        System.out.println("===================================");
    }

    private static String masukkanPesanan(Scanner input) {
        System.out.print(
                "Masukkan nama menu yang akan dipesan: ");

        return input.nextLine();
    }

    private static Menu mencariPesanan(
            Menu[] daftarMenu,
            String pesanan) {

        for (Menu menu : daftarMenu) {

            if (menu.getNama().equalsIgnoreCase(pesanan)) {
                return menu;
            }
        }

        System.out.println("Menu tidak ditemukan!");

        return null;
    }

    private static int tentukanJumlahPesanan(Scanner input) {
        System.out.print("Masukkan jumlah pesanan: ");

        return Integer.parseInt(input.nextLine());
    }

    private static double hitungTotalPesanan(
            Pesanan[] daftarPesanan,
            int jumlahPesanan) {

        double total = 0;

        for (int i = 0; i < jumlahPesanan; i++) {

            if (daftarPesanan[i] != null) {
                total += daftarPesanan[i].getSubtotal();
            }
        }

        return total;
    }

    private static double hitungDiskon(double totalPesanan) {
        if (totalPesanan > 100000) {
            return totalPesanan * 0.10;
        }

        return 0;
    }

    private static Pesanan cariPesananMinuman(Pesanan[] daftarPesanan, int jumlahPesanan) {
        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan pesanan = daftarPesanan[i];
            if (pesanan != null
                    && pesanan.getMenu()
                            .getKategori()
                            .equalsIgnoreCase("Minuman")) {
                return pesanan;
            }
        }

        return null;
    }

    private static double hitungPromoB1G1(Pesanan pesanan, double totalPesanan) {

        if (totalPesanan > 50000 && pesanan != null) {
            int jumlah = pesanan.getJumlah();
            int jumlahGratis = jumlah / 2;
            int jumlahBayar = jumlah - jumlahGratis;
            return jumlahBayar * pesanan.getMenu().getHarga();
        }

        return 0;
    }

    private static double hitungTotalSetelahPromo(Pesanan[] daftarPesanan, int jumlahPesanan, double totalSetelahDiskon,
            double totalPesanan) {

        Pesanan pesananPromo = cariPesananMinuman(daftarPesanan, jumlahPesanan);

        if (totalPesanan > 50000 && pesananPromo != null) {
            double subtotalMinuman = pesananPromo.getSubtotal();
            double totalMinumanSetelahPromo = hitungPromoB1G1(pesananPromo, totalPesanan);
            double potonganPromo = subtotalMinuman - totalMinumanSetelahPromo;
            return totalSetelahDiskon - potonganPromo;
        }

        return totalSetelahDiskon;
    }

    private static double hitungPajak(double totalSetelahPromo) {
        return totalSetelahPromo * 0.10;
    }

    private static double hitungServiceCharge() {
        return 20000;
    }

    private static double hitungTotalBayar(double totalSetelahPromo, double pajak, double serviceCharge) {
        return totalSetelahPromo + pajak + serviceCharge;
    }
}