package RideSharing.Actions;

import RideSharing.Models.Location;
import RideSharing.Models.Ride;

public interface RiderActions {
    public Ride bookRider(Location pickUpLocation, Location dropLocation);
    public void addDriverRating(Ride ride, int rating);
}
