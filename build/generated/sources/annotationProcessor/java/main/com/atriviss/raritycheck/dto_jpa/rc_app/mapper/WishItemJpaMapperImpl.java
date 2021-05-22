package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.WishItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.rc_app.WishItemJpaDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.WishItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:08+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class WishItemJpaMapperImpl implements WishItemJpaMapper {

    @Autowired
    private ItemJpaMapper itemJpaMapper;

    @Override
    public WishItem toWishItem(WishItemJpaDto wishItemJpaDto) {
        if ( wishItemJpaDto == null ) {
            return null;
        }

        Integer id = null;
        Integer userId = null;
        Item item = null;

        id = wishItemJpaDto.getId();
        userId = wishItemJpaDto.getUserId();
        item = itemJpaMapper.toItem( wishItemJpaDto.getItem() );

        WishItem wishItem = new WishItem( id, userId, item );

        return wishItem;
    }

    @Override
    public WishItemJpaDto toWishItemJpaDto(WishItem wishItem) {
        if ( wishItem == null ) {
            return null;
        }

        WishItemJpaDto wishItemJpaDto = new WishItemJpaDto();

        wishItemJpaDto.setId( wishItem.getId() );
        wishItemJpaDto.setUserId( wishItem.getUserId() );
        wishItemJpaDto.setItem( itemJpaMapper.toItemJpaDto( wishItem.getItem() ) );

        return wishItemJpaDto;
    }

    @Override
    public WishItemJpaDto toWishItemJpaDto(WishItemToAddToUser wishItemToAddToUser) {
        if ( wishItemToAddToUser == null ) {
            return null;
        }

        WishItemJpaDto wishItemJpaDto = new WishItemJpaDto();

        wishItemJpaDto.setUserId( wishItemToAddToUser.getUserId() );

        return wishItemJpaDto;
    }

    @Override
    public List<WishItem> toWishItemList(List<WishItemJpaDto> wishItemJpaDtoList) {
        if ( wishItemJpaDtoList == null ) {
            return null;
        }

        List<WishItem> list = new ArrayList<WishItem>( wishItemJpaDtoList.size() );
        for ( WishItemJpaDto wishItemJpaDto : wishItemJpaDtoList ) {
            list.add( toWishItem( wishItemJpaDto ) );
        }

        return list;
    }

    @Override
    public List<WishItemJpaDto> toWishItemJpaDtoList(List<WishItem> wishItemList) {
        if ( wishItemList == null ) {
            return null;
        }

        List<WishItemJpaDto> list = new ArrayList<WishItemJpaDto>( wishItemList.size() );
        for ( WishItem wishItem : wishItemList ) {
            list.add( toWishItemJpaDto( wishItem ) );
        }

        return list;
    }
}
