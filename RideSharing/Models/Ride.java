package RideSharing.Models;

import RideSharing.Enum.RideStatus;

public class Ride {
    private static int rideCount = 0;

    private int rideID;
    private Location pickLocation;
    private Location dropLocation;
    private Rider rider;
    private Driver driver;
    private int totalFare;
    private RideStatus rideStatus;

    public Ride(Location pickLocation, Location dropLocation, Rider rider) {
        this.rideID = rideCount;
        this.pickLocation = pickLocation;
        this.dropLocation = dropLocation;
        this.rider = rider;
        this.totalFare = 0;
        this.rideStatus = RideStatus.INIT;

        Ride.rideCount += 1;
    }

    public int getRideId() {
        return this.rideID;
    }

    public void updateRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
   
    public RideStatus getRideStatus() {
        return this.rideStatus;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    }

    public int getTotalFare() {
        return this.totalFare;
    }

    public int getTotalDistance() {
        return (Math.abs(this.dropLocation.getLongitude() - this.pickLocation.getLongitude()) + Math.abs(this.dropLocation.getLatitude() - this.pickLocation.getLatitude()));
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Rider getRider() {
        return this.rider;
    }

    @Override
    public String toString() {
        return this.rideID + " completed";
    }
}
