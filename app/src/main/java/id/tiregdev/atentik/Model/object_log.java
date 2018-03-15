package id.tiregdev.atentik.Model;

/**
 * Created by HVS on 14/03/18.
 */

public class object_log {

    private String matkul, ruang, jamHadir, telat;

    public object_log(String matkul, String ruang, String jamHadir, String telat) {
        this.matkul = matkul;
        this.ruang = ruang;
        this.jamHadir = jamHadir;
        this.telat = telat;
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
}
