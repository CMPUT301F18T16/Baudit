package ca.klapstein.baudit.data;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Enclosed.class)
public class UsernameTest {
// TODO: enable when a RemoteModel is implemented or mocked
    @RunWith(Parameterized.class)
    public static class ValidUserNameTest {

        private String input;

        @Parameters(name = "{index}: valid({0})={1}")
        public static String[] validUserName() {
            return new String[]{"testUser", "testUser1", "@!516!@4!@$!2°▐"};
        }

        public ValidUserNameTest(String input) {
            this.input = input;
        }

        @Test
        public void testUsernameConstructor() {
            Username username = new Username(input);
            assertNotNull(username);
            assertEquals(input, username.toString());
        }

        @Test
        public void testGetUsername() {
            Username username = new Username(input);
            assertNotNull(username);
            assertEquals(input, username.toString());
        }

        @Test
        public void testSetUsername() {
            Username username = new Username(input);
            username.setUsername(input);
            assertEquals(input, username.toString());
        }
    }

    @RunWith(Parameterized.class)
    public static class InvalidUserNameTest {
        private String input;

        public InvalidUserNameTest(String input) {
            this.input = input;
        }

        @Parameters(name = "{index}: invalid({0})={1}")
        public static String[] invalidUserName() {
            return new String[]{"short", "testUserThatIsWayTooLong", ""};
        }

        @Test(expected = IllegalArgumentException.class)
        public void testUsernameConstructor() {
            Username username = new Username(input);
            assertNotNull(username);
            assertEquals(input, username.toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testSetUsername() {
            Username username = new Username("validuser");
            username.setUsername(input);
            assertEquals(input, username.toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testUsernameConstructorInvalid() {
            new Username(input);
        }
    }
}