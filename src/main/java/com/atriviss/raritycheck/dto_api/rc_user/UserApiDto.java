package com.atriviss.raritycheck.dto_api.rc_user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApiDto {
    private Integer id;
    private String username;

    private String name;
    private String surname;
    private String email;
    private String timezone;

    private String authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}
