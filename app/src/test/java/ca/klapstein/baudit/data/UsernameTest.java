package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UsernameTest {

    @Test
    public void testUsernameConstructor() {
        Username username = new Username("testUser");
        assertNotNull(username);
        assertEquals("testUser", username.getUsername());
    }

    @Test
    public void testGetUsername() {
        Username username = null;

        try {
            username = new Username("testUser");
        } catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(username);
        assertEquals("testUser", username.getUsername());
    }

    @Test
    public void testSetUsername() {
        try {
            Username username = new Username("testUser");
            assertEquals("testUser", username.getUsername());
            username.setUsername("testUser1");
            assertEquals("testUser1", username.getUsername());
            username.setUsername("1234567890");
            assertEquals("1234567890", username.getUsername());
            username.setUsername("123test123");
            assertEquals("123test123", username.getUsername());
        } catch (IllegalArgumentException e) {
            fail();
        }

    }

    @Test
    public void testSetUsernameInvalid() {
        boolean success = false;
        try {
            new Username("test");
            success = true;
        } catch (IllegalArgumentException e) {}

        try {
            new Username("testUserThatIsWayTooLong");
            success = true;
        } catch (IllegalArgumentException e) {}

        try {
            new Username("");
            success = true;
        } catch (IllegalArgumentException e) {}


        if (success) {
            fail();
        }
    }
}