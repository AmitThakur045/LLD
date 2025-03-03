package RideSharing.Actions;

import RideSharing.Models.Ride;

public interface DriverActions {
    public void markReachedPickupLocation(Ride ride);
    public void markRideStarted(Ride ride);
    public void markRideCompleted(Ride ride);
    public void addRiderRating(Ride ride, int rating);
}
