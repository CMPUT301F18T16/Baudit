package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BodyPhotoTest {

    @Mock
    private Bitmap mockValidBitmap;

    @Mock
    private Bitmap mockInvalidBitmap;

    @Before
    public void setUp() {
        mockValidBitmap = mock(Bitmap.class);
        mockInvalidBitmap = mock(Bitmap.class);
    }

    @After
    public void tearDown() {
        mockValidBitmap = null;
    }

    @Test
    public void constructionValid() {
        BodyPhoto bodyPhoto = new BodyPhoto(mockValidBitmap);
        assertNotNull(bodyPhoto);
    }

    @Test
    public void getBitMap() {
        BodyPhoto bodyPhoto = new BodyPhoto(mockValidBitmap);
        Bitmap bitmap = bodyPhoto.getBitmap();
        assertNotNull(bitmap);
        assertEquals(mockValidBitmap, bitmap);
    }

    @Test
    public void setBitMap() {
        BodyPhoto bodyPhoto = new BodyPhoto(mockValidBitmap);
        bodyPhoto.setBitmap(mockValidBitmap);
        Bitmap bitmap = bodyPhoto.getBitmap();
        assertEquals(mockValidBitmap, bitmap);
    }

    @Test
    public void isValid() {
        assertTrue(BodyPhoto.isValid(mockValidBitmap));
    }

    @Ignore("BodyPhoto validation is not implemented")
    @Test(expected = IllegalArgumentException.class)
    public void constructionInvalid() {
        new BodyPhoto(mockInvalidBitmap);
    }

    @Ignore("BodyPhoto validation is not implemented")
    @Test(expected = IllegalArgumentException.class)
    public void setBitMapInvalid() {
        BodyPhoto bodyPhoto = new BodyPhoto(mockValidBitmap);
        bodyPhoto.setBitmap(mockInvalidBitmap);
    }

    @Ignore("BodyPhoto validation is not implemented")
    @Test
    public void isValidInvalid() {
        assertFalse(BodyPhoto.isValid(mockInvalidBitmap));
    }
}