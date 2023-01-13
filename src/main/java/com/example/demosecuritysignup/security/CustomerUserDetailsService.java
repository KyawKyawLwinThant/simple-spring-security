package com.example.demosecuritysignup.security;

import com.example.demosecuritysignup.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerDao customerDao;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return customerDao.findCustomerByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(()->
                        new UsernameNotFoundException(username +
                                " Not Found!"));

    }
}
