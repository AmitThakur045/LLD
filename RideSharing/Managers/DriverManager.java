package RideSharing.Managers;

import java.util.HashMap;

// import RideSharing.Enum.DriverStatus;
// import RideSharing.Models.Driver;

public class DriverManager {
    private HashMap<Integer, Driver> driverMapping;
    
    public DriverManager() {
        driverMapping = new HashMap<>();
    }

    public void addDriver(Driver driver) {
        driverMapping.put(driver.getUserId(), driver);
    }

    public void addDriverRating(Driver driver, int rating) {
        double driverRating = driver.getRating();
        double newRating = ((driverRating * driver.getTotalRide() + rating) / (driver.getTotalRide() + 1));
        driver.setRating(newRating);
    }

    public Driver findDriver() {
        final Driver[] driver = {null};
        driverMapping.forEach((k, v) -> {
            if (v.getDriverStatus() == DriverStatus.AVAILABLE) {
                System.out.println("Driver found with id: " + v.getUserId());
                driver[0] = v;
            }
        });

        if (driver.length == 0) {
            System.out.println("No driver found");
            return null;
        }
        driver[0].notify("Please reach the pickup location");
        return driver[0];
    }
}
