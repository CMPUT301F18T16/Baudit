package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneNumberTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_PhoneNumberConstructor() {
        PhoneNumber phoneNumber = new PhoneNumber("111-111-1111");
        assertNotNull(phoneNumber);
        assertEquals(phoneNumber.getPhoneNumber(), "111-111-1111");
    }

    @Test
    public void test_getPhoneNumber() {
    }

    @Test
    public void test_setPhoneNumberValid() {
    }

    @Test
    public void test_setPhoneNumberInvalid() {
    }
}