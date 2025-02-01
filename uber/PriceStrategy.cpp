#include "bits/stdc++.h"
#include "Trip.cpp"
#include "Common.cpp"

using namespace std;

class PriceStrategy {
    public:
    virtual double calculatePrice(TripMetaData* metaData) = 0;   
};

class RatingBasedPriceStrategy : public PriceStrategy {
    public:
    double calculatePrice(TripMetaData* metaData) {
        return Utils::isHighRating(metaData->getRiderRating()) ? 95.0 : 100.0; 
    }
};