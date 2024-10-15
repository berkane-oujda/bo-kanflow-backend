package com.example.kanflow.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanflow.dto.UserDto;
import com.example.kanflow.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public UserDto me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof User) {
            User currentUser = (User) authentication.getPrincipal();
            return new UserDto(
                    currentUser.getID(),
                    currentUser.getEmail(),
                    currentUser.getFirstname(),
                    currentUser.getLastname());
        } else {
            return null;
        }
    }
}
