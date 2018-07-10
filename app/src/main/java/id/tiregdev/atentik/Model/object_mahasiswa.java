package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_mahasiswa {
    @SerializedName("id_mhsw")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nim")
    private String nim;
    @SerializedName("password")
    private String pass;
    @SerializedName("confirmpassword")
    private String confirmpass;
    @SerializedName("email")
    private String email;
    @SerializedName("tlp")
    private String tlp;
    @SerializedName("imei_hp")
    private String imei_hp;
    @SerializedName("teks")
    private String teks;
    @SerializedName("status_sp")
    private String status_sp;
    @SerializedName("kelas")
    private String kelas;
    @SerializedName("token")
    private String token;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTeks() {
        return teks;
    }

    public void setTeks(String teks) {
        this.teks = teks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getImei_hp() {
        return imei_hp;
    }

    public void setImei_hp(String imei_hp) {
        this.imei_hp = imei_hp;
    }

    public String getStatus_sp() {
        return status_sp;
    }

    public void setStatus_sp(String status_sp) {
        this.status_sp = status_sp;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
}
