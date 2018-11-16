package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PreferencesModelTest {

    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() {
        context = null;
    }

    @Test
    public void saveSharedPreferencesPatientTreeSet() {
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, new PatientTreeSet());
    }

    @Test
    public void loadSharedPreferencesPatientTreeSet() {
        // this will return an empty patientTreeSet
        PatientTreeSet patientTreeSet = PreferencesModel.loadSharedPreferencesPatientTreeSet(context);
        assertNotNull(patientTreeSet);
    }

    @Test
    public void saveSharedPreferencesCareProvider() {
        CareProvider careProvider = new CareProvider(
                new Username("CareProvider"), new Password("foobar123"),
                new ContactInfo(new Email("patient0@hotmail.com"), new PhoneNumber("123-456-7890"))
        );
        PreferencesModel.saveSharedPreferencesCareProvider(context, careProvider);
    }

    @Test
    public void loadSharedPreferencesCareProvider() {
        CareProvider careProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        assertNull(careProvider);
    }
}