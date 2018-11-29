package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.R;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class CareProviderHomeActivityTest extends ActivityTestRule<CareProviderHomeActivity> {

    private Solo solo;
    private DataModel dataModel;

    public CareProviderHomeActivityTest() {
        super(CareProviderHomeActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.setOfflineLoginAccount(new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void testAssignPatient() {
        solo.clickOnView(solo.getView(R.id.care_provider_home_fab));
        // TODO: should go to camera activity but robotium does not like me
    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", CareProviderHomeActivity.class);
    }
}