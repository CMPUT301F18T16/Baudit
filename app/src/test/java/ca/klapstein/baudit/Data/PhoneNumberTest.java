package ca.klapstein.baudit.Data;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneNumberTest {
    @Test
    public void test_PhoneNumberConstructor(){
        PhoneNumber phoneNumber = new PhoneNumber("111-111-1111");
        assertNotNull(phoneNumber);
        assertEquals(phoneNumber.getPhoneNumber(), "111-111-1111");
    }

    @Test
    public void test_getPhoneNumber(){
    }

    @Test
    public void test_setPhoneNumberValid(){
    }

    @Test
    public void test_setPhoneNumberInvalid(){
    }
}