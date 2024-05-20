package com.logistics.ejb.remote;

import com.logistics.ejb.entity.User;
import jakarta.ejb.Remote;

@Remote
public interface UserService {
    boolean authenticateUser(String username, String password);
    void registerUser(String username, String password);
    boolean isAuthenticated(User user);
    User getUserByUsername(String username);
}
