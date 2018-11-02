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


    public PatientTest(String usernameInput, String passwordInput, String emailInput, String phoneInput) {
        this.usernameInput = usernameInput;
        this.passwordInput = passwordInput;
        this.emailInput = emailInput;
        this.phoneInput = phoneInput;
    }

    @Parameterized.Parameters
    public static Collection patientData() {
        return Arrays.asList(new Object[][] {
                { "username", "password", "email@example.com", "780-1337-123" }
        });
    }

    @Test
    public void testPatientConstructor() {
        ContactInfo contactInfo = new ContactInfo();
        PhoneNumber phoneNumber = new PhoneNumber(phoneInput);
        Email email = new Email(emailInput);
        contactInfo.setEmail(email);
        contactInfo.setPhoneNumber(phoneNumber);
        Username userName = new Username(usernameInput);
        Password password = new Password(passwordInput);
        Patient patient = new Patient(userName, password, contactInfo);
    }

    @Test
    public void testGetPatientUsername() {
    }

    @Test
    public void testSetPatientUsername() {
    }

    @Test
    public void testGetPatientContactInfo() {
    }

    @Test
    public void testSetPatientContactInfo() {
    }

    @Test
    public void testGetPatientPassword() {
    }

    @Test
    public void testSetPatientPassword() {
    }
}