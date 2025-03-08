package RideSharing.Managers;

import java.util.HashMap;
import java.util.List;

import RideSharing.Enum.RideStatus;
import RideSharing.Models.Driver;
import RideSharing.Models.Location;
import RideSharing.Models.Ride;
import RideSharing.Models.Rider;

public class RideManager {
    private HashMap<Integer, Ride> rideMapping;
    private static RideManager instance;

    private DriverManager driverManager;
    private RiderManager riderManager;

    private RideManager() {
        driverManager = DriverManager.getInstance();
        riderManager = RiderManager.getInstance();
        rideMapping = new HashMap<>();
    }

    public static RideManager getInstance() {
        if (instance == null) {
            synchronized (RideManager.class) {
                if (instance == null) {
                    instance = new RideManager();
                }
            }
        }
        return instance;
    }

    public Ride createNewRide(Rider rider, Location pickupLocation, Location dropLocation) {
        Ride ride = new Ride(pickupLocation, dropLocation, rider);
        rideMapping.put(ride.getRideId(), ride);

        driverManager.printAllDriver();

        Driver driver = driverManager.findDriver();
        if (driver != null) {
            ride.setDriver(driver);
        }
        return ride;
    }

    public void updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.updateRideStatus(rideStatus);
    }

    public void addRiderRating(Ride ride, int rating) {
        riderManager.addRiderRating(ride.getRider(), rating);
    }

    public void addDriverRating(Ride ride, int rating) {
        driverManager.addDriverRating(ride.getDriver(), rating);
    }

    public void printRide(Ride ride) {
        System.out.println(ride.getRideId() + " " + ride.getDriver().getUsername() + " " + ride.getRider().getUsername() + " statue " + ride.getRideStatus());
    }
}
