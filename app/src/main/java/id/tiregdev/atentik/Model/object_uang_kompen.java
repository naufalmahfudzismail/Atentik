package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_uang_kompen {
    @SerializedName("biaya")
    private int biaya;
    @SerializedName("tanggal")
    private String tanggal;

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
