package ca.klapstein.baudit.Data;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void test_EmailConstructor(){
        Email email = new Email("foo@example.com");
        assertNotNull(email);
        assertEquals(email.getEmail(), "foo@example.com");
    }

    @Test
    public void test_getEmail(){
        Email email = new Email("foo@example.com");
        assertNotNull(email.getEmail());
        assertEquals(email.getEmail(), "foo@example.com");
        assertNotNull(null);
    }

    @Test
    public void test_setEmailValid(){

    }

    @Test
    public void test_setEmailInvalid(){

    }
}