package RideSharing.Managers;

import java.util.HashMap;

import RideSharing.Enum.RideStatus;
import RideSharing.Models.Driver;
import RideSharing.Models.Location;
import RideSharing.Models.Ride;
import RideSharing.Models.Rider;

public class RideManager {
    private HashMap<Integer, Ride> rideMapping;
    
    private DriverManager driverManager;
    private RiderManager riderManager;

    public RideManager() {
        driverManager = new DriverManager();
        riderManager = new RiderManager();
        rideMapping = new HashMap<>();
    }

    public Ride createNewRide(Rider rider, Location pickupLocation, Location dropLocation) {
        Ride ride = new Ride(pickupLocation, dropLocation, rider);
        rideMapping.put(ride.getRideId(), ride);

        Driver driver = driverManager.findDriver();
        ride.setDriver(driver);
        return ride;
    }

    public void updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.updateRideStatus(rideStatus);
        return;
    }

    public void addRiderRating(Ride ride, int rating) {
        riderManager.addRiderRating(ride.getRider(), rating);
    }

    public void addDriverRating(Ride ride, int rating) {
        driverManager.addDriverRating(ride.getDriver(), rating);
    }
}
