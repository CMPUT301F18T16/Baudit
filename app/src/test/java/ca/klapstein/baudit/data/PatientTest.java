package ca.klapstein.baudit.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class PatientTest {

    private Username username;
    private Patient patient;
    private ContactInfo contactInfo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public PatientTest(String firstName, String lastName, String usernameInput, String emailInput,
                       String phoneInput) {
        this.contactInfo = new ContactInfo(
            firstName,
            lastName,
            new Email(emailInput),
            new PhoneNumber(phoneInput)
        );
        this.username = new Username(usernameInput);
        this.patient = new Patient(this.username, contactInfo);
    }

    @Parameters
    public static Collection patientData() {
        return Arrays.asList(new Object[][] {
                {"firstName", "lastName", "username", "email@example.com", "780-123-1234"}
        });
    }

    @Test
    public void testPatientConstructor() {
        assertNotNull(this.patient);
    }

    @Test
    public void testGetPatientUsername() {
        assertEquals(username, patient.getUsername());
    }

    @Test
    public void testSetPatientUsername() {
        Username newUsername = new Username("newUsername");
        patient.setUsername(newUsername);
        assertEquals(patient.getUsername(), newUsername);
    }

    @Test
    public void testGetPatientContactInfo() {
        assertEquals(patient.getContactInfo(), contactInfo);
    }

    @Test
    public void testSetPatientContactInfo() {
        ContactInfo newContactInfo = new ContactInfo("John","Smith", new Email("newemail@example.com"), new PhoneNumber("123-456-7890"));
        patient.setContactInfo(newContactInfo);
        assertEquals(patient.getContactInfo(), newContactInfo);
    }

    @Test
    public void getProblemTreeSet() {
        assertNotNull(patient.getProblemTreeSet());
        // make sure on init the patients ProblemTreeSet is empty
        assertEquals(0, patient.getProblemTreeSet().size());
    }
}