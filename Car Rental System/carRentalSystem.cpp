#include <bits/stdc++.h>
using namespace std;    

enum RideStatus { IDLE, CREATED, WITHDRAWN, COMPLETED };
enum DriverStatus { IDLE, ON_RIDE };

class System;
System* systemInstance = nullptr;

class Ride;
class Person {
    public:
	string name;
};

class Rider: private Person {
    private:
    int id;
    vector<Ride> completedRides;
    Ride* currentRide;

    public:
    Rider(): currentRide(nullptr) {}
    Rider(int id, string name) {
        this->id = id;
        this->name = name;
        currentRide = nullptr;
    }
    bool isPrivilegeRider() {
        return completedRides.size() >= 10;
    }
    int getId() {
        return this->id;
    }
    void createRide(int src, int origin, int dest, int seats) {
        if (origin >= dest) {
            cout << "Wrong origin and destination" << endl;
            return;
        }
        System::createRide(this->id, src, origin, dest, seats);
    }
    void updateRide(int id, int origin, int dest, int seats) {
        if (!currentRide || currentRide->getId() != id) {
            cout << "Wrong ride Id as input" << endl;
            return;
        }
        if (currentRide->getRideStatus() == RideStatus::WITHDRAWN) {
            cout << "Can't update ride. Ride was withdrawn" << endl;
            return;
        }
        if (currentRide->getRideStatus() == RideStatus::COMPLETED) {
            cout << "Can't update ride. Ride already complete" << endl;
            return;
        }

        createRide(id, origin, dest, seats);
    }

    void withdrawRide(int id) {
        if (!currentRide || currentRide->getId() != id) {
            cout << "Wrong ride Id as input" << endl;
            return;
        }
        if (currentRide->getRideStatus() != RideStatus::CREATED) {
            cout << "Ride wasn't in progress. Can't withdraw ride" << endl;
            return;
        }
        currentRide->setRideStatus(RideStatus::WITHDRAWN);
    }
    void closeRide() {
        if (!currentRide || currentRide->getRideStatus() != RideStatus::CREATED) {
            cout << "Ride wasn't in progress. Can't close ride" << endl;
            return;
        }
        completedRides.push_back(*currentRide);
    }
};

class Driver: private Person {
    private:
    int id;
    vector<Ride> completedRides;
    Ride* currentRide;
    DriverStatus driverStatus;

    public:
    Driver(): currentRide(nullptr), driverStatus(IDLE) {}
    Driver(int id, string name) {
        this->id = id;
        this->name = name;
        this->driverStatus = IDLE;
        currentRide = nullptr;
    }
    int getId() {
        return this->id;
    }
    DriverStatus getDriverStatus() {
        return this->driverStatus;
    }
    void setDriverStatus(DriverStatus driverStatus) {
        this->driverStatus = driverStatus;
    }
    int reachedDestination() {
        if (!currentRide || currentRide->getRideStatus() != RideStatus::CREATED) {
            cout << "Ride wasn't in progress. Can't close ride" << endl;
            return;
        }
        currentRide->setRideStatus(RideStatus::COMPLETED);
        completedRides.push_back(*currentRide);
        currentRide->getRider()->closeRide();
        return currentRide->calculateFare(currentRide->getRider()->isPrivilegeRider());
    }
};

class Ride {
    private:
    int id;
    int origin;
    int dest;
    int seats;
    Driver* driver;
    Rider* rider;
    RideStatus rideStatus;

    public:
    static const int AMT_PER_KM = 20;
    Ride() {
        id = origin = dest = seats = 0;
        rideStatus = RideStatus::IDLE;
        driver = nullptr;
        rider = nullptr;
    }
    int getId() {
        return this->id;
    }
    void setId(int id) {
        this->id = id;
    }
    int getOrigin() {
        return this->origin;
    }
    void setOrigin(int origin) {
        this->origin = origin;
    }
    int getDest() {
        return this->dest;
    }
    void setDest(int dest) {
        this->dest = dest;
    }
    void setRideStatus(RideStatus rideStatus) {
        this->rideStatus = rideStatus;
    }
    RideStatus getRideStatus() {
        return this->rideStatus;
    }
    void setSeats(int seats) {
        this->seats = seats;
    }
    int getSeats() {
        return this->seats;
    }
    int calculateFare(bool isPrivilegeRider) {
        int distance = dest - origin;
        if(seats < 2) {
            return distance * AMT_PER_KM * (isPrivilegeRider?0.75:1);
        }
        return distance * seats * AMT_PER_KM * (isPrivilegeRider?0.5:0.75);
    }
    Rider* getRider() {
        return this->rider;
    }
    void setRider(Rider* rider) {
        this->rider = rider;
    }
    Driver* getDriver() {
        return this->driver;
    }
    void setDriver(Driver* driver) {
        this->driver = driver;
    }
};

class System {
    private:
    int availableDriversCount;
    vector<Driver> drivers;
    vector<Rider> riders;

    public:
    System(vector<Driver>& drivers, vector<Rider>& riders) {
        this->drivers = drivers;
        this->riders = riders;
        this->availableDriversCount = drivers.size();
    }
    void addRider(Rider rider) {
        this->riders.push_back(rider);
    }
    void addDriver(Driver driver) {
        this->drivers.push_back(driver);
        this->availableDriversCount++;
    }
    void createRide(int riderId, int rideId, int origin, int dest, int seats) {
        if (availableDriversCount == 0) {
            cout << "No drivers around. Can't create ride\n";
            return;
        }
        Driver* driver = this->getDriver();
        if (!driver) {
            cout << "No drivers around. Can't create ride\n";
            return;
        }

        driver->setDriverStatus(DriverStatus::ON_RIDE);
        Ride currentRide;
        currentRide.setId(rideId);
        currentRide.setOrigin(origin);
        currentRide.setDest(dest);
        currentRide.setSeats(seats);
        currentRide.setRideStatus(RideStatus::CREATED);
        currentRide.setDriver(driver);
        currentRide.setRider(&riders[riderId]);
        availableDriversCount -= 1;
    }

    Driver* getDriver() {
        for (Driver& driver: drivers) {
            if (driver.getDriverStatus() == DriverStatus::IDLE) {
                return &driver;
            }
        }
        return nullptr;
    }
};


int main() {
    
}