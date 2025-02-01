#include "bits/stdc++.h"
#include "Trip.cpp"
#include "Rider.cpp"
#include "Driver.cpp"

using namespace std;

int main() {
    Rider* rider = new Rider("Hello", RATING::FOUR);
    Driver* driver = new Driver("World", RATING::FOUR, DRIVER_STATUS::OFFLINE);

    return 0;
}