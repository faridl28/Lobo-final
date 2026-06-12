package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/mensajes/**").authenticated()
                    .requestMatchers("/api/solicitudes/mis-solicitudes").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/solicitudes").authenticated()
                    .requestMatchers("/api/solicitudes/**").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            )
            .httpBasic(httpBasic -> {});
    return http.build();
  }
}