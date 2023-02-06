package com.example.reservationsystem.config;

import com.example.reservationsystem.security.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin().successForwardUrl("/search").loginProcessingUrl("/login").permitAll()
                .and()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin").hasRole(Role.ADMIN.getRoleString())
                .anyRequest().authenticated();

        return http.build();
    }
}
