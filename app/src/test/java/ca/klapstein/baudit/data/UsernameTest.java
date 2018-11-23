package ca.klapstein.baudit.data;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

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

    @Test
    public void equals() {
        assertTrue(new Username(input).equals(new Username(input)));
    }

    @Test
    public void neverEqualsNull() {
        assertFalse(new Username(input).equals(null));
    }

    @Test
    public void equalsOtherObject() {
        assertFalse(new Username(input).equals(new Object()));
    }

    @Test
    public void testHashCodeSame() {
        Username username1 = new Username(input);
        Username username2 = new Username(input);
        assertEquals(username1.hashCode(), username2.hashCode());
    }

    @Test
    public void testHashCodeDifferent() {
        Username username1 = new Username("foo@example.com");
        Username username2 = new Username("bar@example.com");
        assertNotEquals(username1.hashCode(), username2.hashCode());
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