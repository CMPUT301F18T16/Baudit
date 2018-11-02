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
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean isValid(double coord) {
        // TODO: implement
        return true;
    }
}
