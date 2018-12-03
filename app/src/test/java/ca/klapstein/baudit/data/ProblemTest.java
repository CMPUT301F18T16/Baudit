package ca.klapstein.baudit.data;

import ca.klapstein.baudit.BauditDateFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ProblemTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private Problem getExampleProblem() {
        return new Problem("test");
    }

    @Test
    public void problemConstructor() {
        Problem problem = new Problem("test_title", "");
        assertNotNull(problem);
        assertEquals("test_title", problem.getTitle());
        assertEquals("",problem.getDescription());
    }

    @Test
    public void testGetTitle() throws IllegalArgumentException {
        Problem problem = new Problem("test_title", "");
        assertNotNull(problem);
        assertEquals("test_title", problem.getTitle());
    }

    @Test
    public void testGetDescription() throws IllegalArgumentException {
        Problem problem = new Problem("test_title", "test_description");
        assertEquals("test_description", problem.getDescription());
    }


    @Test
    public void testSetTitleValid() throws IllegalArgumentException {
        Problem problem = new Problem ("test1", "");
        assertEquals("test1", problem.getTitle());
        problem.setTitle("test2");
        assertEquals("test2", problem.getTitle());
        problem.setTitle("testing3");
        assertEquals("testing3", problem.getTitle());
    }

    @Test
    public void testSetDescriptionValid() throws IllegalArgumentException {
        Problem problem = new Problem ("test", "description1");
        assertEquals("description1", problem.getDescription());
        problem.setDescription("description2");
        assertEquals("description2", problem.getDescription());
        problem.setDescription("description3");
        assertEquals("description3", problem.getDescription());
    }

    @Test
    public void getTimeStamp() throws ParseException {
        Problem problem = new Problem("test", "description1");
        String timeStamp = problem.getTimeStamp();
        assertNotNull(timeStamp);
        Date date = BauditDateFormat.getBauditDateFormat().parse(timeStamp);
        assertNotNull(date);
        assertEquals(BauditDateFormat.getBauditDateFormat().format(date), timeStamp);
    }

    @Test
    public void getDate() {
        Problem problem = new Problem("test", "description1");
        Date date = problem.getDate();
        assertNotNull(date);
    }

    @Test
    public void setDate() {
        Problem problem = new Problem("test", "description1");
        Date date1 = new Date(0);
        Date date2 = new Date(1000);
        assertNotEquals(date1, date2);
        problem.setDate(date1);
        assertEquals(date1, problem.getDate());
        problem.setDate(date2);
        assertEquals(date2, problem.getDate());
        assertNotEquals(date1, date2);
    }

    @Test
    public void compareToDifferentDateSameTitle() {
        Problem problem1 = new Problem("test", "description");
        problem1.setDate(new Date(0));
        Problem problem2 = new Problem("test", "description");
        problem2.setDate(new Date(1000));
        assertEquals(-1, problem1.compareTo(problem2));
        assertEquals(1, problem2.compareTo(problem1));
        assertEquals(0, problem2.compareTo(problem2));
    }

    @Test
    public void compareToDifferentDateDifferentTitle() {
        Problem problem1 = new Problem("a", "description");
        problem1.setDate(new Date(0));
        Problem problem2 = new Problem("b", "description");
        problem2.setDate(new Date(1000));
        assertEquals(-1, problem1.compareTo(problem2));
        assertEquals(1, problem2.compareTo(problem1));
        assertEquals(0, problem2.compareTo(problem2));
    }

    @Test
    public void compareToSameDateSameTitle() {
        Problem problem1 = new Problem("test", "description");
        problem1.setDate(new Date(0));
        Problem problem2 = new Problem("test", "description");
        problem2.setDate(new Date(0));
        assertEquals(0, problem1.compareTo(problem2));
    }

    @Test
    public void compareToSameDateDifferentTitle() {
        Problem problem1 = new Problem("a", "description");
        problem1.setDate(new Date(0));
        Problem problem2 = new Problem("b", "description");
        problem2.setDate(new Date(0));
        assertEquals(-1, problem1.compareTo(problem2));
        assertEquals(1, problem2.compareTo(problem1));
        assertEquals(0, problem2.compareTo(problem2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDescriptionInvalid() {
        Problem problem = getExampleProblem();
        problem.setDescription(new String(new char[301]).replace('\0', ' '));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitleInvalid() {
        Problem problem = getExampleProblem();
        problem.setTitle(new String(new char[31]).replace('\0', ' '));
    }

    @Test
    public void getRecordTreeSet() {
        Problem problem = getExampleProblem();
        RecordTreeSet recordTreeSet = problem.getRecordTreeSet();
        assertNotNull(recordTreeSet);
        // make sure newly created problem has no records
        assertEquals(0, problem.getRecordTreeSet().size());
    }

    @Test
    public void setRecordTreeSet() {
        Problem problem = getExampleProblem();
        RecordTreeSet recordTreeSet1 = problem.getRecordTreeSet();
        assertNotNull(recordTreeSet1);
        // make sure newly created problem has no records
        assertEquals(0, problem.getRecordTreeSet().size());

        RecordTreeSet recordTreeSet2 = new RecordTreeSet();
        recordTreeSet2.add(mock(Record.class));
        assertNotEquals(recordTreeSet1, recordTreeSet2);

        problem.setRecordTreeSet(recordTreeSet2);
        assertEquals(recordTreeSet2, problem.getRecordTreeSet());
        assertNotEquals(recordTreeSet1, problem.getRecordTreeSet());

        // ensure not reference overriding
        assertNotEquals(recordTreeSet1, recordTreeSet2);
    }

    @Test
    public void getProblemId() {
        Problem problem = new Problem("test title", "");
        assertNotNull(problem.getProblemId());
    }
}