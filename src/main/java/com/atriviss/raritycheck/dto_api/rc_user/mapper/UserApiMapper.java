package com.atriviss.raritycheck.dto_api.rc_user.mapper;

import com.atriviss.raritycheck.dto_api.rc_user.UserApiDto;
import com.atriviss.raritycheck.mapper.AuthoritiesMapper;
import com.atriviss.raritycheck.mapper.TimezoneMapper;
import com.atriviss.raritycheck.model.User;
import org.mapstruct.Mapper;

@Mapper(uses = {TimezoneMapper.class, AuthoritiesMapper.class})
public interface UserApiMapper {
    User toModel(UserApiDto apiDto);
    UserApiDto toDto(User domainObject);
}
