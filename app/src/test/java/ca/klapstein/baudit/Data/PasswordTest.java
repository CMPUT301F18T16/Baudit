package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PasswordTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void setPassword() {
    }

    @Test
    public void test_validPassword() {
        assertTrue(Password.isValid("foobar"));
        assertTrue(Password.isValid("hunter1"));
    }

    @Test
    public void test_invalidPassword() {
        // TODO: implement
    }
}