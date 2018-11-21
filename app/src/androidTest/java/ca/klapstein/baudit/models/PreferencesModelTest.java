package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PreferencesModelTest {

    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();

        // clear all prefs
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, null);
        PreferencesModel.saveSharedPreferencesPatient(context, null);
        PreferencesModel.saveSharedPreferencesCareProvider(context, null);
        PreferencesModel.saveSharedPreferencesLoginAccount(context, null);
    }

    @After
    public void tearDown() {
        // TODO: empty shared prefs

        // clear all prefs
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, null);
        PreferencesModel.saveSharedPreferencesPatient(context, null);
        PreferencesModel.saveSharedPreferencesCareProvider(context, null);
        PreferencesModel.saveSharedPreferencesLoginAccount(context, null);
        context = null;
    }

    @Test
    public void saveSharedPreferencesPatientTreeSet() {
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, new PatientTreeSet());
    }

    @Test
    public void loadSharedPreferencesPatientTreeSet() {
        this.saveSharedPreferencesPatientTreeSet();
        PatientTreeSet patientTreeSet = PreferencesModel.loadSharedPreferencesPatientTreeSet(context);
        assertNotNull(patientTreeSet);
    }

    @Test
    public void loadSharedPreferencesEmptyPatientTreeSet() {
        PreferencesModel.saveSharedPreferencesPatientTreeSet(context, null);
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
        this.saveSharedPreferencesCareProvider();
        CareProvider careProvider = PreferencesModel.loadSharedPreferencesCareProvider(context);
        assertNotNull(careProvider);
        assertEquals("TESTCareProvider1", careProvider.getUsername().toString());
    }

    @Test
    public void saveSharedPreferencesLoginAccountPatient() {
        PreferencesModel.saveSharedPreferencesLoginAccount(context, new Patient(
                new Username("TESTPatient1"), new Password("foobar123"),
                new ContactInfo(new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
    }

    @Test
    public void saveSharedPreferencesLoginAccountCareProvider() {
        PreferencesModel.saveSharedPreferencesLoginAccount(context, new CareProvider(
                new Username("TESTCareProvider1"), new Password("foobar123"),
                new ContactInfo(new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
    }
    @Test
    public void loadSharedPreferencesLoginAccountPatient() {
        this.saveSharedPreferencesLoginAccountPatient();
        Account account = PreferencesModel.loadSharedPreferencesLoginAccount(context);
        assertNotNull(account);
        assertEquals("TESTPatient1", account.getUsername().toString());
    }

    @Test
    public void loadSharedPreferencesLoginAccountCareProvider() {
        this.saveSharedPreferencesLoginAccountCareProvider();
        Account account = PreferencesModel.loadSharedPreferencesLoginAccount(context);
        assertNotNull(account);
        assertEquals("TESTCareProvider1", account.getUsername().toString());
    }
}