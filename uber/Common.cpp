enum RATING {
    NONE,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE
};

enum DRIVER_STATUS {
    OFFLINE,
    IDLE,
    ON_TRIP
};

enum class TRIP_STATUS {
	UNASSIGNED,
	DRIVER_ON_THE_WAY,
	DRIVER_ARRIVED,
	STARTED,
	PAUSED,
	CANCELLED,
	ENDED,
};

class Utils {
    public:

    static bool isHighRating(RATING rating) {
        return (rating == RATING::FOUR || rating == RATING::FIVE);
    }
};

class Location {
    public:
    double latitude;
    double longitude;
    Location(double latitude, double longitude) {
        this->latitude = latitude;
        this->longitude = longitude;
    }
};