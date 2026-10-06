public class Main {
    public static void main(String[] args) {
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
    }

    private static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("Daftar Menu:");
        for (Menu menu : daftarMenu) {
            System.out.println(menu);
        }
    }
}
