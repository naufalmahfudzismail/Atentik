package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HVS on 15/03/18.
 */

public class object_mhsw_dosen {

    @SerializedName("nama")
    private String nama;
    @SerializedName("nama_kelas")
    private String nama_kelas;
    @SerializedName("nim")
    private String nim;
    @SerializedName("status_dosen")
    private String status_dosen;
    @SerializedName("nip")
    private String nip;
    @SerializedName("nidn")
    private String nidn;
    @SerializedName("tlp")
    private String tlp;
    @SerializedName("email")
    private String email;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("kompen")
    private String kompen;
    @SerializedName("status_sp")
    private String status_sp;


    private String jabatanAatauKelas, nipAtauNim, emailAtauTlpn, jumlahKompenAtauStatusDosen, statusSPatauEmailDosen;
    private int ava;

    public object_mhsw_dosen(String nama, String jabatanAatauKelas, String nipAtauNim, String emailAtauTlpn, String jumlahKompenAtauStatusDosen, String statusSPatauEmailDosen, int ava) {
        this.nama = nama;
        this.jabatanAatauKelas = jabatanAatauKelas;
        this.nipAtauNim = nipAtauNim;
        this.emailAtauTlpn = emailAtauTlpn;
        this.jumlahKompenAtauStatusDosen = jumlahKompenAtauStatusDosen;
        this.statusSPatauEmailDosen = statusSPatauEmailDosen;
        this.ava = ava;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getKompen() {
        return kompen;
    }

    public void setKompen(String kompen) {
        this.kompen = kompen;
    }

    public String getStatus_sp() {
        return status_sp;
    }

    public void setStatus_sp(String status_sp) {
        this.status_sp = status_sp;
    }

    public String getJumlahKompenAtauStatusDosen() {
        return jumlahKompenAtauStatusDosen;
    }

    public void setJumlahKompenAtauStatusDosen(String jumlahKompenAtauStatusDosen) {
        this.jumlahKompenAtauStatusDosen = jumlahKompenAtauStatusDosen;
    }

    public String getStatusSPatauEmailDosen() {
        return statusSPatauEmailDosen;
    }

    public void setStatusSPatauEmailDosen(String statusSPatauEmailDosen) {
        this.statusSPatauEmailDosen = statusSPatauEmailDosen;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getStatus_dosen() {
        return status_dosen;
    }

    public void setStatus_dosen(String status_dosen) {
        this.status_dosen = status_dosen;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTlp() {
        return tlp;
    }

    public void setTlp(String tlp) {
        this.tlp = tlp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatanAatauKelas() {
        return jabatanAatauKelas;
    }

    public void setJabatanAatauKelas(String jabatanAatauKelas) {
        this.jabatanAatauKelas = jabatanAatauKelas;
    }

    public String getNipAtauNim() {
        return nipAtauNim;
    }

    public void setNipAtauNim(String nipAtauNim) {
        this.nipAtauNim = nipAtauNim;
    }

    public String getEmailAtauTlpn() {
        return emailAtauTlpn;
    }

    public void setEmailAtauTlpn(String emailAtauTlpn) {
        this.emailAtauTlpn = emailAtauTlpn;
    }

    public int getAva() {
        return ava;
    }

    public void setAva(int ava) {
        this.ava = ava;
    }
}
