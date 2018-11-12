package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
}