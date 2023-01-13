package com.example.demosecuritysignup.ds;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers=
            new ArrayList<>();
}
