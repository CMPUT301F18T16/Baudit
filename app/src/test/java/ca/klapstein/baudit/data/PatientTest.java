package ca.klapstein.baudit.data;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class PatientTest {

    private String usernameInput;
    private String passwordInput;
    private String emailInput;
    private String phoneInput;
    private ContactInfo contactInfo;
    private PhoneNumber phoneNumber;
    private Email email;
    private Username username;
    private Password password;
    private Patient patient;


    public PatientTest(String usernameInput, String passwordInput, String emailInput, String phoneInput) {
        this.usernameInput = usernameInput;
        this.passwordInput = passwordInput;
        this.emailInput = emailInput;
        this.phoneInput = phoneInput;
        this.contactInfo = new ContactInfo();
        this.phoneNumber = new PhoneNumber(this.phoneInput);
        this.email = new Email(this.emailInput);
        contactInfo.setEmail(this.email);
        contactInfo.setPhoneNumber(this.phoneNumber);
        this.username = new Username(this.usernameInput);
        this.password = new Password(this.passwordInput);
        this.patient = new Patient(this.username, this.password, this.contactInfo);
    }

    @Parameterized.Parameters
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

        ContactInfo newContactInfo = new ContactInfo();
        PhoneNumber newPhoneNumber = new PhoneNumber("123-456-7890");
        Email newEmail = new Email("newemail@example.com");
        newContactInfo.setEmail(newEmail);
        newContactInfo.setPhoneNumber(newPhoneNumber);
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