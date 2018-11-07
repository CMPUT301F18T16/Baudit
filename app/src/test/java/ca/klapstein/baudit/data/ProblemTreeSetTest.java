package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


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
    public void testStub() {
        problemTreeSet = new ProblemTreeSet();

        Problem problem1 = new Problem("title1", "1");
        assertTrue(problemTreeSet.add(problem1));

        Problem problem2 = new Problem("title2", "2");
        assertTrue(problemTreeSet.add(problem2));

        Problem problem3 = new Problem("title3", "3");
        assertTrue(problemTreeSet.add(problem3));

        Problem problem4 = new Problem("title4", "4");
        assertTrue(problemTreeSet.add(problem4));


        assertTrue(problemTreeSet.contains(problem3));
        assertTrue(problemTreeSet.remove(problem3));




        assertFalse(problemTreeSet.contains(problem3));
        assertTrue(problemTreeSet.contains(problem4));











        // TODO: write tests
    }
}