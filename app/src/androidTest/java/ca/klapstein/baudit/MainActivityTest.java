package ca.klapstein.baudit;

import android.support.test.rule.ActivityTestRule;

import ca.klapstein.baudit.Activities.MainActivity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example InstrumentedTest for testing MainActivity
 */
public class MainActivityTest extends ActivityTestRule<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    /**
     * Placeholder test
     */
    @Test
    public void testSuccess() {
        assertEquals(true,true);
    }
}