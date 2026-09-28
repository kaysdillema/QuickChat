package com.quickchat.quickchat;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
// JUnit Test Suite 
    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test Data: "kyl_1" -> Should return True
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+278389689");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!!" -> Should return False
        Login login = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+278389689");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        // Test Data: "Ch&&sec@ke99!" -> Should return True
        Login login = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+278389689");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordFailsComplexity() {
        // Test Data: "password" -> Should return False
        Login login = new Login("Kyle", "Smith", "kyl_1", "password", "+278389689");
        assertFalse(login.checkPasswordComplexity());
    }
}
