package ca.klapstein.baudit.data;

/**
 * Data class representing a geographic location described within latitude and longitude coordinates.
 */
public class GeoLocation {
    private static final String TAG = "GeoLocation";

    /**
     * Latitude must be between -90 and +90.
     */
    private double lat;

    /**
     * Longitude must be between -180 and +180.
     */
    private double lon;

    /**
     * Initialize a GeoLocation.
     *
     * @param lat {@code double} a latitude value that must be between -90 and +90.
     * @param lon {@code double} a longitude value that must be between -180 and +180.
     */
    public GeoLocation(double lat, double lon) {
        setLon(lon);
        setLat(lat);
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        if (isValidLon(lon)) {
            this.lon = lon;
        } else {
            throw new IllegalArgumentException("Invalid Longitude value");
        }
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        if (isValidLat(lat)) {
            this.lat = lat;
        } else {
            throw new IllegalArgumentException("Invalid Latitude value");
        }
    }

    /**
     * Validate whether a latitude value is valid. i.e. Being between -90 and +90.
     *
     * @param lat {@code double}
     * @return {@code boolean} {@code true} if it as valid latitude value, otherwise {@code false}.
     */
    public boolean isValidLat(double lat) {
        return lat >= -90 && lat <= 90;
    }

    /**
     * Validate whether a longitude value is valid. i.e. Being between -180 and +180.
     *
     * @param lon {@code double}
     * @return {@code boolean} {@code true} if it as valid longitude value, otherwise {@code false}.
     */
    public boolean isValidLon(double lon) {
        return lon >= -180 && lon <= 180;
    }
}
