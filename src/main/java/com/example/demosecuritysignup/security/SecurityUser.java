package com.example.demosecuritysignup.security;

import com.example.demosecuritysignup.ds.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {
    private final Customer customer;

    public SecurityUser(Customer customer) {
        this.customer = customer;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
