package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginPatientActivityTest extends ActivityTestRule<LoginPatientActivity> {

    private Solo solo;

    public LoginPatientActivityTest() {
        super(ca.klapstein.baudit.activities.LoginPatientActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", LoginPatientActivity.class);
    }

    @Test
    public void testLoginSuccess() {
    }

    @Test
    public void testLoginFail() {
    }

    @Test
    public void onLogin() {
    }
}