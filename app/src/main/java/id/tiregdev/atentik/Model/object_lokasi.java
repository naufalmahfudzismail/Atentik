package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_lokasi {
    @SerializedName("id_mhsw")
    private String id_mhsw;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("nip")
    private String nip;
    @SerializedName("ruangan")
    private String ruangan;
    @SerializedName("waktu")
    private String waktu;
    @SerializedName("nimornip")
    private String nimornip;
    @SerializedName("posisi")
    private String posisi;
    @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public object_lokasi(String nama, String nimornip, String posisi, String photo) {
        this.nama = nama;
        this.nimornip = nimornip;
        this.posisi = posisi;
        this.photo = photo;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getNimornip() {
        return nimornip;
    }

    public void setNimornip(String nimornip) {
        this.nimornip = nimornip;
    }

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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getruangan() {
        return ruangan;
    }

    public void setruangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
