package com.example.demosecuritysignup;

import com.example.demosecuritysignup.dao.CustomerDao;
import com.example.demosecuritysignup.dao.RoleDao;
import com.example.demosecuritysignup.ds.Customer;
import com.example.demosecuritysignup.ds.Role;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class DemoSecuritySignupApplication {

    private final CustomerDao customerDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public DemoSecuritySignupApplication(CustomerDao customerDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoSecuritySignupApplication.class, args);
    }
    @Bean @Transactional @Profile("dev")
    public ApplicationRunner runner(){
        return r ->{
            Customer customer=new Customer();
            customer.setUsername("john");
            customer.setPassword(passwordEncoder
                    .encode("12345"));
            Role role1=new Role();
            role1.setRoleName("ROLE_USER");
            Role role2=new Role();
            role2.setRoleName("ROLE_ADMIN");
            customer.addRole(role1);
            customer.addRole(role2);
            roleDao.save(role1);
            roleDao.save(role2);
            customerDao.save(customer);
        };
    }

}
