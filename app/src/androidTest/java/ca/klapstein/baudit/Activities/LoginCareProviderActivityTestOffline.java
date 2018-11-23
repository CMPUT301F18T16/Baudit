package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class LoginCareProviderActivityTestOffline extends ActivityTestRule<LoginCareProviderActivity> {

    private Solo solo;
    private DataModel dataModel;

    public LoginCareProviderActivityTestOffline() {
        super(LoginCareProviderActivity.class);
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

    @Test
    public void testLoginCareProvider() {
        dataModel.setOfflineLoginAccount(new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo(new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        solo.waitForActivity(CareProviderHomeActivity.class);
    }

    @Test
    public void testLoginPatient() {
        dataModel.setOfflineLoginAccount(new Patient(
                new Username("TESTPatient1"),
                new ContactInfo(new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
        solo.waitForActivity(PatientHomeActivity.class);
    }
}