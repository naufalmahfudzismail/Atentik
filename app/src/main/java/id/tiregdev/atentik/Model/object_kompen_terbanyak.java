package id.tiregdev.atentik.Model;

/**
 * Created by Muhammad63 on 3/19/2018.
 */

public class object_kompen_terbanyak {

    private String urutan, nama, kelas, jumlahKompen, statusSP;
    int ava;

    public object_kompen_terbanyak(String urutan, String nama, String kelas, String jumlahKompen, String statusSP, int ava) {
        this.urutan = urutan;
        this.nama = nama;
        this.kelas = kelas;
        this.jumlahKompen = jumlahKompen;
        this.statusSP = statusSP;
        this.ava = ava;
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
