package com.atriviss.raritycheck.mapper;

import com.atriviss.raritycheck.model.Authority;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper
public class AuthoritiesMapper {
    public Collection<GrantedAuthority> toCollection(String authoritiesString) {
        return Arrays.stream(authoritiesString.split(",")).map(Authority::fromString).collect(Collectors.toList());
    }

    public String toString(Collection<GrantedAuthority> authorities) {
        String authoritiesString = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return authoritiesString;
    }
}
