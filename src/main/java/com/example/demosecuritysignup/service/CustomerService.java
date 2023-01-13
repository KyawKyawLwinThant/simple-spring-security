package com.example.demosecuritysignup.service;

import com.example.demosecuritysignup.dao.CustomerDao;
import com.example.demosecuritysignup.dao.RoleDao;
import com.example.demosecuritysignup.ds.Customer;
import com.example.demosecuritysignup.ds.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerDao customerDao;
    private final RoleDao roleDao;

    public CustomerService(PasswordEncoder passwordEncoder, CustomerDao customerDao, RoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.customerDao = customerDao;
        this.roleDao = roleDao;
    }
    @Transactional
    public void register(Customer customer){
        Role userRole=roleDao.findRoleByRoleName("ROLE_USER").get();
        customer.addRole(userRole);
        customer.setPassword(passwordEncoder
                .encode(customer.getPassword()));
        customerDao.save(customer);

    }













}
