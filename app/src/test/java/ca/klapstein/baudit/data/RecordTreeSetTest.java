package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RecordTreeSetTest {

    private RecordTreeSet recordTreeSet;

    @Before
    public void setUp() {
        recordTreeSet = new RecordTreeSet();
    }

    @After
    public void tearDown() {
        recordTreeSet = null;
    }

    /**
     * Basic Test for the t Tree Sets for Records
     */
    @Test
    public void testRecordTreeSet() {
        recordTreeSet = new RecordTreeSet();

        Record  record1 = new Record();
        assertTrue(recordTreeSet.add(record1));
        assertTrue(recordTreeSet.contains(record1));

        Record record2 = new Record();
        assertTrue(recordTreeSet.add(record2));
        assertTrue(recordTreeSet.contains(record2));

        Record record3 = new Record();
        assertTrue(recordTreeSet.add(record3));
        assertTrue(recordTreeSet.contains(record3));



        recordTreeSet.remove(record3);
        assertFalse(recordTreeSet.contains(record3));



        Record record4 = new Record();
        assertTrue(recordTreeSet.add(record4));
        assertTrue(recordTreeSet.contains(record4));


    }
}