package com.atriviss.raritycheck.model;

import lombok.Getter;

import java.util.TimeZone;

@Getter
public class User extends UserToCreate {
    private final Integer id;

    public User(Integer id, UserDetailsContainer userDetails, String name, String surname, String email, TimeZone timezone) {
        super( userDetails, name, surname, email, timezone);
        this.id = id;
    }
}
