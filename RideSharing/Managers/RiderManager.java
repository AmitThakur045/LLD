package RideSharing.Managers;

import java.util.HashMap;

import RideSharing.Models.Rider;

public class RiderManager { 
    private HashMap<Integer, Rider> riderMapping;
    private static RiderManager instance;

    private RiderManager() {
        riderMapping = new HashMap<>();
    }

    public static RiderManager getInstance() {
        if (instance == null) {
            synchronized (RiderManager.class) {
                if (instance == null) {
                    instance = new RiderManager();
                }
            }
        }
        return instance;
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
