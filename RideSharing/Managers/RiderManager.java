package RideSharing.Managers;

import java.util.HashMap;

import RideSharing.Models.Rider;

public class RiderManager { 
    private HashMap<Integer, Rider> riderMapping;

    public RiderManager() {
        riderMapping = new HashMap<>();
    }

    public void addRider(Rider rider) {
        riderMapping.put(rider.getUserId(), rider);
    }

    public void addRiderRating(Rider rider, int rating) {
        double riderRating = rider.getRating();
        double newRating = ((riderRating * rider.getTotalRide() + rating) / (rider.getTotalRide() + 1));
        rider.setRating(newRating);
    }   
}
