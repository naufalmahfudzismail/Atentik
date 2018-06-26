package id.tiregdev.atentik.Model;

public class object_tracking {

    private String nama, nimOrNip, posisi;

    public object_tracking(String nama, String nimOrNip, String posisi) {
        this.nama = nama;
        this.nimOrNip = nimOrNip;
        this.posisi = posisi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNimOrNip() {
        return nimOrNip;
    }

    public void setNimOrNip(String nimOrNip) {
        this.nimOrNip = nimOrNip;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}
