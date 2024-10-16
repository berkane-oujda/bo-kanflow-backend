package com.example.kanflow.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collections;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.kanflow.model.User;
import com.example.kanflow.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    private final RsaKeyProperties rsaKeys;

    public JwtTokenFilter(RsaKeyProperties rsaKeys) {
        this.rsaKeys = rsaKeys;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String token = extractToken(request);

            System.out.println("|request|   : " + token + "  :  ya hala");

            if (token != null) {
                UUID userId = getUserIdFromToken(token);
                if (userId != null) {
                    User user = userService.getUserById(userId);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            logger.error("Error processing authentication", ex);
        }

        filterChain.doFilter(request, response);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    private String extractToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> "jwt".equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }

    private UUID getUserIdFromToken(String token) {
        Jwt jwt = jwtDecoder().decode(token);
        String claimAsString = jwt.getClaim("userId");
        return UUID.fromString(claimAsString);
    }
}
