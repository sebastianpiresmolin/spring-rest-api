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
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(GET, "/categories").permitAll()
                                .requestMatchers(POST, "/categories").hasRole("ADMIN")

                                .requestMatchers(GET, "/locations").permitAll()
                                .requestMatchers(GET, "/locations/area/{lon}/{lat}").permitAll()
                                .requestMatchers(GET, "/locations/{id}").permitAll()
                                .requestMatchers(GET, "/locations/categories/{id}").permitAll()
                                .requestMatchers(GET, "/locations/all/user").hasRole("USER")
                                .requestMatchers(POST, "/locations").hasRole("USER")
                                .requestMatchers(PUT, "/locations/delete/{id}").hasRole("USER")
                                .requestMatchers(PUT, "/locations/edit/{id}").hasRole("USER")

                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}

   /* @Bean
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
*/
