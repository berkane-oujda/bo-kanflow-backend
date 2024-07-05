// package com.example.kanflow.config.old;

// import org.slf4j.LoggerFactory;
// import org.slf4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.oauth2.jwt.Jwt;
// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.example.kanflow.model.User;
// import com.example.kanflow.service.UserService;
// import com.nimbusds.jose.jwk.JWK;
// import com.nimbusds.jose.jwk.JWKSet;
// import com.nimbusds.jose.jwk.RSAKey;
// import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
// import com.nimbusds.jose.jwk.source.JWKSource;
// import com.nimbusds.jose.proc.SecurityContext;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// @EnableConfigurationProperties
// public class SecurityConfig {

//     @Autowired
//     private UserService userService;

//     private final RsaKeyProperties rsaKeys;

//     public SecurityConfig(RsaKeyProperties rsaKeys) {
//         this.rsaKeys = rsaKeys;
//     }

//     @Bean
//     public SecurityFilterChain secureFilterChain(HttpSecurity http) throws Exception {
//         return http
//                 .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
//                 .authenticationManager(auth -> {
//                     Jwt jwt = jwtDecoder().decode(auth.getPrincipal().toString());
//                     Long userId = jwt.getClaim("userId");
//                     User user = userService.getUserById(userId);
//                     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
//                     return authentication;
//                 })
//                 .build();
//     }

//     @Order(Ordered.HIGHEST_PRECEDENCE)
//     @Bean
//     public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
//         return http
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .securityMatcher(new AntPathRequestMatcher("/auth/**"))
//                 // .securityMatcher(new AntPathRequestMatcher("/health"))
//                 .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                 // .addFilterBefore(jwtTokenFilter(jwtDecoder()), UsernamePasswordAuthenticationFilter.class)
//                 .build();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
//         return http
//                 .build();
//     }

  //     @Bean
  //     public JwtDecoder jwtDecoder() {
  //         return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
  //     }

  //     @Bean
  //     JwtEncoder jwtEncoder() {
  //         JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
  //         JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
  //         return new NimbusJwtEncoder(jwks);
  //     }

  //     @Bean
  //     public JwtTokenFilter jwtTokenFilter(JwtDecoder jwtDecoder) {
  //         return new JwtTokenFilter(jwtDecoder);
  //     }

  //     @Bean
  //     public BCryptPasswordEncoder bCryptPasswordEncoder() {
  //         return new BCryptPasswordEncoder();
  //     }
// }
