package id.tiregdev.atentik.Model;

/**
 * Created by HVS on 14/03/18.
 */

public class object_cubeacon {

    private String ruangan, nama_cubeacon, proximity, accuracy;

    public object_cubeacon(String ruangan, String nama_cubeacon, String proximity) {
        this.ruangan = ruangan;
        this.nama_cubeacon  = nama_cubeacon;
        this.proximity = proximity;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getName() {
        return nama_cubeacon;
    }

    public void setName(String name) {
        this.nama_cubeacon = nama_cubeacon;
    }

    public String getProximity() {
        return proximity;
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }
}
