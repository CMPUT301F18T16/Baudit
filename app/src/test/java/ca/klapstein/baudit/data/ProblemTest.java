package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProblemTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void ProblemConstructor() {
        Problem problem = new Problem("test_title", "");
        assertNotNull(problem);
        assertEquals("test_title", problem.getTitle());
        assertEquals("",problem.getDescription());
    }

    @Test
    public void testGetTitle(){
        Problem problem = null;

        try{
            problem = new Problem("test_title", "");
        }catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(problem);
        assertEquals("test_title", problem.getTitle());
    }

    @Test
    public void testGetDescription(){
        Problem problem = null;
        try{
            problem = new Problem("test_title", "test_description");
        }catch(IllegalArgumentException e){
            fail();
        }
        assertEquals("test_description", problem.getDescription());
    }

    @Test
    public void testSetTitleValid(){
        try{
            Problem problem = new Problem ("test1", "");
            assertEquals("test1", problem.getTitle());
            problem.setTitle("test2");
            assertEquals("test2", problem.getTitle());
            problem.setTitle("testing3");
            assertEquals("testing3", problem.getTitle());
        } catch (IllegalArgumentException e){
            fail();
        }

    }

    @Test
    public void testSetDescriptionValid(){
        try{
            Problem problem = new Problem ("test", "description1");
            assertEquals("description1", problem.getDescription());
            problem.setDescription("description2");
            assertEquals("description2", problem.getDescription());
            problem.setDescription("description3");
            assertEquals("description3", problem.getDescription());
        } catch (IllegalArgumentException e){
            fail();
        }
    }


}