#include "bits/stdc++.h"
#include "StrategyManager.cpp"
#include "Rider.cpp"
#include "Driver.cpp"
#include "Common.cpp"

using namespace std;


class TripMetaData {
    Location* startLocation;
    Location* endLocation;
    RATING riderRating;
    RATING driverRating;

    public:
    TripMetaData(Location* startLocation, Location* endLocation, RATING riderRating) {
        this->startLocation = startLocation;
        this->endLocation = endLocation;
        this->riderRating = riderRating;
        this->DriverRating = RATING::NONE;
    }

    RATING getRiderRating() {
        return riderRating; 
    }

    RATING getDriverRating() {
        return driverRating; 
    }

    void setDriverRating(RATING rating) {
        driverRating = rating;
    }
}

class Trip {
    Rider* rider;
    Driver* driver;
    Location* startLocation;
    Location* endLocation;
    TRIP_STATUS status;
    int TripId;
    double price;

    PriceStrategy* priceStrategy;
    DriverMatchingStrategy* driverMatchingStrategy;

    public:
    static int nextTripId = 0;
    Trip(Rider* rider, Driver* driver, Location* startLocation, Location* endLocation
        DriverMatchingStrategy* driverMatchingStrategy, PriceStrategy* priceStrategy
    ) {
        this->rider = rider;
        this->driver = driver;
        this->startLocation = startLocation;
        this->endLocation = endLocation;
        this->status = TRIP_STATUS::UNASSIGNED;
        this->driverStrategy = driverMatchingStrategy;
        this->priceStrategy = priceStrategy;
        this->tripId = nextTripId;
        nextTripdId++; 
    }

    int getTripId() {
        return tripId;
    }
    void displayTripDetails() {
        cout << "Trip Id: " << tripId << endl;
        cout << "Rider Name: " << rider->getName() << endl;
        cout << "Driver Name: " << driver->getName() << endl;
        cout << "Start Location: (" << startLocation->latitude << ", " << startLocation->longitude << ")" << endl;
        cout << "End Location: (" << endLocation->latitude << ", " << endLocation->longitude << ")" << endl;
        cout << "Status: " << status << endl;
        cout << "Price: " << price << endl;
    }
};

class TripManager {
    static TripManager* tripManager;
    static mutex mtx;
    map<int, Trip*> tripMap;
    map<int, TripMetaData*> tripMetaDataMap;
    DriverManager* driverManager;
    RiderManager* riderManager;

    TripManager() {
        riderManager = RiderManager::getRiderManager();
        driverManager = DriverManager::getDriverManager();
    }

    public:
    TripManager* TripManager::tripManager = nullptr;
    static TripManager* getTripManager() {
        if (tripManager == nullptr) {
            mtx.lock();
            tripManager = new TripManager();
            mtx.unlock();
        }
        return tripManager;
    }
    void createTrip(Rider* rider, Location* startLocation, Location* endLocation) {
        TripMetaData* metaData = new TripMetaData(startLocation, endLocation, rider->getRating());
        StrategyManager* strategyManager = StrategyManager::getStrategyManager();

        DriverMatchingStrategy* driverMatchingStrategy = strategyManager->getDriverMatchingStrategy(metaData);
        PriceStrategy* priceStrategy = strategyManager->getPriceStrategy(metaData);

        double price = priceStrategy->calculatePrice(metaData);
        Driver* driver = driverMatchingStrategy->matchDriver(metaData);

        Trip* trip = new Trip(rider, driver, startLocation, endLocation, driverMatchingStrategy, priceStrategy);
        tripMap[trip->getTripId()] = trip;
        tripMetaDataMap[trip->getTripId()] = metaData;
    }

    unordered_map<int, Trip*> getTripsMap() {
        return tripMap;
    }
};