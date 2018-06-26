package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_set_jadwal_masuk {
    @SerializedName("id_matkul")
    private String id_matkul;
    @SerializedName("pilihan")
    private String pilihan;
    @SerializedName("waktu_masuk")
    private String waktu_masuk;
    @SerializedName("waktu_diubah")
    private String waktu_diubah;
    @SerializedName("pengubah_jadwal")
    private String pengubah_jadwal;
    @SerializedName("nama_matkul")
    private String nama_matkul;
    @SerializedName("nama")
    private String nama;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("hari")
    private String hari;
    @SerializedName("pesan")
    private String pesan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public object_set_jadwal_masuk(String pilihan, String waktu_masuk, String waktu_diubah, String pengubah_jadwal, String nama_matkul, String nama) {
        this.pilihan = pilihan;
        this.waktu_masuk = waktu_masuk;
        this.waktu_diubah = waktu_diubah;
        this.pengubah_jadwal = pengubah_jadwal;
        this.nama_matkul = nama_matkul;
        this.nama = nama;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_matkul() {
        return id_matkul;
    }

    public void setId_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getWaktu_masuk() {
        return waktu_masuk;
    }

    public void setWaktu_masuk(String waktu_masuk) {
        this.waktu_masuk = waktu_masuk;
    }

    public String getWaktu_diubah() {
        return waktu_diubah;
    }

    public void setWaktu_diubah(String waktu_diubah) {
        this.waktu_diubah = waktu_diubah;
    }

    public String getPengubah_jadwal() {
        return pengubah_jadwal;
    }

    public void setPengubah_jadwal(String pengubah_jadwal) {
        this.pengubah_jadwal = pengubah_jadwal;
    }
}
