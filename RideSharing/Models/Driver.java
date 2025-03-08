package RideSharing.Models;

import RideSharing.Actions.DriverActions;
import RideSharing.Enum.DriverStatus;
import RideSharing.Enum.RideStatus;
import RideSharing.Managers.RideManager;
import RideSharing.Observer.Observe;

public class Driver extends User implements DriverActions, Observe{
    private static int driverCount = 0;
    private DriverStatus driverStatus;
    private final RideManager rideManager;

    public Driver(String username) {
        super(driverCount, username);
        Driver.driverCount += 1;
        rideManager = RideManager.getInstance();
        driverStatus = DriverStatus.AVAILABLE;
    }

    @Override
    public void markRideCompleted(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.COMPLETED);
        this.addRiderRating(ride, driverCount);
        this.setDriverStatus(DriverStatus.AVAILABLE);
        System.out.println(this.getUsername() + " marked Ride " + ride.getRideId() + " Completed");
    }

    @Override
    public void addRiderRating(Ride ride, int rating) {
        rideManager.addRiderRating(ride, rating);
    }

    public DriverStatus getDriverStatus() {
        return this.driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    @Override
    public void markReachedPickupLocation(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.REACHED);
        System.out.println("Driver " + ride.getDriver().getUsername() + " marked Ride" + ride.getRideId() + " Reachded pickup Location");
    }

    @Override
    public void markRideStarted(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.START);
        System.out.println("Driver " + ride.getDriver().getUsername() + " Started the Ride " + ride.getRideId());
    }
}
