package ca.klapstein.baudit.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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

    public CareProviderTest(String usernameInput, String emailInput, String phoneInput) {

        this.contactInfo = new ContactInfo(new Email(emailInput), new PhoneNumber(phoneInput));
        this.username = new Username(usernameInput);
        this.careProvider = new CareProvider(this.username, this.contactInfo);

        ContactInfo patient0ContactInfo = new ContactInfo(new Email("patient0@hotmail.com"), new PhoneNumber("123-456-7890"));
        this.patient0 = new Patient(new Username("patient0"), patient0ContactInfo);

        ContactInfo patient1ContactInfo = new ContactInfo(new Email("patient1@hotmail.com"), new PhoneNumber("012-345-5678"));
        this.patient1 = new Patient(new Username("patient0"), patient1ContactInfo);

        careProvider.getAssignedPatientTreeSet().add(patient0);
        careProvider.getAssignedPatientTreeSet().add(patient1);
    }

    @Parameterized.Parameters
    public static Collection careProviderData() {
        return Arrays.asList(new Object[][]{
                {"nameuser", "email@example.com", "780-123-1234"}
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
        ContactInfo newContactInfo = new ContactInfo(new Email("newemail@example.com"), new PhoneNumber("123-456-7890"));
        careProvider.setContactInfo(newContactInfo);
        assertEquals(careProvider.getContactInfo(), newContactInfo);
    }

}