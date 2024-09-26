# Nama Program        : soal2.py
# Nama Lengkap - NPM  :
# - Francisco Gilbert Sondakh - 140810230004
# - Dzaki Azhari - 140810230034
# Kelas               : B
# Tanggal Buat        : 26/09/2024
# Deskripsi           : Selisih Tanggal dan Waktu

class Waktu:    
    # Constructor tanpa parameter
    def __init__(self, hari=0, bulan=0, tahun=0, jam=0, menit=0, detik=0):
        self.__hari = hari
        self.__bulan = bulan
        self.__tahun = tahun
        self.__jam = jam
        self.__menit = menit
        self.__detik = detik

    # Mengecek apakah tahun kabisat
    def tahun_kabisat(self, tahun):
        return (tahun % 4 == 0 and tahun % 100 != 0) or (tahun % 400 == 0)

    # Mengonversi hari ke jumlah hari dalam bulan
    def konversi_hari_ke_bulan(self, bulan, tahun):
        hari_per_bulan = [31, 29 if self.tahun_kabisat(tahun) else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        return hari_per_bulan[bulan - 1]

    # Menghitung total hari sejak awal tahun
    def hitung_hari_sejak_awal_tahun(self):
        total_hari = self.__hari
        for i in range(1, self.__bulan):
            total_hari += self.konversi_hari_ke_bulan(i, self.__tahun)
        return total_hari

    # Menghitung selisih waktu keberangkatan dan kedatangan
    def hitung_selisih(self, akhir):
        total_hari_awal = self.hitung_hari_sejak_awal_tahun()
        total_hari_akhir = akhir.hitung_hari_sejak_awal_tahun()

        total_hari = 0
        if akhir.get_tahun() > self.__tahun:
            total_hari = (366 if self.tahun_kabisat(self.__tahun) else 365) - total_hari_awal
            for t in range(self.__tahun + 1, akhir.get_tahun()):
                total_hari += 366 if self.tahun_kabisat(t) else 365
            total_hari += total_hari_akhir
        else:
            total_hari = total_hari_akhir - total_hari_awal

        total_jam = akhir.get_jam() - self.__jam
        total_menit = akhir.get_menit() - self.__menit
        total_detik = akhir.get_detik() - self.__detik

        if total_detik < 0:
            total_detik += 60
            total_menit -= 1

        if total_menit < 0:
            total_menit += 60
            total_jam -= 1

        if total_jam < 0:
            total_jam += 24
            total_hari -= 1

        total_tahun = total_hari // 365
        total_hari %= 365

        total_bulan = 0
        hari_per_bulan = [31, 29 if akhir.tahun_kabisat(akhir.get_tahun()) else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        for hari_bulan in hari_per_bulan:
            if total_hari >= hari_bulan:
                total_hari -= hari_bulan
                total_bulan += 1
            else:
                break

        return Waktu(total_hari, total_bulan, total_tahun, total_jam, total_menit, total_detik)

    # Menampilkan selisih waktu
    def display_selisih(self):
        print(f"Lama Selisih: {self.__tahun} tahun, {self.__bulan} bulan, {self.__hari} hari, "
              f"{self.__jam} jam, {self.__menit} menit, {self.__detik} detik")

    # Getter untuk atribut private
    def get_hari(self):
        return self.__hari

    def get_bulan(self):
        return self.__bulan

    def get_tahun(self):
        return self.__tahun

    def get_jam(self):
        return self.__jam

    def get_menit(self):
        return self.__menit

    def get_detik(self):
        return self.__detik


def input_data(label):
    print(f"Input {label}:")
    hari = int(input("Tanggal     : "))
    bulan = int(input("Bulan       : "))
    tahun = int(input("Tahun       : "))
    jam = int(input("Jam         : "))
    menit = int(input("Menit       : "))
    detik = int(input("Detik       : "))
    return Waktu(hari, bulan, tahun, jam, menit, detik)


def main():
    print("Berangkat:")
    waktu_mulai = input_data("waktu keberangkatan")
    print("Kedatangan:")
    waktu_selesai = input_data("waktu kedatangan")
    selisih = waktu_mulai.hitung_selisih(waktu_selesai)
    selisih.display_selisih()


if __name__ == "__main__":
    main()