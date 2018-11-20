package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO: improve these tests they don't actually cover failure or edge cases well
public class DataModelTest {

    private Context context;
    private DataModel dataModel;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        dataModel = new DataModel(context);
    }

    @After
    public void tearDown() {
        context = null;
        // clean out the share prefs login data
        // TODO: empty shared prefs
        dataModel.clearLoginAccountUserName();
        dataModel = null;
    }

    @Test
    public void validateLogin() {
        // TODO: implement
    }

    @Test
    public void uniqueID() {
        // TODO: implement
    }

    @Test
    public void commitPatient() {
        Patient patient3 = new Patient(
                new Username("TESTPatient1"), new Password("foobar123"),
                new ContactInfo(new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        dataModel.commitPatient(patient3);
    }

    @Test
    public void commitPatientTreeSetNull() {
        // commit an patientTreeSet this does nothing to the remote
        PatientTreeSet patientTreeSet = new PatientTreeSet();
        dataModel.commitPatientTreeSet(patientTreeSet);
    }

    @Test
    public void commitPatientTreeSet() {
        PatientTreeSet patientTreeSet = new PatientTreeSet();
        Patient patient1 = new Patient(
                new Username("TESTPatient2"), new Password("foobar123"),
                new ContactInfo(new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        patientTreeSet.add(patient1);
        Patient patient2 = new Patient(
                new Username("TESTPatient3"), new Password("foobar123"),
                new ContactInfo(new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        patientTreeSet.add(patient2);
        dataModel.commitPatientTreeSet(patientTreeSet);
    }

    @Test
    public void getPatientNull() {
        Patient patient = dataModel.getPatient(new Username("NONSUCHPATIENT"));
        assertNull(patient);
    }

    @Test
    public void getPatient() throws InterruptedException {
        this.commitPatientTreeSet();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        Patient patient1 = dataModel.getPatient(new Username("TESTPatient2"));
        assertNotNull(patient1);
        assertEquals(new Username("TESTPatient2"), patient1.getUsername());

        this.commitPatient();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        Patient patient3 = dataModel.getPatient(new Username("TESTPatient3"));
        assertNotNull(patient3);
        assertEquals(new Username("TESTPatient3"), patient3.getUsername());
    }

    @Test
    public void getPatients() {
        PatientTreeSet patientTreeSet = dataModel.getPatients();
        assertNotNull(patientTreeSet);
    }

    @Test
    public void getCareProviderNull() {
        CareProvider careProvider = dataModel.getCareProvider(new Username("NONSUCHCAREPROVIDER"));
        assertNull(careProvider);
    }

    @Test
    public void commitCareProvider() {
        CareProvider careProvider1 = new CareProvider(
                new Username("TESTCareProvider1"), new Password("foobar123"),
                new ContactInfo(new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        dataModel.commitCareProvider(careProvider1);
        CareProvider careProvider2 = new CareProvider(
                new Username("TESTCareProvider2"), new Password("foobar123"),
                new ContactInfo(new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        dataModel.commitCareProvider(careProvider2);
    }

    @Test
    public void getCareProvider() throws InterruptedException {
        this.commitCareProvider();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        CareProvider careProvider = dataModel.getCareProvider(new Username("TESTCareProvider1"));
        assertNotNull(careProvider);
        assertEquals(new Username("TESTCareProvider1"), careProvider.getUsername());
    }

    @Test
    public void uniqueIDTrue() throws InterruptedException {
        this.commitCareProvider();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        assertTrue(dataModel.uniqueID(new Username("UNIQUE_USERNAME")));
    }

    @Test
    public void uniqueIDFalse() throws InterruptedException {
        this.commitCareProvider();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        assertFalse(dataModel.uniqueID(new Username("TESTCareProvider2")));
    }

    @Test
    public void validateLoginSuccess() throws InterruptedException {
        this.commitPatient();
        this.commitCareProvider();
        Thread.sleep(10000);
        assertTrue(dataModel.validateLogin(new Username("TESTPatient1"), new Password("foobar123")));
        assertTrue(dataModel.validateLogin(new Username("TESTCareProvider1"), new Password("foobar123")));
    }

    @Test
    public void validateLoginFail() {
        assertFalse(dataModel.validateLogin(new Username("NONSUCH_ACCOUNT"), new Password("PASSWORD")));
    }

    @Test
    public void setLoginAccountUserName() {
        dataModel.setLoginAccountUserName(new Username("TESTUsername"));
        Username username = dataModel.getLoginAccountUsername();
        assertEquals("TESTUsername", username.toString());
    }

    @Test
    public void clearLoginAccountUserName() {
        dataModel.clearLoginAccountUserName();
        Username username = dataModel.getLoginAccountUsername();
        assertNull(username);
    }

    @Test
    public void getLoginAccountUserName() {
        this.commitCareProvider();
        this.commitPatient();

        dataModel.setLoginAccountUserName(new Username("TESTCareProvider1"));
        CareProvider careProvider = (CareProvider) dataModel.getLoggedInAccount();
        assertEquals("TESTCareProvider1", careProvider.getUsername().toString());
        assertNotNull(careProvider.getAssignedPatientTreeSet());

        dataModel.setLoginAccountUserName(new Username("TESTPatient1"));
        Patient patient = (Patient) dataModel.getLoggedInAccount();
        assertEquals("TESTPatient1", patient.getUsername().toString());
        assertNotNull(patient.getProblemTreeSet());
    }
}