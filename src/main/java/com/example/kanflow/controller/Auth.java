package com.example.kanflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.kanflow.dto.LoginDetailsDto;
import com.example.kanflow.dto.RegsiterDetailsDto;
import com.example.kanflow.dto.TokenDto;
import com.example.kanflow.model.User;
import com.example.kanflow.service.TokenService;
import com.example.kanflow.service.UserServiceImplementation;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserServiceImplementation userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public String register(@RequestBody RegsiterDetailsDto body) throws ResponseStatusException {
        User user = userService.findByEmail(body.getEmail());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "email already used");
        }
        String hashedPassword = bCryptPasswordEncoder.encode(body.getPassword());
        user = new User(body.getEmail(), body.getFirstname(), body.getLastname(), hashedPassword);
        userService.saveUser(user);

        return "OK!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetailsDto body,
            HttpServletResponse response) throws ResponseStatusException {
        User user = this.userService.findByEmail(body.getEmail());
        String hashedPassword = bCryptPasswordEncoder.encode(body.getPassword());
        if (user == null || user.getPassword().equals(hashedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong email or password!");
        }

        String jwt = this.tokenService.generateToken(user);

        String cookieValue = String.format(
                "jwt=%s; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=%d",
                jwt, 24 * 60 * 60);

        response.setHeader("Set-Cookie", cookieValue);

        return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
    }
}
