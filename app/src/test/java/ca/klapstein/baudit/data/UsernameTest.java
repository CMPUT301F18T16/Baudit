package ca.klapstein.baudit.data;

import org.junit.Test;

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
    public void testSetUsername() {
        Username username = new Username("testUser");
        assertEquals("testUser", username.getUsername());
        username.setUsername("testUser1");
        assertEquals("testUser1", username.getUsername());
        username.setUsername("1234567890");
        assertEquals("1234567890", username.getUsername());
        username.setUsername("123test123");
        assertEquals("123test123", username.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUsernameInvalid1() {
        new Username("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUsernameInvalid2() {
        new Username("testUserThatIsWayTooLong");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUsernameInvalid3() {
        new Username("Ťéŝţ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUsernameInvalid4() {
        new Username("");
    }
}