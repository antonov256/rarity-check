package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.TimeZone;

@Getter
public class UserLite {
    private final Integer id;

    private final String username;
    private final String password;

    private final String name;
    private final String surname;
    private final String email;
    private final TimeZone timeZone;

    public UserLite(Integer id, String username, String password, String name, String surname, String email, TimeZone timeZone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.timeZone = timeZone;
    }
}
