package com.atriviss.raritycheck.dto_api.rc_user.mapper;

import com.atriviss.raritycheck.dto_api.rc_user.UserRegisterApiDto;
import com.atriviss.raritycheck.mapper.AuthoritiesMapper;
import com.atriviss.raritycheck.mapper.TimezoneMapper;
import com.atriviss.raritycheck.model.UserToCreate;
import org.mapstruct.Mapper;

@Mapper(uses = {TimezoneMapper.class, AuthoritiesMapper.class})
public interface UserToCreateMapper {
    UserToCreate toDomainObject(UserRegisterApiDto apiDto);
    UserRegisterApiDto toDto(UserToCreate domainObject);
}
