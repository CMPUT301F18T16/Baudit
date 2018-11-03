package ca.klapstein.baudit.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecordTest {

    /**
     * Test that records created at different times are not equal
     */
    @Test
    public void testNewRecord() throws InterruptedException {
        Record record = new Record();
        Thread.sleep(1000);
        Record record1 = new Record();
        assertNotNull(record.getTimestamp());
        assertNotNull(record1.getTimestamp());
        assertNotEquals(record.getTimestamp(), record1.getTimestamp());
    }

    /**
     * Test setting and retrieving the title of the record
     */
    public void testTitle(){
        Record record = new Record();
        record.setTitle("Test title");
        assertEquals("Test title", record.getTitle());
    }

    /**
     * Test setting and retrieving the comment of the record
     */
    public void testComment(){
        Record record = new Record();
        record.setTitle("Just testing out a comment here");
        assertEquals("Just testing out a comment here", record.getComment());
    }

    /**
     * Test adding a keyword
     */
    public void testKeywords(){
        Record record = new Record();
        record.addKeyword("Test");
        assertEquals("Test", record.getKeywords().get(0));
    }
}