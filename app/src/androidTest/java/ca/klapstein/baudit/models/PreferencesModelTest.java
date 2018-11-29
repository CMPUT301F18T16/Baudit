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

    /**
     * Clear Android's shared preferences for Baudit.
     */
    private void clearPrefs() {
        PreferencesModel.saveSharedPreferencesPatient(context, null);
        PreferencesModel.saveSharedPreferencesCareProvider(context, null);
    }

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        clearPrefs();
    }

    @After
    public void tearDown() {
        clearPrefs();
        context = null;
    }

    @Test
    public void saveSharedPreferencesCareProvider() {
        CareProvider careProvider = new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
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
        PreferencesModel.saveSharedPreferencesPatient(context, new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("Patient", "Zero", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
    }

    @Test
    public void saveSharedPreferencesLoginAccountCareProvider() {
        PreferencesModel.saveSharedPreferencesCareProvider(context, new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
    }
    @Test
    public void loadSharedPreferencesLoginAccountPatient() {
        this.saveSharedPreferencesLoginAccountPatient();
        Account account = PreferencesModel.loadSharedPreferencesPatient(context);
        assertNotNull(account);
        assertEquals("TESTPatient1", account.getUsername().toString());
    }

    @Test
    public void loadSharedPreferencesLoginAccountCareProvider() {
        this.saveSharedPreferencesLoginAccountCareProvider();
        Account account = PreferencesModel.loadSharedPreferencesCareProvider(context);
        assertNotNull(account);
        assertEquals("TESTCareProvider1", account.getUsername().toString());
    }
}