package ca.klapstein.baudit.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import ca.klapstein.baudit.data.*;
import ca.klapstein.baudit.models.DataModel;
import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static java.lang.Thread.sleep;

@RunWith(AndroidJUnit4.class)
public class MapAllProblemsActivityTest extends ActivityTestRule<MapAllProblemsActivity> {

    private Solo solo;
    private DataModel dataModel;

    public MapAllProblemsActivityTest() {
        super(MapAllProblemsActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        dataModel.clearOfflineLoginAccount();

        //TODO: clean
        //create a test patient to store records and problems to be iterated
        Username testUsername = new Username("ThisIsATest");
        ContactInfo testContactInfo = new ContactInfo("Test", "McTest", new Email("test@gmail.com"), new PhoneNumber("7805551234"));
        Patient patient = new Patient(testUsername, testContactInfo);

        ProblemTreeSet problemTreeSet = patient.getProblemTreeSet();

        Problem problem1 = new Problem("First problem", "I can't feel my face");
        Problem problem2 = new Problem("Second problem", "I still can't feel my face");

        RecordTreeSet recordTreeSet1 = problem1.getRecordTreeSet();
        RecordTreeSet recordTreeSet2 = problem2.getRecordTreeSet();

        Record record1 = new Record("First occurrence", "This first happened on a Friday");
        Record record2 = new Record("Second occurrence", "This happened on a Saturday");
        Record record3 = new Record("Third occurrence", "This again on Saturday");
        Record record4 = new Record("Fourth occurrence", "This happened on a Sunday");

        record1.setGeoLocation(new GeoLocation(53.524074, -113.526378));
        record2.setGeoLocation(new GeoLocation(53.522849, -113.622665));
        record3.setGeoLocation(new GeoLocation(53.527288, -113.529346));
        record4.setGeoLocation(new GeoLocation(53.515232, -113.481288));

        recordTreeSet1.add(record1);
        recordTreeSet1 = problem1.getRecordTreeSet();
        recordTreeSet1.add(record2);
        problem1.setRecordTreeSet(recordTreeSet1);
        recordTreeSet2.add(record3);
        recordTreeSet2.add(record4);
        problem2.setRecordTreeSet(recordTreeSet1);
        problem2.setRecordTreeSet(recordTreeSet2);
        problemTreeSet.add(problem1);
        problemTreeSet.add(problem2);
        patient.setProblemTreeSet(problemTreeSet);

        dataModel.setOfflineLoginAccount(patient);
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        // TODO: disable for debugging
//        dataModel.clearOfflineLoginAccount();
//        solo.finishOpenedActivities();
    }

    @Test
    public void testOnCreate() throws InterruptedException {
        solo.assertCurrentActivity("Wrong Activity", MapAllProblemsActivity.class);
        sleep(11200);
    }
}
