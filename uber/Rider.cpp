#include "bits/stdc++.h"
#include "Common.cpp"

using namespace std;

class Rider {
    string name;
    RATING rating;

    public:
    Rider (string name, RATING rating) {
        this->name = name;
        this->rating = rating;
    }

    string getName() {
        return name;
    }

    RATING getRating() {
        return rating;
    }
};


class RiderManager {
    RiderManager() {}
    static RiderManager* riderManager;
    static mutex mtx;
    map<string, Rider*> riderMap;

    public:
    RiderManager* RiderManager::riderManager = nullptr;
    static RiderManager* getRiderManager() {
        if (riderManager == NULL) {
            mtx.lock();
            riderManager = new RiderManager();
            mtx.unlock();
        }
        return riderManager;
    }
    void addRider(string name, Rider* rider) {
        riderMap[name] = rider;
    }
    Rider* getRider(string name) {
        return riderMap[name];
    }
};