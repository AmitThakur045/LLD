package RideSharing.Models;

import RideSharing.Actions.DriverActions;
import RideSharing.Enum.DriverStatus;
import RideSharing.Enum.RideStatus;
import RideSharing.Managers.RideManager;
import RideSharing.Observer.Observe;

public class Driver extends User implements DriverActions, Observe{
    private static int driverCount = 0;
    private DriverStatus driverStatus;
    private RideManager rideManager;

    public Driver(String username) {
        super(driverCount, username);
        Driver.driverCount += 1;
        rideManager = new RideManager();
        driverStatus = DriverStatus.AVAILABLE;
    }

    @Override
    public void markRideCompleted(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.COMPLETED);
        this.addRiderRating(null, driverCount);
        driverStatus = DriverStatus.AVAILABLE;
    }

    @Override
    public void addRiderRating(Ride ride, int rating) {
        rideManager.addRiderRating(ride, rating);
    }

    public DriverStatus getDriverStatus() {
        return this.driverStatus;
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    @Override
    public void markReachedPickupLocation(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.REACHED);
        driverStatus = DriverStatus.BUSY;
    }

    @Override
    public void markRideStarted(Ride ride) {
        rideManager.updateRideStatus(ride, RideStatus.START);
    }
}
