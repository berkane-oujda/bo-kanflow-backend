package com.example.kanflow.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties
public class SecurityConfig {

    @Bean
    public SecurityFilterChain secureFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // public routes
                // .securityMatcher(new AntPathRequestMatcher("/auth/**"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**")
                .permitAll())
                // secured routes
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .build();
    }
}
