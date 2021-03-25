package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.UserInfoApiDto;
import com.atriviss.raritycheck.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoApiDtoConverter implements Converter<User, UserInfoApiDto> {
    @Override
    public UserInfoApiDto convert(User source) {
        UserInfoApiDto target = new UserInfoApiDto(
                source.getId(),
                source.getUsername(),
                source.getName(),
                source.getSurname(),
                source.getEmail(),
                source.getTimeZone().getDisplayName(),
                source.getPortfolio().getOwnList().size(),
                source.getPortfolio().getWishList().size()
        );

        return target;
    }
}
