package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getPassword() {

        Password password = new Password("test1234");
        assertTrue(password.getPassword().equals("test1234"));
    }

    @Test
    public void setPassword() {

        Password password0 = new Password("test123"); // password too short
        assertFalse(password0.getPassword().equals("test123"));
        assertTrue(password0.getPassword().equals(null));

        Password password1 = new Password("abcdefghijk1234567890"); // password too long
        assertFalse(password1.getPassword().equals("abcdefghijk1234567890"));
        assertTrue(password1.getPassword().equals(null));

        Password password2 = new Password("ABC123abc"); // viable password
        assertTrue(password1.getPassword().equals("ABC123abc"));
        assertFalse(password1.getPassword().equals(null));


    }
}