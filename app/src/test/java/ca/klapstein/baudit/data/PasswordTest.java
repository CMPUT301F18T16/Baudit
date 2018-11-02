package ca.klapstein.baudit.Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;

public class PasswordTest {

    @Test
    public void getPassword() {

        Password password = new Password("test1234");
        assertTrue(password.getPassword().equals("test1234"));
    }

    @Test
    public void testPasswordValid() {

        try{
            Password password0 = new Password("ABC123abc"); // viable password
            assertTrue(password0.getPassword().equals("ABC123abc"));
            Password password1 = new Password("abcdefghik1234567890");
            assertTrue(password1.getPassword().equals("abcdefghik1234567890"));
        } catch(IllegalArgumentException e){
            fail();
        }
    }

    public void testSetPasswordInvalid() {

        boolean success = true;

        try{
            Password password0 = new Password("test1234");
            assertFalse(password0.getPassword().equals("test123"));
            assertTrue(password0.getPassword() == null);
            Password password1 = new Password("abcdefghijk1234567890");
            assertFalse(password1.getPassword().equals("abcdefghijk1234567890"));
            assertTrue(password1.getPassword() == null);
        } catch (IllegalArgumentException e){
            success = false;
        }

        if (success){
            fail();
        }
    }
}