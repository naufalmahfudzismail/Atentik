package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Muhammad63 on 3/19/2018.
 */

public class object_kompen_terbanyak {

    @SerializedName("id_mhsw")
    private String id_mhsw;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nama_kelas")
    private String nama_kelas;
    @SerializedName("kompen")
    private String kompen;
    @SerializedName("biaya")
    private String biaya;
    @SerializedName("status_sp")
    private String status_sp;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_mhsw() {
        return id_mhsw;
    }

    public void setId_mhsw(String id_mhsw) {
        this.id_mhsw = id_mhsw;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getKompen() {
        return kompen;
    }

    public void setKompen(String kompen) {
        this.kompen = kompen;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getStatus_sp() {
        return status_sp;
    }

    public void setStatus_sp(String status_sp) {
        this.status_sp = status_sp;
    }

    private String urutan, kelas, jumlahKompen, statusSP;
    int ava;

    public object_kompen_terbanyak(String urutan, String nama, String kelas, String jumlahKompen, String statusSP, String photo) {
        this.urutan = urutan;
        this.nama = nama;
        this.kelas = kelas;
        this.jumlahKompen = jumlahKompen;
        this.statusSP = statusSP;
        this.photo = photo;
    }

    public String getUrutan() {
        return urutan;
    }

    public void setUrutan(String urutan) {
        this.urutan = urutan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJumlahKompen() {
        return jumlahKompen;
    }

    public void setJumlahKompen(String jumlahKompen) {
        this.jumlahKompen = jumlahKompen;
    }

    public String getStatusSP() {
        return statusSP;
    }

    public void setStatusSP(String statusSP) {
        this.statusSP = statusSP;
    }

    public int getAva() {
        return ava;
    }

    public void setAva(int ava) {
        this.ava = ava;
    }
}
