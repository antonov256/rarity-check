package com.atriviss.raritycheck.dto_api;

import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import lombok.Getter;

@Getter
public class AuthorizationResponse {
    private final Status status;
    private final String message;
    private final UserApiDto user;

    public AuthorizationResponse(Status status, String message, UserApiDto user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public enum Status {
        SUCCESS, FAILURE
    }
}
