package id.tiregdev.atentik.Model;

/**
 * Created by Muhammad63 on 3/22/2018.
 */

public class object_jadwal {

    private String nama_matkul;
    private String jam_mulai;
    private String jam_selesai;
    private String hari;
    private String nama_dosen;

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    private String namaMatkul;
    private String namaDosenAtauNamaKelas;
    private String jam;
    private String jpm;
    private String ruangan;

    public object_jadwal(String namaMatkul, String namaDosenAtauNamaKelas, String jam, String ruangan, String hari) {
        this.namaMatkul = namaMatkul;
        this.namaDosenAtauNamaKelas = namaDosenAtauNamaKelas;
        this.jam = jam;
//        this.jpm = jpm;
        this.ruangan = ruangan;
        this.hari = hari;
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
