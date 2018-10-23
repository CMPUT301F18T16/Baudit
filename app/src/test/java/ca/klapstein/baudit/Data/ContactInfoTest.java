package ca.klapstein.baudit.Data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {
    ContactInfo contactInfo;

    @Before
    public void setUp() throws Exception {
        contactInfo = new ContactInfo();
    }


    @Test
    public void getPhone() {
        assertNull(contactInfo.getPhoneNumber());
        contactInfo.setPhoneNumber("111-111-1111");
        assertEquals("111-111-1111", contactInfo.getPhoneNumber());
    }

    @Test
    public void setPhone() {
    }

    @Test
    public void getEmail() {
    }

    @Test
    public void setEmail() {
    }
}