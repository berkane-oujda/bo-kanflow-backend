package com.example.kanflow.service;
import com.example.kanflow.model.User;
import java.util.List;



public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);
}
