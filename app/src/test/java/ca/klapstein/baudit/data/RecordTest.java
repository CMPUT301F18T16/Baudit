package ca.klapstein.baudit.data;

import org.junit.Test;

import static org.junit.Assert.*;


public class RecordTest {

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
}