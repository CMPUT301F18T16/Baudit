package ca.klapstein.baudit.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.*;


@RunWith(Parameterized.class)
public class CareProviderTest {

    private ContactInfo contactInfo;
    private Username username;
    private CareProvider careProvider;

    private Patient patient0;
    private Patient patient1;

    public CareProviderTest(String firstName, String lastName, String usernameInput, String emailInput, String phoneInput) {

        this.contactInfo = new ContactInfo(
            firstName,
            lastName,
            new Email(emailInput),
            new PhoneNumber(phoneInput)
        );
        this.username = new Username(usernameInput);
        this.careProvider = new CareProvider(this.username, this.contactInfo);

        ContactInfo patient0ContactInfo = new ContactInfo("John", "Doe", new Email("patient0@hotmail.com"), new PhoneNumber("123-456-7890"));
        this.patient0 = new Patient(new Username("patient0"), patient0ContactInfo);

        ContactInfo patient1ContactInfo = new ContactInfo("Jane", "Doe", new Email("patient1@hotmail.com"), new PhoneNumber("012-345-5678"));
        this.patient1 = new Patient(new Username("patient0"), patient1ContactInfo);

        careProvider.getAssignedPatientTreeSet().add(patient0);
        careProvider.getAssignedPatientTreeSet().add(patient1);
    }

    @Parameters
    public static Collection careProviderData() {
        return Arrays.asList(new Object[][]{
                {"firstName", "lastName", "username", "email@example.com", "780-123-1234"}
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
        Username newUsername = new Username("new username");
        careProvider.setUsername(newUsername);
        assertEquals(careProvider.getUsername(), newUsername);
    }

    @Test
    public void testCareProviderContactInfo() {
        assertEquals(careProvider.getContactInfo(), contactInfo);
    }

    @Test
    public void testSetCareProviderContactInfo() {
        ContactInfo newContactInfo = new ContactInfo("newFirstName", "newLastName", new Email("newemail@example.com"), new PhoneNumber("123-456-7890"));
        careProvider.setContactInfo(newContactInfo);
        assertEquals(careProvider.getContactInfo(), newContactInfo);
    }

}