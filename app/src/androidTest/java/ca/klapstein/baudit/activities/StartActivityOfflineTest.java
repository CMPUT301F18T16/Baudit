package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import ca.klapstein.baudit.data.CareProvider;
import ca.klapstein.baudit.data.ContactInfo;
import ca.klapstein.baudit.data.Email;
import ca.klapstein.baudit.data.Patient;
import ca.klapstein.baudit.data.PhoneNumber;
import ca.klapstein.baudit.data.Username;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class StartActivityOfflineTest extends ActivityTestRule<StartActivity> {

    private Solo solo;
    private DataModel dataModel;

    public StartActivityOfflineTest() {
        super(StartActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.clearOfflineLoginAccount();
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    /**
     * Tests an invalid login attempt. Should not open a new activity.
     * Will be updated when the remote login validation is completed.
     */
    @Test
    public void testLoginFail() {
        dataModel.clearOfflineLoginAccount();
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        solo.assertCurrentActivity("Wrong Activity", StartActivity.class);
    }

    @Test
    public void testLoginCareProvider() {
        dataModel.setOfflineLoginAccount(new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        solo.waitForActivity(CareProviderHomeActivity.class);
    }

    @Test
    public void testLoginPatient() {
        dataModel.setOfflineLoginAccount(new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        solo.waitForActivity(PatientHomeActivity.class);
    }
}