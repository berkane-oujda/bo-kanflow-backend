package com.example.kanflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.kanflow.config.JwtTokenFilter;
import com.example.kanflow.dto.LoginDetailsDto;
import com.example.kanflow.dto.RegsiterDetailsDto;
import com.example.kanflow.model.User;
import com.example.kanflow.service.TokenService;
import com.example.kanflow.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtTokenFilter tokenFilter;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegsiterDetailsDto body) throws ResponseStatusException {
        User user = userService.findByEmail(body.getEmail());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "email already used");
        }
        userService.register(body.getFirstname(), body.getLastname(), body.getEmail(), body.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetailsDto body,
            HttpServletResponse response) throws ResponseStatusException {
        User user = this.userService.findByEmail(body.getEmail());

        if (user == null || !bCryptPasswordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email or password!");

        }
        String jwt = this.tokenService.generateToken(user.getID());
        String cookieValue = String.format(
                "jwt=%s; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=%d",
                jwt, 15 * 60);
        response.setHeader("Set-Cookie", cookieValue);

        return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(
            @CookieValue(name = "jwt", required = false) String oldToken,
            HttpServletResponse response) {
        if (oldToken == null || !tokenService.validateToken(oldToken)) {
            return new ResponseEntity<>("Invalid or missing token", HttpStatus.UNAUTHORIZED);
        }

        String newToken = tokenService.generateToken(tokenFilter.getUserIdFromToken(oldToken));

        String cookieValue = String.format(
                "jwt=%s; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=%d",
                newToken, 24 * 60 * 60);

        response.setHeader("Set-Cookie", cookieValue);

        return new ResponseEntity<>("Token refreshed successfully", HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        String cookieValue = String.format(
                "jwt=%s; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=%d",
                "", 0);
        response.setHeader("Set-Cookie", cookieValue);

        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }
}