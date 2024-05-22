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

import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
@Remote(UserService.class)
public class UserService implements UserServiceRemote {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{4,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean authenticateUser(String username, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();

        if (!users.isEmpty()) {
            User user = users.get(0);
            String hashedPassword = user.getPasswordHash();
            String saltedHashedPassword = PasswordUtils.hashPassword(password, user.getSalt());
            return hashedPassword.equals(saltedHashedPassword);
        }

        return false;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void registerUser(String username, String password) {
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username format");
        }

        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password format");
        }

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        if (query.getResultList().isEmpty()) {
            byte[] salt = generateSalt();
            String hashedPassword = PasswordUtils.hashPassword(password, salt);
            User user = new User(username, hashedPassword, salt);
            em.persist(user);
        } else {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public boolean isAuthenticated(User user) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class);
        query.setParameter("userId", user.getId());
        List<User> users = query.getResultList();

        return !users.isEmpty();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();

        return users.isEmpty() ? null : users.get(0);
    }

    private boolean isValidUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
