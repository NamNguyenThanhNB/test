package vn.thanhnam.assignmentandroidnc.model;

public class Maps {
    private String nameAdr;
    private String longtitude;
    private String latitude;

    public Maps() {
    }

    public Maps(String nameAdr, String longtitude, String latitude) {
        this.nameAdr = nameAdr;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public String getNameAdr() {
        return nameAdr;
    }

    public void setNameAdr(String nameAdr) {
        this.nameAdr = nameAdr;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
