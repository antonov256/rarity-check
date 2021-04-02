package com.atriviss.raritycheck.dto_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoApiDto {
    private Integer id;

    private String username;

    private String name;
    private String surname;

    private String email;
    private String timeZone;

    private Integer ownListSize;
    private Integer wishListSize;

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
