package ca.klapstein.baudit.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneNumberTest {
    @Test
    public void testPhoneNumberConstructor() {
        PhoneNumber phoneNumber = new PhoneNumber("(780)4444444");
        assertNotNull(phoneNumber);
        assertEquals("7804444444", phoneNumber.getPhoneNumber());
    }

    @Test
    public void testGetPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("012-345-6789");
        assertNotNull(phoneNumber);
        assertEquals("0123456789", phoneNumber.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumberValid() {
        PhoneNumber phoneNumber = new PhoneNumber("(780) 555 5555");
        assertEquals("7805555555", phoneNumber.getPhoneNumber());
        phoneNumber.setPhoneNumber("780-555-5556");
        assertEquals("7805555556", phoneNumber.getPhoneNumber());
        phoneNumber.setPhoneNumber("780 555-5557");
        assertEquals("7805555557", phoneNumber.getPhoneNumber());
        phoneNumber.setPhoneNumber("(780)-555-5558");
        assertEquals("7805555558", phoneNumber.getPhoneNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhoneNumberInvalid() {
        new PhoneNumber("[780]-555-5555");
    }
}