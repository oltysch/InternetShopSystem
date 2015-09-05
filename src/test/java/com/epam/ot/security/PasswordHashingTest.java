package com.epam.ot.security;

import com.epam.ot.entity.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordHashingTest {

    @Test
    public void testValidatePasswordIfPasswordRight() throws Exception {
        String password = TestUtils.generateString(9);

        String hashPassword = PasswordHashing.generatePasswordHash(password);
        Boolean validationResult = PasswordHashing.validatePassword(password, hashPassword);
        System.out.println(validationResult.booleanValue());

        assertNotNull(validationResult);
        assertNotNull(hashPassword);
        assertNotEquals("", hashPassword);
        assertTrue(hashPassword.length() > 100);
        assertTrue(validationResult);
    }

    @Test
    public void testValidatePasswordIfPasswordWrong() throws Exception {
        String password = "auiodfhyiud";
        String wrongPassword = "auiodfhylud";

        String hashPassword = PasswordHashing.generatePasswordHash(password);
        Boolean validationResult = PasswordHashing.validatePassword(wrongPassword, hashPassword);
        System.out.println(validationResult.booleanValue());

        assertNotNull(validationResult);
        assertFalse(validationResult);
    }
}