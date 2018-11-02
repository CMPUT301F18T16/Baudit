package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GeoLocationTest {

    private static final double valid_lat = 10.00001;
    private static final double valid_lon = 10.00001;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getLon() {
        GeoLocation geoLocation = new GeoLocation(valid_lat, valid_lon);
        assertEquals(10.00001, geoLocation.getLon());
    }

    @Test
    public void setLon() {
        GeoLocation geoLocation = new GeoLocation(valid_lat, valid_lon);
        geoLocation.setLon(5.00001);
        assertEquals(5.00001, geoLocation.getLon());
    }

    @Test
    public void getLat() {
        GeoLocation geoLocation = new GeoLocation(valid_lat, valid_lon);
        assertEquals(10.00001, geoLocation.getLat());
    }

    @Test
    public void setLat() {
        GeoLocation geoLocation = new GeoLocation(valid_lat, valid_lon);
        geoLocation.setLat(5.00001);
        assertEquals(5.00001, geoLocation.getLat());
    }

    @Test
    public void isValid() {
    }
}