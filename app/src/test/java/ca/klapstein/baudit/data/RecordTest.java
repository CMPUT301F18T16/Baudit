package ca.klapstein.baudit.data;

import org.junit.Test;


import static org.junit.Assert.*;

public class RecordTest {



    /**
     * Test that records created at different times are not equal
     */

    @Test
    public void testNewRecord() {
        Record record1 = new Record();
        Record record2 = new Record();
        assertNotNull(record1.getTimeStamp());
        assertNotNull(record2.getTimeStamp());
    }


    /**
     * Test title created at different times are not equal
     */
    @Test
    public void testTitle() {
        Record record = new Record();
        record.setTitle("Test title");
        assertEquals("Test title", record.getTitle());

    }

    /**
     * Test Key Words for the records
     */
    @Test
    public void testKeywords() {
        Record record = new Record();
        record.addKeyword("Test");
        assertEquals("Test", record.getKeywords().get(0));
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
}