package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PasswordTest {
    @Test
    public void test_validPassword() {
        assertTrue(Password.isValid("abcd1234"));
        assertTrue(Password.isValid("password"));
        assertTrue(Password.isValid("011235813"));
    }

    @Test
    public void test_invalidPassword() {
        assertFalse(Password.isValid("password!"));
        assertFalse(Password.isValid("short"));
        assertFalse(Password.isValid("thisPasswordIsWayTooExcessive"));
    }

    @Test
    public void testPasswordConstructor() {
        Password password = new Password("passwordConstruct");
        assertNotNull(password);
        assertEquals("passwordConstruct", password.getPassword());
    }

    @Test
    public void testGetPassword() {
        Password password = null;

        try {
            password = new Password("passwordGet");
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(password);
        assertEquals("passwordGet", password.getPassword());
    }

    @Test
    public void testSetPasswordValid() {
        try {
            Password password = new Password("abcd1234");
            assertEquals("abcd1234", password.getPassword());
            password.setPassword("password");
            assertEquals("password", password.getPassword());
            password.setPassword("011235813");
            assertEquals("011235813", password.getPassword());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testSetPasswordInvalid() {
        boolean success = false;
        try {
            new Password("@bad!password");
            success = true;
        } catch (IllegalArgumentException e) {}

        try {
            new Password("");
            success = true;
        } catch (IllegalArgumentException e) {}

        try {
            new Password("thisPasswordIsWayTooExcessive");
            success = true;
        } catch (IllegalArgumentException e) {}

        if (success) {
            fail();
        }
    }
}