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
import static org.junit.Assert.assertTrue;

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
        CareProvider careProvider = new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        );
        Patient patient1 = new Patient(
                new Username("TESTPatient13"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        );
        patient1.getProblemTreeSet().add(new Problem("problem 1", "description"));
        patient1.getProblemTreeSet().add(new Problem("problem 2", "description"));
        patient1.getProblemTreeSet().add(new Problem("problem 3", "description"));
        careProvider.getAssignedPatientTreeSet().add(patient1);
        Patient patient2 = new Patient(
                new Username("TESTPatient12"),
                new ContactInfo("Billy", "Bob", new Email("bob@example.com"), new PhoneNumber("111-111-1111"))
        );
        careProvider.getAssignedPatientTreeSet().add(patient2);
        dataModel.setOfflineLoginAccount(careProvider);
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

//    // TODO: for local testing only
//    @Test
//    public void testAssignPatient() {
//        solo.clickOnView(solo.getView(R.id.care_provider_home_fab));
//        solo.waitForActivity(CreateAccountActivity.class);
//        solo.assertCurrentActivity("Wrong Activity", CreateAccountActivity.class);
//    }

    @Test
    public void onCreate() {
        solo.assertCurrentActivity("Wrong Activity", CareProviderHomeActivity.class);
        solo.waitForActivity(CareProviderHomeActivity.class);
        assertTrue(solo.searchText("John"));
        assertTrue(solo.searchText("Billy"));
    }

    @Test
    public void testEditAccount() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem(getActivity().getResources().getString(R.string.edit_account));
        solo.waitForActivity(EditCareProviderAccountActivity.class);
        solo.assertCurrentActivity("Wrong Activity", EditCareProviderAccountActivity.class);
    }

    @Test
    public void testDeleteProblem() {
        solo.clickLongOnView(solo.getText("John"));
        solo.clickOnText(getActivity().getResources().getString(R.string.remove_patient));
        solo.clickOnButton(getActivity().getResources().getString(R.string.cancel));
    }

    @Test
    public void testDeleteProblemCancel() {
        solo.clickLongOnView(solo.getText("John"));
        solo.clickOnText(getActivity().getResources().getString(R.string.remove_patient));
        solo.clickOnButton(getActivity().getResources().getString(R.string.remove));
    }

    @Test
    public void testViewPatient() {
        solo.clickLongOnView(solo.getText("John"));
        solo.clickOnText(getActivity().getResources().getString(R.string.view_account));
        solo.waitForActivity(CareProviderProblemListActivity.class);
        solo.clickOnButton(getActivity().getResources().getString(R.string.ok));
    }

    @Test
    public void testQRCodeDisplay() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem(getActivity().getResources().getString(R.string.display_qr_code));
        solo.waitForActivity(DisplayQRCodeActivity.class);
        solo.assertCurrentActivity("Wrong Activity", DisplayQRCodeActivity.class);
    }
}