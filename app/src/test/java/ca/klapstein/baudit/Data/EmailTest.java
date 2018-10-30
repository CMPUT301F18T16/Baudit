package ca.klapstein.baudit.Data;

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
    public void test_EmailConstructor() {
        Email email = new Email("foo@example.com");
        assertNotNull(email);
        assertEquals(email.getEmail(), "foo@example.com");
    }

    @Test
    public void test_getEmail() {
        Email email = new Email("foo@example.com");
        assertNotNull(email.getEmail());
        assertEquals(email.getEmail(), "foo@example.com");
    }

    @Test
    public void test_validEmail() {
        assertTrue(Email.isValid("foo@example.com"));
    }

    @Test
    public void test_invalidEmail() {
        assertFalse(Email.isValid("foobar"));
    }
}