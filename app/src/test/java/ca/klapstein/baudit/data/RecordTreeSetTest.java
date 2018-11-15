package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

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
    public void testRecordTreeSetComparable() {

        // Assert RecordTreeSet is sorted by date

        Calendar calendar = Calendar.getInstance();
        long timeMilli = calendar.getTimeInMillis();

        Record record0 = new Record();
        Record record1 = new Record();
        Record record2 = new Record();

        record0.setDate(new Date(timeMilli + 100));
        record1.setDate(new Date(timeMilli + 101));
        record2.setDate(new Date(timeMilli + 102));

        recordTreeSet.add(record2);
        recordTreeSet.add(record0);
        recordTreeSet.add(record1);

        assertTrue(recordTreeSet.first().equals(record0));
        assertTrue(recordTreeSet.last().equals(record2));
        assertTrue(recordTreeSet.size() == 3);

    }
}