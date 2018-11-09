package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@code RecordPhoto}.
 * <p>
 * NOTE: it may be smarted to just make this an instrumentation test as it
 * requires android utilities to actually work. For now using mocks is reasonable but
 * testing the validator might become problematic.
 */
public class RecordPhotoTest {

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
        RecordPhoto recordPhoto = new RecordPhoto(mockValidBitmap);
        assertNotNull(recordPhoto);
    }

    @Test
    public void getBitMap() {
        RecordPhoto recordPhoto = new RecordPhoto(mockValidBitmap);
        Bitmap bitmap = recordPhoto.getBitmap();
        assertNotNull(bitmap);
        assertEquals(mockValidBitmap, bitmap);
    }

    @Test
    public void setBitMap() {
        RecordPhoto recordPhoto = new RecordPhoto(mockValidBitmap);
        recordPhoto.setBitmap(mockValidBitmap);
        Bitmap bitmap = recordPhoto.getBitmap();
        assertEquals(mockValidBitmap, bitmap);
    }

    @Test
    public void isValid() {
        assertTrue(RecordPhoto.isValid(mockValidBitmap));
    }

    @Ignore("RecordPhoto validation is not implemented")
    @Test(expected = IllegalArgumentException.class)
    public void constructionInvalid() {
        new RecordPhoto(mockInvalidBitmap);
    }

    @Ignore("RecordPhoto validation is not implemented")
    @Test(expected = IllegalArgumentException.class)
    public void setBitMapInvalid() {
        RecordPhoto recordPhoto = new RecordPhoto(mockValidBitmap);
        recordPhoto.setBitmap(mockInvalidBitmap);
    }

    @Ignore("RecordPhoto validation is not implemented")
    @Test
    public void isValidInvalid() {
        assertFalse(RecordPhoto.isValid(mockInvalidBitmap));
    }
}