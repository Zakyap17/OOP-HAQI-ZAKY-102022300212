import java.util.ArrayList;
import java.util.Scanner;


public class Pembelian {
    private static ArrayList<Penerbangan> daftarPenerbangan = new ArrayList<>();
    private static ArrayList<Penumpang> daftarPenumpang = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihanMenu = 0;

        
        daftarPenerbangan.add(new Penerbangan("GA010", "Jakarta", "DPS, Bali", "08:15", "10:15", 1290000));
        daftarPenerbangan.add(new Penerbangan("SJ020", "Surabaya", "HKD, Medan", "09:15", "11:45", 1320000));

        while (pilihanMenu != 4) {
            System.out.println("========== EAD Ticket Booking System ==========");
            System.out.println("1. Tampilkan Daftar Penerbangan");
            System.out.println("2. Beli Tiket");
            System.out.println("3. Tampilkan Pesanan Tiket");
            System.out.println("4. Exit");
            System.out.print("Silahkan pilih menu: ");
            pilihanMenu = scanner.nextInt();

            switch (pilihanMenu) {
                case 1:
                    System.out.println("\nDaftar Penerbangan Tersedia:");
                    tampilkanDaftarPenerbangan();
                    break;

                case 2:
                    System.out.print("Masukkan nomor penerbangan yang ingin dipesan: ");
                    int pilihanPenerbangan = scanner.nextInt();

                    if (pilihanPenerbangan >= 0 && pilihanPenerbangan < daftarPenerbangan.size()) {
                        Penerbangan penerbanganTerpilih = daftarPenerbangan.get(pilihanPenerbangan);

                        System.out.print("Masukkan NIK Penumpang: ");
                        String NIK = scanner.next();
                        System.out.print("Masukkan Nama Depan Penumpang: ");
                        String namaDepan = scanner.next();
                        System.out.print("Masukkan Nama Belakang Penumpang: ");
                        String namaBelakang = scanner.next();

                       
                        Penumpang penumpangBaru = new Penumpang(NIK, namaDepan, namaBelakang);
                        daftarPenumpang.add(penumpangBaru);

                        System.out.println("\nTerima kasih telah mengisi data penumpang.");
                        System.out.println("Berikut adalah tiket penerbangan yang tersedia:");

                        penerbanganTerpilih.tampilDaftarPenerbangan();
                    } else {
                        System.out.println("Penerbangan tidak tersedia.");
                    }
                    break;

                case 3:
                    if (daftarPenumpang.isEmpty()) {
                        System.out.println("Pembelian Tiket Tidak Ada");
                    } else {
                        System.out.println("\n========== Detail Tiket Penerbangan ==========");
                        for (Penumpang penumpang : daftarPenumpang) {
                            penumpang.tampilDaftarPenumpang();
                        }
                        for (Penerbangan penerbangan : daftarPenerbangan) {
                            penerbangan.tampilDaftarPenerbangan();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }


    private static void tampilkanDaftarPenerbangan() {
        for (int i = 0; i < daftarPenerbangan.size(); i++) {
            System.out.println(i + ". " + daftarPenerbangan.get(i).getNomorPenerbangan() + " - " +
                    daftarPenerbangan.get(i).getBandaraKeberangkatan() + " ke " +
                    daftarPenerbangan.get(i).getBandaraTujuan());
        }
        System.out.println();
    }
}