package com.logistics.ejb.service;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.remote.UserServiceRemote;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Stateless
public class UserServiceBean implements UserServiceRemote {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User authenticate(String username, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();

        if (user != null && verifyPassword(password, user.getPasswordHash(), user.getSalt())) {
            return user;
        }

        return null;
    }

    private boolean verifyPassword(String password, String passwordHash, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            String base64Hash = Base64.getEncoder().encodeToString(hashedPassword);
            return base64Hash.equals(passwordHash);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error during password verification", e);
            return false;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void registerUser(User user) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", user.getUsername());
        if (query.getResultList().isEmpty()) {
            em.persist(user);
        } else {
            throw new IllegalArgumentException("Username already exists");
        }
    }
}
