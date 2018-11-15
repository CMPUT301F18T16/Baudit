package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {
    private ContactInfo contactInfo;

    @Before
    public void setUp() {
        contactInfo = new ContactInfo(ExampleEmail1(), ExamplePhoneNumber1());
    }

    @After
    public void tearDown() {
        contactInfo = null;
    }

    private Email ExampleEmail1() {
        return new Email("foo@example.com");
    }

    private Email ExampleEmail2() {
        return new Email("bar@example.com");
    }

    private PhoneNumber ExamplePhoneNumber1() {
        return new PhoneNumber("111-111-1111");
    }

    private PhoneNumber ExamplePhoneNumber2() {
        return new PhoneNumber("222-222-2222");
    }

    @Test
    public void getPhone() {
        assertNotNull(contactInfo.getPhoneNumber());
        assertEquals(ExamplePhoneNumber1(), contactInfo.getPhoneNumber());
    }

    @Test
    public void setPhone() {
        assertEquals(ExamplePhoneNumber1(), contactInfo.getPhoneNumber());
        contactInfo.setPhoneNumber(ExamplePhoneNumber2());
        assertEquals(ExamplePhoneNumber2(), contactInfo.getPhoneNumber());
        assertNotEquals(ExamplePhoneNumber1(), ExamplePhoneNumber2());
    }

    @Test
    public void getEmail() {
        assertNotNull(contactInfo.getEmail());
        assertEquals(ExampleEmail1(), contactInfo.getEmail());
    }

    @Test
    public void setEmail() {
        assertEquals(ExampleEmail1(), contactInfo.getEmail());
        contactInfo.setEmail(ExampleEmail2());
        assertEquals(ExampleEmail2(), contactInfo.getEmail());
        assertNotEquals(ExampleEmail1(), ExampleEmail2());
    }
}