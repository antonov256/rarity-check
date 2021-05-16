package com.atriviss.raritycheck.dto_api;

import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import lombok.Getter;

@Getter
public class AuthenticationApiDto {
    private final SuccessFailure status;
    private final String message;
    private final String token;
    private final UserApiDto user;

    public AuthenticationApiDto(SuccessFailure status, String message, String token, UserApiDto user) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    public enum SuccessFailure {
        SUCCESS, FAILURE
    }
}
