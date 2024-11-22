package com.example.springrestapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(GET, "/categories").permitAll()
                                .requestMatchers(POST, "/categories").hasRole("ADMIN")

                                .requestMatchers(GET,  "/locations").permitAll()
                                .requestMatchers(GET, "/locations/area/{lon}/{lat}").permitAll()
                                .requestMatchers(GET,  "/locations/{id}").permitAll()
                                .requestMatchers(GET,  "/locations/categories/{id}").permitAll()
                                .requestMatchers(GET, "/locations/all/user").hasRole("USER")
                                .requestMatchers(POST, "/locations").hasRole("USER")
                                .requestMatchers(PUT, "/locations/delete/{id}").hasRole("USER")
                                .requestMatchers(PUT, "/locations/edit/{id}").hasRole("USER")
                                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        PasswordEncoder encoder = passwordEncoder();
        UserDetails user = User.builder()
                .username("elr0nd")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
