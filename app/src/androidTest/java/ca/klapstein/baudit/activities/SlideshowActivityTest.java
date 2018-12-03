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
import static ca.klapstein.baudit.activities.SlideshowActivity.RECORD_PHOTO_PROBLEM_ID_FIELD;
import static ca.klapstein.baudit.activities.SlideshowActivity.RECORD_PHOTO_RECORD_ID_FIELD;

public class SlideshowActivityTest extends ActivityTestRule<SlideshowActivity> {

    private Solo solo;
    private DataModel dataModel;

    public SlideshowActivityTest() {
        super(SlideshowActivity.class);
    }

    @Before
    public void setUp() {
        dataModel = new DataModel(InstrumentationRegistry.getTargetContext());
        Patient patient = new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        );
        Problem problem = new Problem("test problem", "test problem description");
        Record record = new Record("test record");
        problem.getRecordTreeSet().add(record);
        patient.getProblemTreeSet().add(problem);
        // TODO: add test bitmap
        dataModel.setOfflineLoginAccount(patient);

        // setup an intent to present the first problem's first record's photos
        Intent intent = new Intent();
        intent.putExtra(RECORD_PHOTO_PROBLEM_ID_FIELD, 0);
        intent.putExtra(RECORD_PHOTO_RECORD_ID_FIELD, 0);
        super.launchActivity(intent);
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() {
        dataModel.clearOfflineLoginAccount();
        solo.finishOpenedActivities();
    }

    @Test
    public void onCreate() {
        solo.waitForActivity(SlideshowActivity.class);
        solo.assertCurrentActivity("Wrong Activity", SlideshowActivity.class);
    }
}