package ca.klapstein.baudit.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class PatientTest {

    private Username username;
    private Password password;
    private Patient patient;
    private ContactInfo contactInfo;


    public PatientTest(String usernameInput, String passwordInput, String emailInput,
                       String phoneInput) {
        Email email = new Email(emailInput);
        PhoneNumber phoneNumber = new PhoneNumber(phoneInput);
        this.contactInfo = new ContactInfo(email, phoneNumber);
        this.username = new Username(usernameInput);
        this.password = new Password(passwordInput);
        this.patient = new Patient(this.username, this.password, contactInfo);
    }

    @Parameters
    public static Collection patientData() {
        return Arrays.asList(new Object[][] {
                { "username", "password", "email@example.com", "780-123-1234" }
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
    public void testGetPatientPassword() {
        assertEquals(patient.getPassword(), password);
    }

    @Test
    public void testSetPatientPassword() {
        Password newPassword = new Password("newpassword");
        patient.setPassword(newPassword);
        assertEquals(patient.getPassword(), newPassword);
    }
}