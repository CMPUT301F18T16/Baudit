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

    @Test
    public void testStub() {
        recordTreeSet = new RecordTreeSet();

        Record  record1 = new Record();
        Record record2 = new Record();
        Record record3 = new Record();


        assertTrue(recordTreeSet.add(record1));
        assertTrue(recordTreeSet.add(record2));
        assertTrue(recordTreeSet.contains(record2));

        assertTrue(recordTreeSet.remove(record2));
        assertFalse(recordTreeSet.contains(record2));

        assertTrue(recordTreeSet.contains(record1));

        assertTrue(recordTreeSet.add(record3));

    }
}