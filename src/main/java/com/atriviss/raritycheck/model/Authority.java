package com.atriviss.raritycheck.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Slf4j
public class Authority implements GrantedAuthority {
    public static final Authority ADMIN = new Authority("ADMIN");
    public static final Authority USER = new Authority("USER");

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    public static Authority fromString(String string) {
        if(string == null)
            throw new IllegalArgumentException("Authority = null");

        if(string.equals(USER.getAuthority()))
            return USER;

        if(string.equals(ADMIN.getAuthority()))
            return ADMIN;

        log.warn("Unknown authority: " + string);
        return new Authority(string);
    }

    @Override
    public String toString() {
        return authority;
    }
}
