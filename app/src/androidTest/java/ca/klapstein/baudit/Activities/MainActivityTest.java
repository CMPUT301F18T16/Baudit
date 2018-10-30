package ca.klapstein.baudit.Activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityTestRule<MainActivity> {

    private Solo solo;

    public MainActivityTest(){
        super(ca.klapstein.baudit.Activities.MainActivity.class);
    }

    @Before
    public void setUp() {
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
    }
}