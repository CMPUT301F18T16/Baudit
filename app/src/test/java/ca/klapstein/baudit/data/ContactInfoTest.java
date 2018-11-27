package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ContactInfoTest {

    @Mock
    private Email mockEmail;
    @Mock
    private PhoneNumber mockPhoneNumber;


    private ContactInfo contactInfo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contactInfo = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1());
    }

    @After
    public void tearDown() {
        contactInfo = null;
    }

    private String getExampleFirstName1() { return "John";}

    private String getExampleFirstName2() { return "Jane";}

    private String getExampleLastName1() { return "Smith";}

    private String getExampleLastName2() { return "Doe";}

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

    @Test
    public void testHashCodeSame() {
        ContactInfo contactInfo1 = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1()
        );
        ContactInfo contactInfo2 = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1()
        );
        assertEquals(contactInfo1.hashCode(), contactInfo2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        ContactInfo contactInfo1 = new ContactInfo(
                getExampleFirstName1(),
                getExampleLastName1(),
                getExampleEmail1(),
                getExamplePhoneNumber1()
        );
        ContactInfo contactInfo2 = new ContactInfo(
                getExampleFirstName2(),
                getExampleLastName2(),
                getExampleEmail2(),
                getExamplePhoneNumber2()
        );
        assertNotEquals(contactInfo1.hashCode(), contactInfo2.hashCode());
    }

    @Test
    public void equals() {
        ContactInfo contactInfo1 = new ContactInfo(
                this.getExampleFirstName1(),
                this.getExampleLastName1(),
                mockEmail,
                mockPhoneNumber
        );
        ContactInfo contactInfo2 = new ContactInfo(
                this.getExampleFirstName1(),
                this.getExampleLastName1(),
                mockEmail,
                mockPhoneNumber
        );
        assertTrue(contactInfo1.equals(contactInfo2));
    }

    @Test
    public void neverEqualsNull() {
        assertFalse(new ContactInfo(
                this.getExampleFirstName1(),
                this.getExampleLastName1(),
                mockEmail,
                mockPhoneNumber
        ).equals(null));
    }

    @Test
    public void equalsOtherObject() {
        assertFalse(new ContactInfo(
                this.getExampleFirstName1(),
                this.getExampleLastName1(),
                mockEmail,
                mockPhoneNumber
        ).equals(new Object()));
    }
}