package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ContactInfoTest {

    private ContactInfo contactInfo1;
    private ContactInfo contactInfo2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contactInfo1 = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1());
        contactInfo2 = new ContactInfo(
                getExampleFirstName2(),
                getExampleLastName2(),
                getExampleEmail2(),
                getExamplePhoneNumber2());
    }

    @After
    public void tearDown() {
        contactInfo1 = null;
        contactInfo2 = null;
    }

    private String getExampleFirstName1() { return "John";}

    private String getExampleFirstName2() { return "Jane";}

    private String getExampleLastName1() { return "Smith";}

    private String getExampleLastName2() { return "Doe";}

    private Email getExampleEmail1() { return new Email("foo@example.com"); }

    private Email getExampleEmail2() { return new Email("bar@example.com"); }

    private PhoneNumber getExamplePhoneNumber1() { return new PhoneNumber("111-111-1111"); }

    private PhoneNumber getExamplePhoneNumber2() { return new PhoneNumber("222-222-2222"); }

    @Test
    public void getPhone() {
        assertNotNull(contactInfo1.getPhoneNumber());
        assertEquals(getExamplePhoneNumber1(), contactInfo1.getPhoneNumber());
    }

    @Test
    public void setPhone() {
        assertEquals(getExamplePhoneNumber1(), contactInfo1.getPhoneNumber());
        contactInfo1.setPhoneNumber(getExamplePhoneNumber2());
        assertEquals(getExamplePhoneNumber2(), contactInfo1.getPhoneNumber());
        assertNotEquals(getExamplePhoneNumber1(), getExamplePhoneNumber2());
    }

    @Test
    public void getEmail() {
        assertNotNull(contactInfo1.getEmail());
        assertEquals(getExampleEmail1(), contactInfo1.getEmail());
    }

    @Test
    public void setEmail() {
        assertEquals(getExampleEmail1(), contactInfo1.getEmail());
        contactInfo1.setEmail(getExampleEmail2());
        assertEquals(getExampleEmail2(), contactInfo1.getEmail());
        assertNotEquals(getExampleEmail1(), getExampleEmail2());
    }

    @Test
    public void getFirstName() {
        assertNotNull(contactInfo1.getFirstName());
        assertEquals(getExampleFirstName1(), contactInfo1.getFirstName());
    }

    @Test
    public void setFirstName() {
        assertEquals(getExampleFirstName1(), contactInfo1.getFirstName());
        contactInfo1.setFirstName(getExampleFirstName2());
        assertEquals(getExampleFirstName2(), contactInfo1.getFirstName());
        assertNotEquals(getExampleFirstName1(), getExampleFirstName2());
    }

    @Test
    public void getLastName() {
        assertNotNull(contactInfo1.getLastName());
        assertEquals(getExampleLastName1(), contactInfo1.getLastName());
    }

    @Test
    public void setLastName() {
        assertEquals(getExampleLastName1(), contactInfo1.getLastName());
        contactInfo1.setLastName(getExampleLastName2());
        assertEquals(getExampleLastName2(), contactInfo1.getLastName());
        assertNotEquals(getExampleLastName1(), getExampleLastName2());
    }

    @Test
    public void testHashCodeSame() {
        ContactInfo contactInfo1Same = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1()
        );
        assertEquals(contactInfo1.hashCode(), contactInfo1Same.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        assertNotEquals(contactInfo1.hashCode(), contactInfo2.hashCode());
    }

    @Test
    public void equals() {
        ContactInfo contactInfo1Same = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1()
        );
        assertTrue(contactInfo1.equals(contactInfo1Same));
    }

    @Test
    public void equalsNull() {
        assertFalse(contactInfo1.equals(null));
    }

    @Test
    public void equalsOtherObject() {
        assertFalse(contactInfo1.equals(new Object()));
    }
}