package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
                new Username("TESTCareProvider1"), new Password("foobar123"),
                new ContactInfo(new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        );
        PreferencesModel.saveSharedPreferencesCareProvider(context, careProvider);
    }

    @Test
    public void loadSharedPreferencesCareProvider() {
        CareProvider careProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        assertNull(careProvider);
    }

    @Test
    public void saveSharedPreferencesLoginAccountUsername() {
        PreferencesModel.saveSharedPreferencesLoginAccountUsername(context, new Username("TESTUsername"));
    }

    @Test
    public void loadSharedPreferencesLoginAccountUsername() {
        this.saveSharedPreferencesCareProvider();
        PreferencesModel.saveSharedPreferencesLoginAccountUsername(context, new Username("TESTUsername"));
        Username username = PreferencesModel.loadSharedPreferencesLoginAccountUsername(context);
        assertNotNull(username);
        assertEquals("TESTUsername", username.toString());
    }
}