package ca.klapstein.baudit.data;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecordTest {

    /**
     * Test that records created at different times are not equal
     */
    @Test
    public void testNewRecord() {
        Record record = new Record();
        assertNotNull(record.getTimestamp());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA);
        assertEquals(dateFormat.format(new Date()), record.getTimestamp());
    }

    /**
     * Test setting and retrieving the title of the record
     */
    @Test
    public void testTitle(){
        Record record = new Record();
        record.setTitle("Test title");
        assertEquals("Test title", record.getTitle());
    }

    /**
     * Test setting and retrieving the comment of the record
     */
    @Test
    public void testComment(){
        Record record = new Record();
        record.setComment("Just testing out a comment here");
        assertEquals("Just testing out a comment here", record.getComment());
    }

    /**
     * Test adding a keyword
     */
    @Test
    public void testKeywords(){
        Record record = new Record();
        record.addKeyword("Test");
        assertEquals("Test", record.getKeywords().get(0));
    }
}