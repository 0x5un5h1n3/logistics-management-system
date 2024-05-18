package com.logistics.ejb.remote;

import com.logistics.ejb.entity.User;
import jakarta.ejb.Remote;

@Remote
public interface UserServiceRemote {
    User authenticate(String username, String password);
    void registerUser(User user);
}