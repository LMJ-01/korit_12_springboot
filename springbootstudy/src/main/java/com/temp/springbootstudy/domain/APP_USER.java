package com.temp.springbootstudy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class APP_USER {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, username, password;

    public APP_USER() {}

    public APP_USER(String password, String username, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
