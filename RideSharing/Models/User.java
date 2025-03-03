package RideSharing.Models;

public class User {
    private int user_id;
    private String username;
    private double rating;
    private int totalRide;

    public User(int user_id, String username) {
        this.user_id = user_id;
        this.username = username;
        this.rating = 0;
        this.totalRide = 0;
    }

    public String getUsername() { return this.username; }

    public double getRating() { return this.rating; }

    public int getUserId() { return this.user_id; }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalRide() {
        return this.totalRide;
    }
}
