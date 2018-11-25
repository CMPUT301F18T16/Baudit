package ca.klapstein.baudit.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PatientTest {

    private Username username;
    private Patient patient;
    private ContactInfo contactInfo;

    @Mock
    private BodyPhoto bodyPhoto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public PatientTest(String usernameInput, String emailInput,
                       String phoneInput) {
        Email email = new Email(emailInput);
        PhoneNumber phoneNumber = new PhoneNumber(phoneInput);
        this.contactInfo = new ContactInfo(email, phoneNumber);
        this.username = new Username(usernameInput);
        this.patient = new Patient(this.username, contactInfo);
    }

    @Parameters
    public static Collection patientData() {
        return Arrays.asList(new Object[][] {
                {"username", "email@example.com", "780-123-1234"}
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
        Username newusername = new Username("newusername");
        patient.setUsername(newusername);
        assertEquals(patient.getUsername(), newusername);
    }

    @Test
    public void testGetPatientContactInfo() {
        assertEquals(patient.getContactInfo(), contactInfo);
    }

    @Test
    public void testSetPatientContactInfo() {
        ContactInfo newContactInfo = new ContactInfo(new Email("newemail@example.com"), new PhoneNumber("123-456-7890"));
        patient.setContactInfo(newContactInfo);
        assertEquals(patient.getContactInfo(), newContactInfo);
    }

    @Test
    public void getBodyPhoto() {
        assertNull(patient.getBodyPhoto());
        patient.setBodyPhoto(bodyPhoto);
        assertNotNull(patient.getBodyPhoto());
    }

    @Test
    public void setBodyPhoto() {
        patient.setBodyPhoto(bodyPhoto);
        assertNotNull(patient.getBodyPhoto());
    }

    @Test
    public void getProblemTreeSet() {
        assertNotNull(patient.getProblemTreeSet());
        // make sure on init the patients ProblemTreeSet is empty
        assertEquals(0, patient.getProblemTreeSet().size());
    }
}