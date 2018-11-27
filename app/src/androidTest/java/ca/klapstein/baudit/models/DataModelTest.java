package ca.klapstein.baudit.models;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import ca.klapstein.baudit.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        dataModel.clearOfflineLoginAccount();
        context = null;
        dataModel = null;
    }

    @Test
    public void commitPatient() {
        Patient patient1 = new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        dataModel.commitPatient(patient1);
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
        Patient patient2 = new Patient(
                new Username("TESTPatient2"),
                new ContactInfo("Patient", "Two", new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        patientTreeSet.add(patient2);
        Patient patient3 = new Patient(
                new Username("TESTPatient3"),
                new ContactInfo("Patient", "Three", new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        patientTreeSet.add(patient3);
        dataModel.commitPatientTreeSet(patientTreeSet);
    }

    @Test
    public void getPatientNull() {
        Patient patient = dataModel.getPatient(new Username("NONSUCHPATIENT"));
        assertNull(patient);
    }

    @Test
    public void getPatient() throws InterruptedException {
        this.commitPatient();
        // wait for remote elastic search cluster to settle
        Thread.sleep(10000);
        Patient patient1 = dataModel.getPatient(new Username("TESTPatient1"));
        assertNotNull(patient1);
        assertEquals(new Username("TESTPatient1"), patient1.getUsername());
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
        CareProvider careProvider = new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor","Strange", new Email("foobar@example.com"), new PhoneNumber("111-111-1111"))
        );
        dataModel.commitCareProvider(careProvider);
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
        assertFalse(dataModel.uniqueID(new Username("TESTCareProvider1")));
    }

    @Test
    public void validateLoginPatientSuccess() throws InterruptedException {
        this.commitPatient();
        Thread.sleep(10000);
        assertNotNull(dataModel.validateLogin(new Username("TESTPatient1")));
    }

    @Test
    public void validateLoginCareProviderSuccess() throws InterruptedException {
        this.commitCareProvider();
        Thread.sleep(10000);
        assertNotNull(dataModel.validateLogin(new Username("TESTCareProvider1")));
    }

    @Test
    public void validateLoginFail() {
        assertNull(dataModel.validateLogin(new Username("NONSUCH_ACCOUNT")));
    }

    @Test
    public void setLoginAccountPatient() {
        dataModel.setOfflineLoginAccount(new Patient(
                new Username("TESTPatient1"),
                new ContactInfo("John", "Smith", new Email("patient@example.com"), new PhoneNumber("111-111-1111"))
        ));
        Account account = dataModel.getLoggedInAccount();
        assertNotNull(account);
        assertEquals("TESTPatient1", account.getUsername().toString());
    }

    @Test
    public void setLoginAccountCareProvider() {
        dataModel.setOfflineLoginAccount(new CareProvider(
                new Username("TESTCareProvider1"),
                new ContactInfo("Doctor", "Strange", new Email("cp@example.com"), new PhoneNumber("111-111-1111"))
        ));
        Account account = dataModel.getLoggedInAccount();
        assertNotNull(account);
        assertEquals("TESTCareProvider1", account.getUsername().toString());
    }

    @Test
    public void clearLoginAccount() {
        dataModel.clearOfflineLoginAccount();
        Account account = dataModel.getLoggedInAccount();
        assertNull(account);

        CareProvider careProvider = dataModel.getLoggedInCareProvider();
        assertNull(careProvider);

        Patient patient = dataModel.getLoggedInPatient();
        assertNull(patient);
    }

    @Test
    public void getLoggedInAccountCareProvider() {
        this.commitCareProvider();
        this.setLoginAccountCareProvider();
        CareProvider careProvider = (CareProvider) dataModel.getLoggedInAccount();
        assertNotNull(careProvider);
        assertEquals("TESTCareProvider1", careProvider.getUsername().toString());
        assertNotNull(careProvider.getAssignedPatientTreeSet());
    }

    @Test
    public void getLoggedInAccountPatient() {
        this.commitPatient();
        this.setLoginAccountPatient();
        Patient patient = (Patient) dataModel.getLoggedInAccount();
        assertNotNull(patient);
        assertEquals("TESTPatient1", patient.getUsername().toString());
        assertNotNull(patient.getProblemTreeSet());
    }
}