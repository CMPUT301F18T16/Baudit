package ca.klapstein.baudit.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneNumberTest {
    @Test
    public void testPhoneNumberConstructor() {
        PhoneNumber phoneNumber = new PhoneNumber("(780)4444444");
        assertNotNull(phoneNumber);
        assertEquals("7804444444", phoneNumber.toString());
    }

    @Test
    public void testGetPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("012-345-6789");
        assertNotNull(phoneNumber);
        assertEquals("0123456789", phoneNumber.toString());
    }

    @Test
    public void testSetPhoneNumberValid() {
        PhoneNumber phoneNumber = new PhoneNumber("(780) 555 5555");
        assertEquals("7805555555", phoneNumber.toString());
        phoneNumber.setPhoneNumber("780-555-5556");
        assertEquals("7805555556", phoneNumber.toString());
        phoneNumber.setPhoneNumber("780 555-5557");
        assertEquals("7805555557", phoneNumber.toString());
        phoneNumber.setPhoneNumber("(780)-555-5558");
        assertEquals("7805555558", phoneNumber.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhoneNumberInvalid() {
        new PhoneNumber("[780]-555-5555");
    }


    @Test
    public void equals() {
        assertTrue(new PhoneNumber("111-111-1111").equals(new PhoneNumber("111-111-1111")));
    }

    @Test
    public void notEquals() {
        assertFalse(new PhoneNumber("111-111-1111").equals(new PhoneNumber("222-222-2222")));
    }

    @Test
    public void neverEqualsNull() {
        assertFalse(new PhoneNumber("111-111-1111").equals(null));
    }

    @Test
    public void equalsOtherObject() {
        assertFalse(new PhoneNumber("111-111-1111").equals(new Object()));
    }

    @Test
    public void testHashCodeSame() {
        PhoneNumber phoneNumber1 = new PhoneNumber("111-111-1111");
        PhoneNumber phoneNumber2 = new PhoneNumber("111-111-1111");
        assertEquals(phoneNumber1.hashCode(), phoneNumber2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        PhoneNumber phoneNumber1 = new PhoneNumber("111-111-1111");
        PhoneNumber phoneNumber2 = new PhoneNumber("222-222-2222");
        assertNotEquals(phoneNumber1.hashCode(), phoneNumber2.hashCode());
    }

}