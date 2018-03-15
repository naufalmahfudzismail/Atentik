package id.tiregdev.atentik.Model;

/**
 * Created by HVS on 14/03/18.
 */

public class object_cubeacon {

    private String ruangan, cubeacon;

    public object_cubeacon(String ruangan, String cubeacon) {
        this.ruangan = ruangan;
        this.cubeacon = cubeacon;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getCubeacon() {
        return cubeacon;
    }

    public void setCubeacon(String cubeacon) {
        this.cubeacon = cubeacon;
    }
}
