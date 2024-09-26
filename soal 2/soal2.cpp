/*
Nama Program        : soal2.java
Nama Lengkap - NPM  :
- Francisco Gilbert Sondakh - 140810230004
- Dzaki Azhari - 140810230034
Kelas               : B
Tanggal Buat        : 26/09/2024
Deskripsi           : Selisih Tanggal dan Waktu
*/

#include <iostream>
using namespace std;

class Waktu {
    //membuat data yang ingin disimpan dalam OOP berbentuk private
    private:
        int hari, bulan, tahun, jam, menit, detik;

    public:
        // membangun constructor dengan menggunakan parameter
        Waktu(int hari = 0, int bulan = 0, int tahun = 0, int jam = 0, int menit = 0, int detik = 0) {
            this->hari = hari;
            this->bulan = bulan;
            this->tahun = tahun;
            this->jam = jam;
            this->menit = menit;
            this->detik = detik;
        }

        // mengecek apakah suatu tahun merupakan tahun kabisat atau bukan
        bool tahunKabisat(int tahun) {
            return ((tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0));
        }

        //konversi hari ke bulan
        int konversiHarikeBulan(int bulan, int tahun) {
            int hariPerBulan[] = {31, (tahunKabisat(tahun) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            return hariPerBulan[bulan - 1];
        }

        int hitungHariSejakAwalTahun() {
            int totalHari = hari;
            for (int i = 1; i < bulan; i++) {
                totalHari += konversiHarikeBulan(i, tahun);
            }
            return totalHari;
        }

        // buat mennghitung selisih waktu keberangkatan dan kedatangan
        Waktu hitungSelisih(Waktu akhir) {
            int totalHariAwal = hitungHariSejakAwalTahun();
            int totalHariAkhir = akhir.hitungHariSejakAwalTahun();

            int totalHari = 0;
            if (akhir.tahun > tahun) {
                totalHari = (tahunKabisat(tahun) ? 366 : 365) - totalHariAwal;
                for (int t = tahun + 1; t < akhir.tahun; t++) {
                    totalHari += tahunKabisat(t) ? 366 : 365;
                }
                totalHari += totalHariAkhir;
            } else {
                totalHari = totalHariAkhir - totalHariAwal;
            }

            int totalJam = akhir.jam - jam;
            int totalMenit = akhir.menit - menit;
            int totalDetik = akhir.detik - detik;

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
            int hariPerBulan[] = {31, (akhir.tahunKabisat(akhir.tahun) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            for (int i = 0; i < 12; i++) {
                if (totalHari >= hariPerBulan[i]) {
                    totalHari -= hariPerBulan[i];
                    totalBulan++;
                } else {
                    break;
                }
            }

            return Waktu(totalHari, totalBulan, totalTahun, totalJam, totalMenit, totalDetik);
        }

        void printOut() {
            cout << "Lama Selisih: " << tahun << " tahun, " << bulan << " bulan, " << hari << " hari, " << jam << " jam, " << menit << " menit, " << detik << " detik" << endl;
        }
};

//buat input si datanya
Waktu inputData(string label) {
    int hari, bulan, tahun, jam, menit, detik;
    cout << label << endl;
    cout << "Tanggal     : "; cin >> hari;
    cout << "Bulan       : "; cin >> bulan;
    cout << "Tahun       : "; cin >> tahun;
    cout << "Jam         : "; cin >> jam;
    cout << "Menit       : "; cin >> menit;
    cout << "Detik       : "; cin >> detik;
    return Waktu(hari, bulan, tahun, jam, menit, detik);
}

int main() {
    cout << "Berangkat :" << endl;
    Waktu waktuMulai = inputData("Masukkan waktu keberangkatan");
    
    cout << "Kedatangan :" << endl;
    Waktu waktuSelesai = inputData("Masukkan waktu kedatangan");
    
    Waktu selisih = waktuMulai.hitungSelisih(waktuSelesai);
    selisih.printOut();
    
    return 0;
}