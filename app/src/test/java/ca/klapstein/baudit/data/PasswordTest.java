package ca.klapstein.baudit.data;


import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
    public class PasswordTest {

    @RunWith(Parameterized.class)
    public static class ValidInputs {

        private String input;


        public ValidInputs(String input) {
            this.input = input;
        }

        @Parameters
        public static String[] validPasswords() {
            return new String[]{"abcd1234", "password", "011235813", "passwordConstruct", "passwordGet"};
        }

        @Test
        public void test_validPassword() {
            assertTrue(Password.isValid(input));
        }


        @Test
        public void testPasswordConstructor() {
            Password password = new Password(input);
            assertNotNull(password);
        }

        @Test
        public void testGetPassword() {
            Password password = new Password(input);
            assertEquals(input, password.getPassword());
        }

        @Test
        public void testSetPassword() {
            Password password = new Password(input);
            password.setPassword("differentPassword");
            assertEquals("differentPassword", password.getPassword());
        }
    }

    @RunWith(Parameterized.class)
    public static class InvalidInputs {

        private String input;

        public InvalidInputs (String input) {
            this.input = input;
        }

        @Parameters
        public static String[] invalidPasswords() {
            return new String[]{"password!", "short", "@bad!password", "thisPasswordIsWayTooExcessive"};
        }

        @Test
        public void test_invalidPassword() {
            assertFalse(Password.isValid(input));
        }


        @Test(expected = IllegalArgumentException.class)
        public void testPasswordConstructorInvalid() {
            new Password(input);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testSetPasswordInvalid() {
            Password password = new Password("validpassword");
            password.setPassword(input);

        }
    }
}