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
        assertEquals(email.toString(), "foo@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmailConstructorInvalid() {
        new Email("foobar");
    }

    @Test
    public void getEmail() {
        Email email = new Email("foo@example.com");
        assertNotNull(email.toString());
        assertEquals(email.toString(), "foo@example.com");
    }

    @Test
    public void validEmail() {
        assertTrue(Email.isValid("foo@example.com"));
    }

    @Test
    public void invalidEmail() {
        assertFalse(Email.isValid("foobar"));
    }

    @Test
    public void setEmailValid() {
        Email email = new Email("foo@example.com");
        email.setEmail("bar@example.com");
        assertEquals("bar@example.com", email.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setEmailInvalid() {
        // TODO: parameterize
        Email email = new Email("foo@example.com");
        email.setEmail("foobar");
    }

    @Test
    public void equals() {
        assertTrue(new Email("foo@example.com").equals(new Email("foo@example.com")));
    }

    @Test
    public void neverEqualsNull() {
        assertFalse(new Email("foo@example.com").equals(null));
    }

    @Test
    public void equalsOtherObject() {
        assertFalse(new Email("foo@example.com").equals(new Object()));
    }

    @Test
    public void testHashCodeSame() {
        Email email1 = new Email("foo@example.com");
        Email email2 = new Email("foo@example.com");
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Email email1 = new Email("foo@example.com");
        Email email2 = new Email("bar@example.com");
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }
}