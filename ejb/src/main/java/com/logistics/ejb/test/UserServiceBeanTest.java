package com.logistics.ejb.test;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.service.UserServiceBean;
import com.logistics.util.PasswordUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceBeanTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private UserServiceBean userService;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("WebPU");
        em = emf.createEntityManager();
        userService = new UserServiceBean();
        userService.em = em;
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void testRegisterUser() {
        String username = "testuser";
        String password = "testpassword";
        userService.registerUser(username, password);

        User user = userService.authenticate(username, password);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertTrue(PasswordUtils.hashPassword(password, user.getSalt()).equals(user.getPasswordHash()));
    }

}
