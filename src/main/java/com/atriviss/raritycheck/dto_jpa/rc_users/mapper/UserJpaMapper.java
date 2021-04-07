package com.atriviss.raritycheck.dto_jpa.rc_users.mapper;

import com.atriviss.raritycheck.mapper.AuthoritiesMapper;
import com.atriviss.raritycheck.mapper.TimezoneMapper;
import com.atriviss.raritycheck.dto_jpa.rc_users.UserJpaDto;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.model.UserToCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {TimezoneMapper.class, AuthoritiesMapper.class})
public interface UserJpaMapper {
    @Mapping(source = "username", target = "userDetails.username")
    @Mapping(source = "password", target = "userDetails.password")
    @Mapping(source = "accountNonExpired", target = "userDetails.accountNonExpired")
    @Mapping(source = "accountNonLocked", target = "userDetails.accountNonLocked")
    @Mapping(source = "credentialsNonExpired", target = "userDetails.credentialsNonExpired")
    @Mapping(source = "enabled", target = "userDetails.enabled")
    User toModel(UserJpaDto jpaDto);

    @Mapping(source = "userDetails.username", target = "username")
    @Mapping(source = "userDetails.password", target = "password")
    @Mapping(source = "userDetails.accountNonExpired", target = "accountNonExpired")
    @Mapping(source = "userDetails.accountNonLocked", target = "accountNonLocked")
    @Mapping(source = "userDetails.credentialsNonExpired", target = "credentialsNonExpired")
    @Mapping(source = "userDetails.enabled", target = "enabled")
    UserJpaDto toJpaDto(User domainObject);

    @Mapping(source = "userDetails.username", target = "username")
    @Mapping(source = "userDetails.password", target = "password")
    @Mapping(source = "userDetails.authorities", target = "authorities")
    @Mapping(source = "userDetails.accountNonExpired", target = "accountNonExpired")
    @Mapping(source = "userDetails.accountNonLocked", target = "accountNonLocked")
    @Mapping(source = "userDetails.credentialsNonExpired", target = "credentialsNonExpired")
    @Mapping(source = "userDetails.enabled", target = "enabled")
    UserJpaDto toJpaDto(UserToCreate userToCreate);
}
