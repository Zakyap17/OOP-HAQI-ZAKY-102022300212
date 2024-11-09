public class Main {
    public static void main(String[] args) {
        System.out.println("Detail Hewan\n");

        
        Kucing kucing = new Kucing("Momo", 2, "Persia");
        System.out.println("ini adalah " + kucing.nama + " !");
        kucing.suara();
        kucing.makan();
        kucing.makan("ikan");
        kucing.infoHewan();

        System.out.println();

        
        Burung burung = new Burung("Rio", 1, "Hijau");
        System.out.println("ini adalah " + burung.nama + " !");
        burung.suara();
        burung.makan();
        burung.makan("biji-bijian");
        burung.infoHewan();
    }
}