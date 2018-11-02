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
    public class PasswordTestValid {

        private String input;

        public PasswordTestValid(String input) {
            this.input = input;
        }

        @Parameters
        public String[] data1() {
            return new String[]{"abcd1234", "password", "011235813"};
        }

        @Test
        public void test_validPassword() {
            assertTrue(Password.isValid(input));
        }
    }

    @RunWith(Parameterized.class)
    public class PasswordTestInvalid {

        private String input;

        public PasswordTestInvalid(String input) {
            this.input = input;
        }

        @Parameters
        public String[] data2() {
            return new String[]{"password!", "short", "thisPasswordIsWayTooExcessive"};
        }

        @Test
        public void test_invalidPassword() {
            assertFalse(Password.isValid(input));
        }
    }

    @RunWith(Parameterized.class)
    public class PasswordTestConstructor {

        private String input;

        public PasswordTestConstructor(String input) {
            this.input = input;
        }

        @Parameters
        public String[] data3() {
            return new String[]{"passwordConstruct"};
        }

        @Test
        public void testPasswordConstructor() {
            Password password = new Password(input);
            assertNotNull(password);
            assertEquals(input, password.getPassword());
        }
    }

    @RunWith(Parameterized.class)
    public class PasswordTestGet {

        private String input;

        public PasswordTestGet(String input) {
            this.input = input;
        }

        @Parameters
        public String[] data4() {
            return new String[]{"passwordGet"};
        }

        @Test
        public void testGetPassword() {
            Password password = new Password(input);
            assertNotNull(password);
            assertEquals(input, password.getPassword());
        }
    }

    @RunWith(Parameterized.class)
    public class PasswordTestSetValid {

        private String input;

        public PasswordTestSetValid(String input) {
            this.input = input;
        }


        @Parameters
        public String[] data5() {
            return new String[]{"abcd1234", "password", "011235813"};
        }

        @Test
        public void testSetPasswordValid() {
            Password password = new Password(input);
            assertEquals(input, password.getPassword());
        }
    }

    @RunWith(Parameterized.class)
    public class PasswordTestSetInvalid {

        private String input;

        public PasswordTestSetInvalid(String input) {
            this.input = input;
        }

        @Parameters
        public String[] data6() {
            return new String[] { "@bad!password", "", "thisPasswordIsWayTooExcessive"};
        }

        @Test
        public void testSetPasswordInvalid() {
            new Password(input);
        }
    }
}