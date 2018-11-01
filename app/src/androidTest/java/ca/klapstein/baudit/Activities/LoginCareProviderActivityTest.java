package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginCareProviderActivityTest extends ActivityTestRule<LoginCareProviderActivity> {

    private Solo solo;

    public LoginCareProviderActivityTest() {
        super(ca.klapstein.baudit.activities.LoginCareProviderActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", LoginCareProviderActivity.class);
    }

    @Test
    public void onLogin() {
    }
}