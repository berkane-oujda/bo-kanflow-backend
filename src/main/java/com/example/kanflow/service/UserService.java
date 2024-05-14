package com.example.kanflow.service;
import java.util.List;

import com.example.kanflow.model.User;

public interface UserService {
    User getUserById(Long id);
    User findByEmail(String email);
    List<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);

}
