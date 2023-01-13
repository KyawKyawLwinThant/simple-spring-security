package com.example.demosecuritysignup.config;

import com.example.demosecuritysignup.security.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Throwable {
        http.authorizeHttpRequests()
                .requestMatchers("/bootstrap/**", "/home","/register","/")
                .permitAll()
                .requestMatchers("/main1")
                .hasAnyRole("ADMIN", "USER")
                .requestMatchers("/main2")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();


        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf()
                .disable();


        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
