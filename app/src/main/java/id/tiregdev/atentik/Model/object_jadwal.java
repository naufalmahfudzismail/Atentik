package id.tiregdev.atentik.Model;

/**
 * Created by Muhammad63 on 3/22/2018.
 */

public class object_jadwal {

    private String namaMatkul, namaDosenAtauNamaKelas, jam, jpm, ruangan;

    public object_jadwal(String namaMatkul, String namaDosenAtauNamaKelas, String jam, String jpm, String ruangan) {
        this.namaMatkul = namaMatkul;
        this.namaDosenAtauNamaKelas = namaDosenAtauNamaKelas;
        this.jam = jam;
        this.jpm = jpm;
        this.ruangan = ruangan;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getNamaDosenAtauNamaKelas() {
        return namaDosenAtauNamaKelas;
    }

    public void setNamaDosenAtauNamaKelas(String namaDosenAtauNamaKelas) {
        this.namaDosenAtauNamaKelas = namaDosenAtauNamaKelas;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getJpm() {
        return jpm;
    }

    public void setJpm(String jpm) {
        this.jpm = jpm;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}
