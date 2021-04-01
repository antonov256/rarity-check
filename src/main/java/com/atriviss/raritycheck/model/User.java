package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.TimeZone;

@Getter
public class User extends UserLite {
    private final Portfolio portfolio;

    public User(Integer id, String username, String password, String name, String surname, String email, TimeZone timeZone, Portfolio portfolio) {
        super(id, username, password, name, surname, email, timeZone);
        this.portfolio = portfolio;
    }
}
