package com.example.kanflow.service;
import java.util.List;
import java.util.UUID;

import com.example.kanflow.model.User;

public interface UserService {
    User getUserById(UUID id);
    User findByEmail(String email);
    List<User> getAllUsers();
    User register(String firstname, String lastname, String email, String password);

    void deleteUser(UUID id);

}
