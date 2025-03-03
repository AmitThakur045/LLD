package RideSharing.Models;

import RideSharing.Actions.RiderActions;
import RideSharing.Managers.RideManager;

public class Rider extends User implements RiderActions{
    private static int riderCount = 0;

    private RideManager rideManager;

    public Rider(String username) {
        super(riderCount, username);
        Rider.riderCount += 1;
    }

    @Override
    public Ride bookRider(Location pickUpLocation, Location dropLocation) {
        return rideManager.createNewRide(this, pickUpLocation, dropLocation);
    }

    @Override
    public void addDriverRating(Ride ride, int rating) {
        rideManager.addDriverRating(ride, rating);
    }

}
