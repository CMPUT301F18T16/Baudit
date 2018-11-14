package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


@RunWith(Parameterized.class)
public class CareProviderTest {

    private ContactInfo contactInfo;
    private Username username;
    private Password password;
    private CareProvider careProvider;

    private Patient patient0;
    private Patient patient1;

    public CareProviderTest(String usernameInput, String passwordInput, String emailInput, String phoneInput) {

        this.contactInfo = new ContactInfo();
        contactInfo.setEmail(new Email(emailInput));
        contactInfo.setPhoneNumber(new PhoneNumber(phoneInput));
        this.username = new Username(usernameInput);
        this.password = new Password(passwordInput);
        this.careProvider = new CareProvider(this.username, this.password, this.contactInfo);

        ContactInfo patient0ContactInfo = new ContactInfo();
        patient0ContactInfo.setEmail(new Email("patient0@hotmail.com"));
        patient0ContactInfo.setPhoneNumber(new PhoneNumber("123-456-7890"));
        this.patient0 = new Patient(new Username("patient0"), new Password("password0"), patient0ContactInfo);

        ContactInfo patient1ContactInfo = new ContactInfo();
        patient1ContactInfo.setEmail(new Email("patient1@hotmail.com"));
        patient1ContactInfo.setPhoneNumber(new PhoneNumber("012-345-5678"));
        this.patient1 = new Patient(new Username("patient0"), new Password("password0"), patient1ContactInfo);

        careProvider.getAssignedPatientTreeSet().add(patient0);
        careProvider.getAssignedPatientTreeSet().add(patient1);
    }

    @Parameterized.Parameters
    public static Collection careProviderData() {
        return Arrays.asList(new Object[][]{
                {"nameuser", "password", "email@example.com", "780-123-1234"}
        });
    }

    @Test
    public void testCareProviderConstructor() {
        assertNotNull(this.careProvider);
    }

    @Test
    public void testGetAssignedPatients(){

        assertTrue(careProvider.getAssignedPatientTreeSet().contains(patient0));
        assertTrue(careProvider.getAssignedPatientTreeSet().contains(patient1));
    }

    @Test
    public void testGetCareProviderUsername() {
        assertEquals(this.username, careProvider.getUsername());
    }

    @Test
    public void testSetCareProviderUsername() {
        Username newusername = new Username("newusername");
        careProvider.setUsername(newusername);
        assertEquals(careProvider.getUsername(), newusername);
    }

    @Test
    public void testCareProvidertContactInfo() {
        assertEquals(careProvider.getContactInfo(), contactInfo);
    }

    @Test
    public void testSetCareProviderContactInfo() {
        ContactInfo newContactInfo = new ContactInfo();
        PhoneNumber newPhoneNumber = new PhoneNumber("123-456-7890");
        Email newEmail = new Email("newemail@example.com");
        newContactInfo.setEmail(newEmail);
        newContactInfo.setPhoneNumber(newPhoneNumber);
        careProvider.setContactInfo(newContactInfo);
        assertEquals(careProvider.getContactInfo(), newContactInfo);
    }

    @Test
    public void testGetCareProviderPassword() {
        assertEquals(careProvider.getPassword(), password);
    }

    @Test
    public void testSetCareProviderPassword() {
        Password newPassword = new Password("newpassword");
        careProvider.setPassword(newPassword);
        assertEquals(careProvider.getPassword(), newPassword);
    }
}