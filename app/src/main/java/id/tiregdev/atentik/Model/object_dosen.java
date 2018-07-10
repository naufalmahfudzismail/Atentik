package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_dosen {
    @SerializedName("id_dosen")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nip")
    private String nip;
    @SerializedName("nidn")
    private String nidn;
    @SerializedName("password")
    private String pass;
    @SerializedName("confirmpassword")
    private String confirmpass;
    @SerializedName("email")
    private String email;
    @SerializedName("status_dosen")
    private String status_dosen;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("tlp")
    private String tlp;
    @SerializedName("token")
    private String token;
    @SerializedName("teks")
    private String teks;
    @SerializedName("imei_hp")
    private String imei_hp;
    @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getImei_hp() {
        return imei_hp;
    }

    public void setImei_hp(String imei_hp) {
        this.imei_hp = imei_hp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus_dosen() {
        return status_dosen;
    }

    public void setStatus_dosen(String status_dosen) {
        this.status_dosen = status_dosen;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }
}
