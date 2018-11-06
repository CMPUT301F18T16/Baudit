package ca.klapstein.baudit.data;

import org.junit.Test;

import static junit.framework.TestCase.fail;

import static org.junit.Assert.*;

public class BodyPhotoCoordsTest {

    @Test
    public void testBodyPhotoCoordsConstructor() {
        BodyPhotoCoords bodyPhotoCoords = new BodyPhotoCoords(100, 150);
        assertNotNull(bodyPhotoCoords);
        assertEquals(100, bodyPhotoCoords.getPosX());
        assertEquals(150, bodyPhotoCoords.getPosY());
    }

    @Test
    public void testGetBodyPhotoCoords() {
        BodyPhotoCoords bodyPhotoCoords = null;

        try {
            bodyPhotoCoords = new BodyPhotoCoords(200, 500);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(bodyPhotoCoords);
        assertEquals(200, bodyPhotoCoords.getPosX());
        assertEquals(500, bodyPhotoCoords.getPosY());
    }

    @Test
    public void testSetBodyPhotoCoordsValid() {
        try {
            BodyPhotoCoords bodyPhotoCoords = new BodyPhotoCoords(100, 100);
            assertEquals(100, bodyPhotoCoords.getPosY());
            assertEquals(100, bodyPhotoCoords.getPosY());
            bodyPhotoCoords.setCoords(150, 300);
            assertEquals(150, bodyPhotoCoords.getPosX());
            assertEquals(300, bodyPhotoCoords.getPosY());
            bodyPhotoCoords.setCoords(17, 31);
            assertEquals(17, bodyPhotoCoords.getPosX());
            assertEquals(31, bodyPhotoCoords.getPosY());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBodyPhotoCoordsInvalid() {
        new BodyPhotoCoords(-2, -1);
    }

    @Test
    public void testIsValid() {
        // TODO: parameterize these tests
        // test some valid coords
        assertTrue(BodyPhotoCoords.isValid(0, 0));
        assertTrue(BodyPhotoCoords.isValid(100, 100));
        assertTrue(BodyPhotoCoords.isValid(1000000000, 1000000000));

        // test some invalid coords
        assertFalse(BodyPhotoCoords.isValid(-1, 0));
        assertFalse(BodyPhotoCoords.isValid(0, -1));
        assertFalse(BodyPhotoCoords.isValid(-1, -1));
    }
}