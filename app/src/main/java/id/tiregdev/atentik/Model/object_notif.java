package id.tiregdev.atentik.Model;

/**
 * Created by HVS on 15/03/18.
 */

public class object_notif {

    private String judul, isi, waktu;

    public object_notif(String judul, String isi, String waktu) {
        this.judul = judul;
        this.isi = isi;
        this.waktu = waktu;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
