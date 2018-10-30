package ca.klapstein.baudit.Managers;

import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BauditDataBaseManagerTest {

    private BauditDataBaseManager bauditDataBaseManager;

    @Before
    public void setUp()  {
        bauditDataBaseManager = new BauditDataBaseManager();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStub() {
        // TODO: write tests
    }
}