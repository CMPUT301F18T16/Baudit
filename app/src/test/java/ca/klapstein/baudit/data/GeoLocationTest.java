package ca.klapstein.baudit.data;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GeoLocationTest {

    private static final String VALID_ADDRESS = "Edmonton International Airport";
    private static final double VALID_LAT = 10.00001;
    private static final double VALID_LON = 10.00001;
    private static final double INVALID_LON = 190.00001;
    private static final double INVALID_LAT = 100.00001;

    @Test
    public void getAddress() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        assertEquals("Edmonton International Airport", geoLocation.getAddress());
    }

    @Test
    public void setAddress() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        geoLocation.setAddress("Middle Earth");
        assertEquals("Middle Earth", geoLocation.getAddress());
    }

    @Test
    public void getLon() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        assertEquals(10.00001, geoLocation.getLon());
    }

    @Test
    public void setLon() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        geoLocation.setLon(5.00001);
        assertEquals(5.00001, geoLocation.getLon());
    }

    @Test
    public void getLat() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        assertEquals(10.00001, geoLocation.getLat());
    }

    @Test
    public void setLat() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        geoLocation.setLat(5.00001);
        assertEquals(5.00001, geoLocation.getLat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructLatInvalid() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, INVALID_LAT, VALID_LON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructLonInvalid() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, INVALID_LAT, VALID_LON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructLonLatInvalid() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, INVALID_LAT, INVALID_LON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLatInvalid() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        geoLocation.setLat(INVALID_LAT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLonInvalid() {
        GeoLocation geoLocation = new GeoLocation(VALID_ADDRESS, VALID_LAT, VALID_LON);
        geoLocation.setLon(INVALID_LON);
    }
}