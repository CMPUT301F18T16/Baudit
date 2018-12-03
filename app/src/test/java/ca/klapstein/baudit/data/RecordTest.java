package ca.klapstein.baudit.data;

import android.graphics.Bitmap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class RecordTest {

    @Mock
    private Bitmap mockRecordPhoto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructor1() {
        Record record = new Record();
        assertNull(record.getTitle());
        assertNull(record.getComment());
    }

    @Test
    public void testConstructor2() {
        Record record = new Record("title");
        assertEquals("title", record.getTitle());
        assertNull(record.getComment());
    }

    @Test
    public void testConstructor3() {
        Record record = new Record("title", "comment");
        assertEquals("title", record.getTitle());
        assertEquals("comment", record.getComment());
    }

    /**
     * Test that records created at different times are not equal
     */
    @Test
    public void testNewRecord() throws InterruptedException {
        Record record = new Record();
        Thread.sleep(1000);
        Record record1 = new Record();
        assertNotNull(record.getTimeStamp());
        assertNotNull(record1.getTimeStamp());
        assertNotEquals(record.getTimeStamp(), record1.getTimeStamp());
        assertNotEquals(record.getDate(), record1.getDate());
    }

    /**
     * Test setting and retrieving the title of the record
     */
    @Test
    public void testTitle() {
        Record record = new Record();
        record.setTitle("Test title");
        assertEquals("Test title", record.getTitle());
    }

    /**
     * Test setting and retrieving the comment of the record
     */
    @Test
    public void testComment() {
        Record record = new Record();
        record.setComment("Just testing out a comment here");
        assertEquals("Just testing out a comment here", record.getComment());
    }

    /**
     * Test adding a keyword
     */
    @Test
    public void testKeywords() {
        Record record = new Record();
        record.addKeyword("Test");
        assertEquals("Test", record.getKeywords().get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitleInvalid() {
        Record record = new Record();
        record.setTitle(new String(new char[31]).replace('\0', ' '));
    }


    @Test(expected = IllegalArgumentException.class)
    public void setCommentInvalid() {
        Record record = new Record();
        record.setComment(new String(new char[301]).replace('\0', ' '));
    }

    @Test
    public void getGeoLocation() {
        Record record = new Record();
        assertNull(record.getGeoLocation());
    }

    @Test
    public void setGeoLocation() {
        Record record = new Record();
        assertNull(record.getGeoLocation());
        record.setGeoLocation(mock(GeoLocation.class));
        assertNotNull(record.getGeoLocation());
    }

    @Test
    public void setDate() {
        Record record = new Record();
        Date date1 = new Date(0);
        Date date2 = new Date(1000);
        assertNotEquals(date1, date2);
        record.setDate(date1);
        assertEquals(date1, record.getDate());
        record.setDate(date2);
        assertEquals(date2, record.getDate());

        // assert no reference issues
        assertNotEquals(date1, date2);
    }

    @Test
    public void compareTo() {
        Record record1 = new Record();
        record1.setDate(new Date(0));
        Record record2 = new Record();
        record1.setDate(new Date(1000));
        assertEquals(-1, record1.compareTo(record2));
        assertEquals(1, record2.compareTo(record1));
        assertEquals(0, record2.compareTo(record2));
    }

    @Test
    public void removeKeyWord() {
        Record record = new Record();

        // remove a keyword that does not exist in the list of keywords
        record.removeKeyword("NON_SUCH_KEYWORD");
        assertFalse(record.getKeywords().contains("NON_SUCH_KEYWORD"));

        // remove a keyword that does exist in the list of keywords
        record.addKeyword("KEYWORD");
        assertTrue(record.getKeywords().contains("KEYWORD"));
        record.removeKeyword("KEYWORD");
        assertFalse(record.getKeywords().contains("KEYWORD"));
    }

    @Test
    public void setRecordId() {
        Record record = new Record();
        UUID uuid = UUID.randomUUID();
        record.setRecordId(uuid);
        assertEquals(uuid, record.getRecordId());
    }

    @Test
    public void getRecordId() {
        Record record = new Record();
        assertNotNull(record.getRecordId());
    }

    @Test
    public void getLastRecordPhotoNull() {
        Record record = new Record();
        assertNull(record.getLastRecordPhoto());
    }

    @Test
    public void addRecordPhoto() {
        Record record = new Record();
        record.addRecordPhoto(mockRecordPhoto);
        assertEquals(1, record.getRecordPhotos().size());
    }

    @Test
    public void getLastRecordPhotoNonNull() {
        Record record = new Record();
        record.addRecordPhoto(mockRecordPhoto);
        assertNotNull(record.getLastRecordPhoto());
    }

    @Test
    public void getRecordPhotos() {
        Record record = new Record();
        ArrayList<Bitmap> recordPhotos = record.getRecordPhotos();
        assertEquals(0, recordPhotos.size());
    }
}