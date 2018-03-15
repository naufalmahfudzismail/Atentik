package id.tiregdev.atentik.Model;

/**
 * Created by HVS on 15/03/18.
 */

public class object_mhsw_dosen {

    private String nama, jabatanAatauKelas, nipAtauNim, emailAtauTlpn;

    public object_mhsw_dosen(String nama, String jabatanAatauKelas, String nipAtauNim, String emailAtauTlpn) {
        this.nama = nama;
        this.jabatanAatauKelas = jabatanAatauKelas;
        this.nipAtauNim = nipAtauNim;
        this.emailAtauTlpn = emailAtauTlpn;
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
}
