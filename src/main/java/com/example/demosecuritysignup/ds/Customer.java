package com.example.demosecuritysignup.ds;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=
            new ArrayList<>();

    public void addRole(Role role){
        role.getCustomers().add(this);
        roles.add(role);
    }
}
