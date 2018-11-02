package ca.klapstein.baudit.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class UsernameTest {

    private String input;

    public UsernameTest(String input) {
        this.input = input;
    }

    @Before
    public void setUp() {
    }

    @Parameterized.Parameters
    public static String[] data1() {
        return new String[]{"testUser", "testUser1"};
    }

    @Test(expected=IllegalArgumentException.class)
    public void testUsernameConstructor() {
        Username username = new Username(input);
        assertNotNull(username);
        assertEquals(input, username.getUsername());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetUsername() {
        Username username = new Username(input);
        assertNotNull(username);
        assertEquals(input, username.getUsername());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetUsername() {
        Username username = new Username("temp");
        username.setUsername(input);
        assertEquals(input, username.getUsername());

    }

    @Parameterized.Parameters
    public static String[] data2() {
        return new String[]{"short", "testUserThatIsWayTooLong", ""};
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetUsernameInvalid() {
        new Username(input);
    }
}