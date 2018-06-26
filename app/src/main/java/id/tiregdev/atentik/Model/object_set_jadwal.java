package id.tiregdev.atentik.Model;

public class object_set_jadwal {

    private String namaMatkul, namaDosen, namaUpdate, waktuUpdate;

    public object_set_jadwal(String namaMatkul, String namaDosen, String namaUpdate, String waktuUpdate) {
        this.namaMatkul = namaMatkul;
        this.namaDosen = namaDosen;
        this.namaUpdate = namaUpdate;
        this.waktuUpdate = waktuUpdate;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getNamaUpdate() {
        return namaUpdate;
    }

    public void setNamaUpdate(String namaUpdate) {
        this.namaUpdate = namaUpdate;
    }

    public String getWaktuUpdate() {
        return waktuUpdate;
    }

    public void setWaktuUpdate(String waktuUpdate) {
        this.waktuUpdate = waktuUpdate;
    }
}
