package ca.klapstein.baudit.managers;

import android.support.test.runner.AndroidJUnit4;

import org.greenrobot.eventbus.EventBus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BauditRemoteManagerTest {

    private EventBus bus;
    private BauditRemoteManager bauditRemoteManager;

    @Before
    public void setUp() {
        bus = EventBus.getDefault();
        bauditRemoteManager = new BauditRemoteManager(bus);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStub() {
        // TODO: write tests
    }
}