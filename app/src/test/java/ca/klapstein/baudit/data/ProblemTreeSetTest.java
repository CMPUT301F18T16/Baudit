package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ProblemTreeSetTest {

    private ProblemTreeSet problemTreeSet;

    @Before
    public void setUp() {
        problemTreeSet = new ProblemTreeSet();
    }

    @After
    public void tearDown() {
        problemTreeSet = null;
    }

    @Test
    public void testGetProblem() {
        Problem problem = new Problem("title", "test");
        problemTreeSet.add(problem);
        assertEquals("title", problemTreeSet.first().getTitle());
        assertEquals("test", problemTreeSet.first().getDescription());
    }

    @Test
    public void testAddProblem() {
        Problem problem = new Problem("title", "test");
        problemTreeSet.add(problem);
        assertTrue(problemTreeSet.contains(problem));
    }

    @Test
    public void testRemoveProblem() {
        Problem problem = new Problem("title", "test");
        problemTreeSet.add(problem);
        assertTrue(problemTreeSet.contains(problem));
        problemTreeSet.remove(problem);
        assertFalse(problemTreeSet.contains(problem));
    }

    @Test
    public void testProblemTreeSetComparable() {

        Problem problem0 = new Problem("problem0");
        Problem problem1 = new Problem("problem1");
        Problem problem2 = new Problem("problem2");

        problemTreeSet.add(problem2);
        problemTreeSet.add(problem1);
        problemTreeSet.add(problem0);

        // Assert ProblemTreeSet is sorted by username alphabetically
        assertTrue(problemTreeSet.first().equals(problem0));
        assertTrue(problemTreeSet.last().equals(problem2));
        assertTrue(problemTreeSet.size() == 3);

        Problem problem3 = new Problem("problem3");

        Calendar calendar = Calendar.getInstance();
        long timeMilli = calendar.getTimeInMillis();
        problem3.setDate(new Date(timeMilli + 100));

        problemTreeSet.add(problem3);

        // Assert ProblemTreeSet is sorted by date if no title is set
        assertTrue(problemTreeSet.first().equals(problem0));
        assertTrue(problemTreeSet.last().equals(problem3));
        assertTrue(problemTreeSet.size() == 4);
    }
}
