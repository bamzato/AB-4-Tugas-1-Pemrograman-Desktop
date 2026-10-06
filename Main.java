public class Main {
    public static void main(String[] args) {
        Menu menu1 = new Menu("Nasi Goreng", 15000, "Makanan");
        Menu menu2 = new Menu("Es Teh", 5000, "Minuman");
        Menu menu3 = new Menu("Es Jeruk", 5000, "Minuman");

        System.out.println(menu1.toString());
        System.out.println(menu2.toString());
        System.out.println(menu3.toString());
    }
}
