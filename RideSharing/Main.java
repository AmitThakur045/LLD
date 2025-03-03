package RideSharing;

import RideSharing.Managers.DriverManager;
import RideSharing.Managers.RideManager;
import RideSharing.Managers.RiderManager;
import RideSharing.Models.Driver;
import RideSharing.Models.Location;
import RideSharing.Models.Ride;
import RideSharing.Models.Rider;

public class Main {
    public static void main(String[] args) {
        // write test cases here to check the implementation of ride sharing system

        RideManager rideManager = new RideManager();
        DriverManager driverManager = new DriverManager();
        RiderManager riderManager = new RiderManager();

        Location start = new Location(0, 0);
        Location end = new Location(10, 10);
        Location end2 = new Location(20, 10);

        Rider rider1 = new Rider("rider1");
        Rider rider2 = new Rider("rider2");
        
        riderManager.addRider(rider1);
        riderManager.addRider(rider1);

        Driver driver1 = new Driver("driver1");
        Driver driver2 = new Driver("driver2");

        driverManager.addDriver(driver1);
        driverManager.addDriver(driver2);
        
        Ride ride1 = rideManager.createNewRide(rider2, start, end);
        Ride ride2 = rideManager.createNewRide(rider1, start, end2);
        
        Driver driverMappedWithRide1 = ride1.getDriver();
        Driver driverMappedWithRide2 = ride2.getDriver();
        
        driverMappedWithRide1.markReachedPickupLocation(ride1);
        driverMappedWithRide1.markRideStarted(ride1);

        driverMappedWithRide2.markReachedPickupLocation(ride2);
        driverMappedWithRide1.markRideCompleted(ride1);

        driverMappedWithRide2.markRideStarted(ride2);
        driverMappedWithRide2.markRideCompleted(ride2);
    }
}