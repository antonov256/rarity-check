package com.atriviss.raritycheck.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
public class User {
    private Integer id;

    private String username;
    private String password;

    private String name;
    private String surname;
    private String email;
    private TimeZone timeZone;

    private final Portfolio portfolio;

    public User(Integer id, String username, String password, String name, String surname, String email, TimeZone timeZone, Portfolio portfolio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.timeZone = timeZone;
        this.portfolio = portfolio;
    }
}
