package ca.klapstein.baudit.data;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BodyPhotoCoordsTest {

    @Test
    public void testBodyPhotoCoordsConstructor() {
        BodyPhotoCoords bodyPhotoCoords = new BodyPhotoCoords(100,150);
        assertNotNull(bodyPhotoCoords);
        assertEquals(100, bodyPhotoCoords.getX());
        assertEquals(150, bodyPhotoCoords.getY());
    }

    @Test
    public void testGetBodyPhotoCoords(){
        BodyPhotoCoords bodyPhotoCoords = null;

        try {
            bodyPhotoCoords = new BodyPhotoCoords(200,500);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(bodyPhotoCoords);
        assertEquals(200, bodyPhotoCoords.getX());
        assertEquals(500, bodyPhotoCoords.getY());
    }

    @Test
    public void testSetBodyPhotoCoordsValid(){
        try{
            BodyPhotoCoords bodyPhotoCoords = new BodyPhotoCoords(100,100);
            assertEquals(100, bodyPhotoCoords.getX());
            assertEquals(100, bodyPhotoCoords.getY());
            bodyPhotoCoords.setX(150);
            bodyPhotoCoords.setY(300);
            assertEquals(150, bodyPhotoCoords.getX());
            assertEquals(300, bodyPhotoCoords.getY());
            bodyPhotoCoords.setX(17);
            bodyPhotoCoords.setY(31);
            assertEquals(17, bodyPhotoCoords.getX());
            assertEquals(31, bodyPhotoCoords.getY());
        } catch(IllegalArgumentException e){
            fail();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBodyPhotoCoordsInvalid(){
        new BodyPhotoCoords(-2, -1);
    }
}