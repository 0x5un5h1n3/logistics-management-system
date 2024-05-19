package com.logistics.ejb.test;

import com.logistics.util.PasswordUtils;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    void testHashPassword() {
        String password = "testpassword";
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);

        // Verify that the same password and salt produce the same hash
        String hashedPasswordAgain = PasswordUtils.hashPassword(password, salt);
        assertEquals(hashedPassword, hashedPasswordAgain);
    }
}
