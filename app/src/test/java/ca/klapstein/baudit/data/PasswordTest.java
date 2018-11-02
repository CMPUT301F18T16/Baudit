package ca.klapstein.baudit.data;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PasswordTest {

    private String input;

    public PasswordTest(String input) {
        this.input = input;
    }

    @Parameters
    public static String[] data1() {
        return new String[] { "abcd1234", "password", "011235813"};
    }

    @Test
    public void test_validPassword() {
        assertTrue(Password.isValid(input));
    }

    @Parameters
    public static String[] data2() {
        return new String[] { "password!", "short", "thisPasswordIsWayTooExcessive"};
    }

    @Test
    public void test_invalidPassword() {
        assertFalse(Password.isValid(input));
    }

    @Parameters
    public static String[] data3() {
        return new String[] { "passwordConstruct"};
    }

    @Test
    public void testPasswordConstructor() {
        Password password = new Password(input);
        assertNotNull(password);
        assertEquals(input, password.getPassword());
    }

    @Parameters
    public static String[] data4() {
        return new String[] { "passwordGet" };
    }

    @Test
    public void testGetPassword() {
        Password password = new Password(input);
        assertNotNull(password);
        assertEquals(input, password.getPassword());
    }

    @Parameters
    public static String[] data5() {
        return new String[] { "abcd1234", "password", "011235813"};
    }

    @Test
    public void testSetPasswordValid() {
        Password password = new Password(input);
        assertEquals(input, password.getPassword());
    }

    @Parameters
    public static String[] data6() {
        return new String[] { "@bad!password", "", "thisPasswordIsWayTooExcessive"};
    }

    @Test
    public void testSetPasswordInvalid() {
        new Password(input);
    }
}