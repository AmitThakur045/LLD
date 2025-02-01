#include "bits/stdc++.h"
#include "Trip.cpp"
#include "Driver.cpp"

using namespace std;

class DriverMatchingStrategy {
    public:
    virtual Driver* matchDriver(TripMetaData* metaData) = 0;   
};

class LeastTimeStrategy: public DriverMatchingStrategy {
    public:
    Driver* matchDriver(TripMetaData* metaData) {
        DriverManager* driverManager = new DriverManager();
        
        if (driverManager->getDriversMap().size() == 0) {
            cout << "No drivers available" << endl;
            return nullptr;
        }

        Driver* driver = driverManager->getDriversMap().begin()->second;
        metaData->setDriverRating(driver->getRating());
        return driver;
    }
};