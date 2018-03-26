package id.tiregdev.atentik.Model;

/**
 * Created by Muhammad63 on 3/25/2018.
 */

public class object_log_mahasiswa {

    private String nama, nim, jam, telat, kompen;

    public object_log_mahasiswa(String nama, String nim, String jam, String telat, String kompen) {
        this.nama = nama;
        this.nim = nim;
        this.jam = jam;
        this.telat = telat;
        this.kompen = kompen;
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

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
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
