package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CareProviderTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStub() {
        // TODO: write tests
    }

    @Test
    public void editContactInfo(){
        CareProvider cp = new CareProvider();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        cp.setContactInfo(contactInfo);

        assertTrue(cp.getContactInfo().getEmail().getEmail().equals("John@hotmail.com"));
        assertTrue(cp.getContactInfo().getPhoneNumber().getPhoneNumber().equals("780-123-4567"));

        email.setEmail("NotJohn@hotmail.com");
        contactInfo.setEmail(email);
        number.setPhoneNumber("123-456-7890");

        assertTrue(cp.getContactInfo().getEmail().getEmail().equals("NotJohn@hotmail.com"));
        assertTrue(cp.getContactInfo().getPhoneNumber().getPhoneNumber().equals("123-456-7890"));
    }

    @Test
    public void attemptLogin(){
        CareProvider cp = new CareProvider();
        Username username = new Username("John");
        Password password = new Password("hidden");
        cp.setUsername(username);
        cp.setPassword(password);

        assertFalse(cp.attemptLogin(new Username("Wrong"), new Password("Wrong")));
        assertFalse(cp.attemptLogin(new Username("Wrong"), new Password("hidden")));
        assertFalse(cp.attemptLogin(new Username("John"), new Password("Wrong")));
        assertTrue(cp.attemptLogin(new Username("John"), new Password("hidden")));
    }

    @Test
    public void getUsername() {
        CareProvider cp = new CareProvider();
        Username username = new Username("John");

        cp.setUsername(username);
        assertTrue(cp.getUsername().equals(username));
    }

    @Test
    public void setUsername() {
        CareProvider cp = new CareProvider();
        Username username = new Username("John");

        cp.setUsername(username);
        assertTrue(cp.getUsername().equals(username));

    }

    @Test
    public void getContactInfo() {
        CareProvider cp = new CareProvider();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        cp.setContactInfo(contactInfo);

        assertTrue(cp.getContactInfo().equals(contactInfo));
        assertTrue(cp.getContactInfo().getEmail().equals(email));
        assertTrue(cp.getContactInfo().getPhoneNumber().equals(number));

    }

    @Test
    public void setContactInfo() {
        CareProvider cp = new CareProvider();
        ContactInfo contactInfo = new ContactInfo();

        Email email = new Email("John@hotmail.com");
        contactInfo.setEmail(email);
        PhoneNumber number = new PhoneNumber("780-123-4567");
        contactInfo.setPhoneNumber(number);
        cp.setContactInfo(contactInfo);

        assertTrue(cp.getContactInfo().equals(contactInfo));
        assertTrue(cp.getContactInfo().getEmail().equals(email));
        assertTrue(cp.getContactInfo().getPhoneNumber().equals(number));
    }

    @Test
    public void getPassword() {
        CareProvider cp = new CareProvider();
        Password password = new Password("hidden");

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }

    @Test
    public void setPassword() {
        CareProvider cp = new CareProvider();
        Password password = new Password("hidden");

        cp.setPassword(password);
        assertTrue(cp.getPassword().equals(password));
    }
}