package com.example.springrestapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers(GET, "/api/categories").permitAll()
                                .requestMatchers(GET, "/api/categories/{name}").permitAll()
                                .requestMatchers(POST, "/api/categories").hasAuthority("SCOPE_admin")

                                .requestMatchers(GET, "/api/locations").permitAll()
                                .requestMatchers(GET, "/api/locations/area/{lon}/{lat}").permitAll()
                                .requestMatchers(GET, "/api/locations/{id}").permitAll()
                                .requestMatchers(GET, "/api/locations/categories/{id}").permitAll()
                                .requestMatchers(GET, "/api/locations/all/user").hasAuthority("SCOPE_user")
                                .requestMatchers(POST, "/api/locations").hasAuthority("SCOPE_user")
                                .requestMatchers(PUT, "/api/locations/delete/{id}").hasAuthority("SCOPE_user")
                                .requestMatchers(PUT, "/api/locations/edit/{id}").hasAuthority("SCOPE_user")

                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}
