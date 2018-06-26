package id.tiregdev.atentik.Model;

import com.google.gson.annotations.SerializedName;

public class object_total {
    @SerializedName("total_masuk")
    private int total_masuk;
    @SerializedName("total_telat")
    private int total_telat;
    @SerializedName("total_izin")
    private double total_izin;
    @SerializedName("total_tidak_masuk")
    private double total_tidak_masuk;
    @SerializedName("total_sakit")
    private double total_sakit;
    @SerializedName("total_bekom")
    private double total_bekom;
    @SerializedName("biaya")
    private int biaya;
    @SerializedName("total_kompen")
    private double total_kompen;

    public double getTotal_izin() {
        return total_izin;
    }

    public void setTotal_izin(double total_izin) {
        this.total_izin = total_izin;
    }

    public double getTotal_tidak_masuk() {
        return total_tidak_masuk;
    }

    public void setTotal_tidak_masuk(double total_tidak_masuk) {
        this.total_tidak_masuk = total_tidak_masuk;
    }

    public double getTotal_sakit() {
        return total_sakit;
    }

    public void setTotal_sakit(double total_sakit) {
        this.total_sakit = total_sakit;
    }

    public double getTotal_bekom() {
        return total_bekom;
    }

    public void setTotal_bekom(double total_bekom) {
        this.total_bekom = total_bekom;
    }

    public int getTotal_masuk() {
        return total_masuk;
    }

    public void setTotal_masuk(int total_masuk) {
        this.total_masuk = total_masuk;
    }

    public int getTotal_telat() {
        return total_telat;
    }

    public void setTotal_telat(int total_telat) {
        this.total_telat = total_telat;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public double getTotal_kompen() {
        return total_kompen;
    }

    public void setTotal_kompen(double total_kompen) {
        this.total_kompen = total_kompen;
    }
}
