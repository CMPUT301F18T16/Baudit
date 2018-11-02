package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RecordTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNewRecord() {
        Record record = new Record();
        Record record1 = new Record();
        assertNotNull(record.timestamp);
        assertNotNull(record1.timestamp);
        assertNotEquals(record.timestamp, record1.timestamp);
    }

    public void testTitle(){
        Record record = new Record();
        record.setTitle("Test title");
        assertEquals("Test title", record.getTitle());
    }

    public void testComment(){
        Record record = new Record();
        record.setTitle("Just testing out a comment here");
        assertEquals("Just testing out a comment here", record.getComment());
    }

    public void testKeywords(){
        Record record = new Record();
        record.addKeyword("Test");
        assertEquals("Test", record.keywords[record.keywords.length+1]);
    }

}