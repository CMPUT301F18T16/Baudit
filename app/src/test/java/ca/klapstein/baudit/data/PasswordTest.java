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
    public class ValidInputs {

        private String input;

        public ValidInputs(String input) {
            this.input = input;
        }

        @Parameters
        public String[] dataValid() {
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
            assertEquals(input, password.getPassword());
        }

        @Test
        public void testGetPassword() {
            Password password = new Password(input);
            assertNotNull(password);
            assertEquals(input, password.getPassword());
        }

        @Test
        public void testSetPasswordValid() {
            Password password = new Password(input);
            assertEquals(input, password.getPassword());
        }
    }

    @RunWith(Parameterized.class)
    public class InvalidInputs {

        private String input;

        public InvalidInputs (String input) {
            this.input = input;
        }

        @Parameters
        public String[] dataInvalid() {
            return new String[]{"password!", "short", "@bad!password", "thisPasswordIsWayTooExcessive"};
        }

        @Test
        public void test_invalidPassword() {
            assertFalse(Password.isValid(input));
        }


        @Test
        public void testSetPasswordInvalid() {
            new Password(input);
        }
    }
}