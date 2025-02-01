#include "bits/stdc++.h"
#include "DriverMatchingStrategy.cpp"
#include "PriceStrategy.cpp"

using namespace std;

class StrategyManager {
    StrategyManager(){}
    static StrategyManager* strategyManager;
    static mutex mtx;

    public:
    StrategyManager* StrategyManager::strategyManager = nullptr;
    StrategyManager* getStrategyManager() {
        if (strategyManager == nullptr) {
            mtx.lock();
            strategyManager = new StrategyManager();
            mtx.unlock();
        }
        return strategyManager;
    }
    
    PriceStrategy* getPriceStrategy(TripMetaData* metaData) {
        return new RatingBasedPriceStrategy();
    }

    DriverMatchingStrategy* getDriverMatchingStrategy(TripMetaData* metaData) {
        return new LeastTimeStrategy();
    }
};