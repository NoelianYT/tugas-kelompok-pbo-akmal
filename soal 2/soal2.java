/*
Nama Program        : soal2.java
Nama Lengkap - NPM  :
- Francisco Gilbert Sondakh - 140810230004
- Dzaki Azhari - 140810230034
Kelas               : B
Tanggal Buat        : 26/09/2024
Deskripsi           : Selisih Tanggal dan Waktu
*/

import java.util.Scanner;

class Waktu {
    private int hari, bulan, tahun, jam, menit, detik;

    //constructor buat waktu tapi tidak memakai parameter
    public Waktu() {
        this.hari = 0;
        this.bulan = 0;
        this.tahun = 0;
        this.jam = 0;
        this.menit = 0;
        this.detik = 0;
    }

    //constructor buat waktu pakai parameter
    public Waktu(int hari, int bulan, int tahun, int jam, int menit, int detik) {
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jam = jam;
        this.menit = menit;
        this.detik = detik;
    }

    //buat ngecek tahun kabisat
    public boolean tahunKabisat(int tahun) {
        return ((tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0));
    }

    //konversi hari ke bulan
    public int konversiHarikeBulan(int bulan, int tahun) {
        int[] hariPerBulan = {31, tahunKabisat(tahun) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return hariPerBulan[bulan - 1];
    }

    public int hitungHariSejakAwalTahun() {
        int totalHari = this.hari;
        for (int i = 1; i < this.bulan; i++) {
            totalHari += konversiHarikeBulan(i, this.tahun);
        }
        return totalHari;
    }

    // buat mennghitung selisih waktu keberangkatan dan kedatangan
    public Waktu hitungSelisih(Waktu akhir) {
        int totalHariAwal = hitungHariSejakAwalTahun();
        int totalHariAkhir = akhir.hitungHariSejakAwalTahun();
        
        int totalHari = 0;
        if (akhir.tahun > this.tahun) {
            totalHari = (tahunKabisat(this.tahun) ? 366 : 365) - totalHariAwal;
            for (int t = this.tahun + 1; t < akhir.tahun; t++) {
                totalHari += tahunKabisat(t) ? 366 : 365;
            }
            totalHari += totalHariAkhir;
        } else {
            totalHari = totalHariAkhir - totalHariAwal;
        }
        int totalJam = akhir.jam - this.jam;
        int totalMenit = akhir.menit - this.menit;
        int totalDetik = akhir.detik - this.detik;

        if (totalDetik < 0) {
            totalDetik += 60;
            totalMenit--;
        }

        if (totalMenit < 0) {
            totalMenit += 60;
            totalJam--;
        }

        if (totalJam < 0) {
            totalJam += 24;
            totalHari--;
        }

        int totalTahun = totalHari / 365;
        totalHari %= 365;

        int totalBulan = 0;
        int[] hariPerBulan = {31, akhir.tahunKabisat(akhir.tahun) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < hariPerBulan.length; i++) {
            if (totalHari >= hariPerBulan[i]) {
                totalHari -= hariPerBulan[i];
                totalBulan++;
            } else {
                break;
            }
        }

        return new Waktu(totalHari, totalBulan, totalTahun, totalJam, totalMenit, totalDetik);
    }

    public void printOut() {
        System.out.println("Lama Selisih: " + this.tahun + " tahun, " + this.bulan + " bulan, " +
                this.hari + " hari, " + this.jam + " jam, " + this.menit + " menit, " + this.detik + " detik");
    }
}

public class soal2 {
    //buat input si datanya
    public static Waktu inputData(String label) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tanggal     :");
        int hari = scanner.nextInt();
        System.out.println("Bulan       :");
        int bulan = scanner.nextInt();
        System.out.println("Tahun       :");
        int tahun = scanner.nextInt();
        System.out.println("Jam         :");
        int jam = scanner.nextInt();
        System.out.println("Menit       :");
        int menit = scanner.nextInt();
        System.out.println("Detik       :");
        int detik = scanner.nextInt();

        return new Waktu(hari, bulan, tahun, jam, menit, detik);
    }

    public static void main(String[] args) {
        System.out.println("Berangkat :");
        Waktu waktuMulai = inputData("");
        System.out.println("Kedatangan :");
        Waktu waktuSelesai = inputData("");
        Waktu selisih = waktuMulai.hitungSelisih(waktuSelesai);
        selisih.printOut();
    }
}