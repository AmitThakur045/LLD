package RideSharing.Actions;

import RideSharing.Models.Location;
import RideSharing.Models.Ride;

public interface RiderActions {
    public Ride bookRide(Location pickUpLocation, Location dropLocation);
    public void addDriverRating(Ride ride, int rating);
}
