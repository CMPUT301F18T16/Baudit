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

        Patient patient = new Patient(
                new Username("ThisIsATest"),
                new ContactInfo("Test", "McTest", new Email("test@example.com"), new PhoneNumber("7805551234")));

        //  create a first problem with one record with a geo location
        Problem problem1 = new Problem("First problem", "I can't feel my face");
        Record record1 = new Record("First occurrence", "This first happened on a Friday");
        record1.setGeoLocation(new GeoLocation(53.524074, -113.526378));
        problem1.getRecordTreeSet().add(record1);
        patient.getProblemTreeSet().add(problem1);

        // create a second problem with two records with geo locations
        Problem problem2 = new Problem("Second problem", "I still can't feel my face");
        Record record2 = new Record("Second occurrence", "This again on Saturday");
        record2.setGeoLocation(new GeoLocation(53.527288, -113.529346));
        problem2.getRecordTreeSet().add(record2);
        Record record3 = new Record("Third occurrence", "This happened on a Sunday");
        record3.setGeoLocation(new GeoLocation(53.515232, -113.481288));
        problem2.getRecordTreeSet().add(record3);
        patient.getProblemTreeSet().add(problem2);

        // create a third problem with one record **without** a geolocation
        Problem problem3 = new Problem("Third problem", "I still can't feel my face");
        Record record4 = new Record("Fourth occurrence", "This again on Saturday");
        problem2.getRecordTreeSet().add(record4);
        patient.getProblemTreeSet().add(problem3);

        // create a forth problem with no records
        Problem problem4 = new Problem("Fourth problem", "I still can't feel my face");
        patient.getProblemTreeSet().add(problem4);

        dataModel.setOfflineLoginAccount(patient);
        super.launchActivity(new Intent());
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void testOnCreate() {
        solo.assertCurrentActivity("Wrong Activity", MapAllProblemsActivity.class);
        solo.waitForActivity(solo.getCurrentActivity().toString());
    }
}
