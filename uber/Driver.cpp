#include "bits/stdc++.h"
#include "Common.cpp"

using namespace std;

class Driver {
    string name;
    RATING rating;
    DRIVER_STATUS status;

    public:
    Driver (string name, RATING rating) {
        this->name = name;
        this->rating = rating;
        this->status = DRIVER_STATUS::OFFLINE;
    }

    string getName() {
        return name;
    }

    RATING getRating() {
        return rating;
    }

    DRIVER_STATUS getStatus() {
        return status;
    }
};


class DriverManager {
    DriverManager() {}
    static DriverManager* driverManager;
    static mutex mtx;
    map<string, Driver*> driverMap;

    public: 
    DriverManager* DriverManager::driverManager = nullptr;
    DriverManager* getDriverManager() {
        if (driverManager == nullptr) {
            mtx.lock();
            driverManager = new DriverManager();
            mtx.unlock();
        }
        return driverManager;
    }

    void addDriver(string name, Driver* driver) {
        driverMap[name] = driver;
    }
    
    Driver* getDriver(string name) {
        return driverMap[name];
    }

    map<string, Driver*> getDriversMap() {
        return driverMap;
    }
};