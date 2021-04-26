package com.atriviss.raritycheck.dto_api;

import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import lombok.Getter;

@Getter
public class AuthenticationApiDto {
    private final String token;
    private final UserApiDto user;

    public AuthenticationApiDto(String token, UserApiDto user) {
        this.token = token;
        this.user = user;
    }
}
