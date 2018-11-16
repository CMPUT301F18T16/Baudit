package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {
    private ContactInfo contactInfo;

    @Before
    public void setUp() {
        contactInfo = new ContactInfo(getExampleEmail1(), getExamplePhoneNumber1());
    }

    @After
    public void tearDown() {
        contactInfo = null;
    }

    private Email getExampleEmail1() {
        return new Email("foo@example.com");
    }

    private Email getExampleEmail2() {
        return new Email("bar@example.com");
    }

    private PhoneNumber getExamplePhoneNumber1() {
        return new PhoneNumber("111-111-1111");
    }

    private PhoneNumber getExamplePhoneNumber2() {
        return new PhoneNumber("222-222-2222");
    }

    @Test
    public void getPhone() {
        assertNotNull(contactInfo.getPhoneNumber());
        assertEquals(getExamplePhoneNumber1(), contactInfo.getPhoneNumber());
    }

    @Test
    public void setPhone() {
        assertEquals(getExamplePhoneNumber1(), contactInfo.getPhoneNumber());
        contactInfo.setPhoneNumber(getExamplePhoneNumber2());
        assertEquals(getExamplePhoneNumber2(), contactInfo.getPhoneNumber());
        assertNotEquals(getExamplePhoneNumber1(), getExamplePhoneNumber2());
    }

    @Test
    public void getEmail() {
        assertNotNull(contactInfo.getEmail());
        assertEquals(getExampleEmail1(), contactInfo.getEmail());
    }

    @Test
    public void setEmail() {
        assertEquals(getExampleEmail1(), contactInfo.getEmail());
        contactInfo.setEmail(getExampleEmail2());
        assertEquals(getExampleEmail2(), contactInfo.getEmail());
        assertNotEquals(getExampleEmail1(), getExampleEmail2());
    }
}