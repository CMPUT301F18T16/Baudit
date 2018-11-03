package ca.klapstein.baudit.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void EmailConstructor() {
        Email email = new Email("foo@example.com");
        assertNotNull(email);
        assertEquals(email.getEmail(), "foo@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmailConstructorInvalid() {
        new Email("foobar");
    }

    @Test
    public void getEmail() {
        Email email = new Email("foo@example.com");
        assertNotNull(email.getEmail());
        assertEquals(email.getEmail(), "foo@example.com");
    }

    @Test
    public void validEmail() {
        assertTrue(Email.isValid("foo@example.com"));
    }

    @Test
    public void invalidEmail() {
        assertFalse(Email.isValid("foobar"));
    }
}