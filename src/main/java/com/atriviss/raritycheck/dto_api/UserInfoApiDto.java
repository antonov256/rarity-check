package com.atriviss.raritycheck.dto_api;

import lombok.Getter;

@Getter
public class UserInfoApiDto {
    private final Integer id;

    private final String username;

    private final String name;
    private final String surname;

    private final String email;
    private final String timeZone;

    private final Integer ownListSize;
    private final Integer wishListSize;

    public UserInfoApiDto(Integer id, String username, String name, String surname, String email, String timeZone, Integer ownListSize, Integer wishListSize) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.timeZone = timeZone;
        this.ownListSize = ownListSize;
        this.wishListSize = wishListSize;
    }
}
