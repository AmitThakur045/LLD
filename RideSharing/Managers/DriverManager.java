package RideSharing.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import RideSharing.Enum.DriverStatus;
import RideSharing.Models.Driver;

public class DriverManager {

    private static DriverManager instance;
    private final HashMap<Integer, Driver> driverMapping;

    private DriverManager() {
        driverMapping = new HashMap<>();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public void addDriver(Driver driver) {
        driverMapping.put(driver.getUserId(), driver);
    }

    public void addDriverRating(Driver driver, int rating) {
        double driverRating = driver.getRating();
        double newRating = ((driverRating * driver.getTotalRide() + rating) / (driver.getTotalRide() + 1));
        driver.setRating(newRating);
    }

    public List<Driver> getAllDriver() {
        return new ArrayList<>(driverMapping.values());
    }

    public Driver findDriver() {
        List<Driver> driverList = this.getAllDriver();

        Driver availableDriver = driverList.stream()
                .filter(driver -> {
                    return driver.getDriverStatus() == DriverStatus.AVAILABLE;
                })
                .findFirst()
                .orElse(null);

        if (availableDriver != null) {
            availableDriver.setDriverStatus(DriverStatus.BUSY);
            availableDriver.notify(availableDriver.getUsername() + " have a ride to pick");
        } else {
            System.out.println("No available driver");
        }
        return availableDriver;
    }

    public void printAllDriver() {
        List<Driver> drivers = this.getAllDriver();
        System.out.println();
        drivers.forEach((driverObj) ->
                System.out.println(driverObj.getUserId() + " " + driverObj.getUsername() + " " + driverObj.getDriverStatus())
        );
        System.out.println();
    }
}
