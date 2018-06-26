package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HVS on 14/03/18.
 */

public class object_log {

    @SerializedName("jam_hadir")
    private String jam_hadir;
    @SerializedName("waktu_telat")
    private String waktu_telat;
    @SerializedName("kompen")
    private String kompen;
    @SerializedName("nama_matkul")
    private String nama_matkul;
    @SerializedName("ruangan")
    private String ruangan;
    @SerializedName("id_mhsw")
    private String id_mhsw;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("status")
    private String status;

    public String getId_mhsw() {
        return id_mhsw;
    }

    public void setId_mhsw(String id_mhsw) {
        this.id_mhsw = id_mhsw;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJam_hadir() {
        return jam_hadir;
    }

    public void setJam_hadir(String jam_hadir) {
        this.jam_hadir = jam_hadir;
    }

    public String getWaktu_telat() {
        return waktu_telat;
    }

    public void setWaktu_telat(String waktu_telat) {
        this.waktu_telat = waktu_telat;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    private String matkul, ruang, jamHadir, telat;

    public object_log(String matkul, String ruang, String jamHadir, String telat, String kompen) {
        this.matkul = matkul;
        this.ruang = ruang;
        this.jamHadir = jamHadir;
        this.telat = telat;
        this.kompen = kompen;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJamHadir() {
        return jamHadir;
    }

    public void setJamHadir(String jamHadir) {
        this.jamHadir = jamHadir;
    }

    public String getTelat() {
        return telat;
    }

    public void setTelat(String telat) {
        this.telat = telat;
    }

    public String getKompen() {
        return kompen;
    }

    public void setKompen(String kompen) {
        this.kompen = kompen;
    }
}
