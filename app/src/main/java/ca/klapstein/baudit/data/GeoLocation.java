package ca.klapstein.baudit.data;

/**
 * Data class representing a geographic location described within latitude and longitude coordinates.
 */
public class GeoLocation {
    private double lat;
    private double lon;

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

    public boolean isValidLat(double lat) {
        return lat >= -90 && lat <= 90;
    }

    public boolean isValidLon(double lon) {
        return lon >= -180 && lon <= 180;
    }
}
