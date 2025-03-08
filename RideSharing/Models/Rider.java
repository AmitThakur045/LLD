package RideSharing.Models;

import RideSharing.Actions.RiderActions;
import RideSharing.Managers.RideManager;

public class Rider extends User implements RiderActions{
    private static int riderCount = 0;

    private final RideManager rideManager;

    public Rider(String username) {
        super(riderCount, username);
        rideManager = RideManager.getInstance();
        Rider.riderCount += 1;
    }

    @Override
    public Ride bookRide(Location pickUpLocation, Location dropLocation) {
        System.out.println("Rider " + this.getUsername() + " created a ride.");
        return rideManager.createNewRide(this, pickUpLocation, dropLocation);
    }

    @Override
    public void addDriverRating(Ride ride, int rating) {
        rideManager.addDriverRating(ride, rating);
        System.out.println("Rider " + this.getUsername() + " added ride rating.");
    }

}
