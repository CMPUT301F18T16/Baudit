package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {
    private ContactInfo contactInfo;

    @Before
    public void setUp() {
        contactInfo = new ContactInfo();
    }

    @After
    public void tearDown() {
    }

    private Email ExampleEmail(){
        return new Email("foo@example.com");
    }

    private PhoneNumber ExamplePhoneNumber(){
        return new PhoneNumber("111-111-1111");
    }

    @Test
    public void getPhone() {
        assertNull(contactInfo.getPhoneNumber());
        contactInfo.setPhoneNumber(ExamplePhoneNumber());
        assertEquals(ExamplePhoneNumber(), contactInfo.getPhoneNumber());
    }

    @Test
    public void setPhone() {
        assertNull(contactInfo.getPhoneNumber());
        contactInfo.setPhoneNumber(ExamplePhoneNumber());
        assertEquals(ExamplePhoneNumber(), contactInfo.getPhoneNumber());
    }

    @Test
    public void getEmail() {
        assertNull(contactInfo.getEmail());
        contactInfo.setEmail(ExampleEmail());
        assertEquals(ExampleEmail(), contactInfo.getEmail());
    }

    @Test
    public void setEmail() {
        assertNull(contactInfo.getEmail());
        contactInfo.setEmail(ExampleEmail());
        assertEquals(ExampleEmail(), contactInfo.getEmail());
    }
}