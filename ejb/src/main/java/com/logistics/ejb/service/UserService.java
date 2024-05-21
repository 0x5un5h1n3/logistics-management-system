package com.logistics.ejb.service;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.remote.UserServiceRemote;
import com.logistics.util.PasswordUtils;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Stateless
@Remote(UserServiceRemote.class)
public class UserService implements UserServiceRemote {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        if (query.getResultList().isEmpty()) {
            String hashedPassword = PasswordUtils.hashPassword(password, new byte[16]);
            User user = new User(username, hashedPassword);
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
}
