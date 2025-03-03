package RideSharing.Models;

public class Location {
    private int latitute;
    private int longitude;

    public Location(int longitude, int latitute) {
        this.longitude = longitude;
        this.latitute = latitute;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitute;
    }
}
