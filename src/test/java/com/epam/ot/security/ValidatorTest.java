package com.epam.ot.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testIsLoginValid() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isLoginValid("Valid_loginForTest1");
        } catch (Throwable e) {
            throwable = e;
        }

        assertNull(res);
        assertNull(throwable);
    }

    @Test
    public void testIsLoginHaveIncorrectCaharacters() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isLoginValid("It's login NOt Valid");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.login.incorrect_characters", res);
        assertNull(throwable);
    }

    @Test
    public void testIsLoginIncorrect() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isLoginValid("It's login NOt Valid and too long for 'login'");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.login.too_long", res);
        assertNull(throwable);
    }

    @Test
    public void testIsLoginJustLong() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isLoginValid("This_login_tooLong_123_4567_butWithoutIncorrectCharacters");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.login.too_long", res);
        assertNull(throwable);
    }

    @Test
    public void testIsEmailValid() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isEmailValid("email_is_Valid123@mail");
        } catch (Throwable e) {
            throwable = e;
        }

        assertNull(res);
        assertNull(throwable);
    }

    @Test
    public void testIsEmailHaveSpaces() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isEmailValid("this email with spaces@mail");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.email.incorrect", res);
        assertNull(throwable);
    }

    @Test
    public void testIsEmailNotCorrect() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isEmailValid("email_withoutDog");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.email.incorrect", res);
        assertNull(throwable);
    }

    @Test
    public void testIsPasswordValid() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isPasswordValid("just_valid_password");
        } catch (Throwable e) {
            throwable = e;
        }

        assertNull(res);
        assertNull(throwable);
    }

    @Test
    public void testIfPasswordStrong() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isPasswordValid("@3ItsStrong:Password123");
        } catch (Throwable e) {
            throwable = e;
        }

        assertNull(res);
        assertNull(throwable);
    }

    @Test
    public void testIfPasswordTooLong() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isPasswordValid("CorrectPassword_but_tooLongForBD");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.password.too_long", res);
        assertNull(throwable);
    }

    @Test
    public void testIfPasswordHaveNotCorrectChars() throws Exception {
        Throwable throwable = null;
        String res = null;
        try {
            res = Validator.isPasswordValid("Incorrect passWord");
        } catch (Throwable e) {
            throwable = e;
        }

        assertEquals("error.password.incorrect", res);
        assertNull(throwable);
    }
}