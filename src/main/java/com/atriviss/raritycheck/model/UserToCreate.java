package com.atriviss.raritycheck.model;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.TimeZone;

@Getter
public class UserToCreate implements UserDetails {
    @Delegate
    private UserDetailsContainer userDetails;

    private final String name;
    private final String surname;
    private final String email;
    private final TimeZone timezone;

    public UserToCreate(UserDetailsContainer userDetails, String name, String surname, String email, TimeZone timezone) {
        this.userDetails = userDetails;
        this.name = name;
        this.surname = surname;
        this.email = email;

        if(timezone == null)
            timezone = TimeZone.getDefault();

        this.timezone = timezone;
    }
}
