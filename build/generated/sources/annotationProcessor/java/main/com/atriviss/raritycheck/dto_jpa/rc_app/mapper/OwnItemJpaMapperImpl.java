package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.OwnItemToAddToUser;
import com.atriviss.raritycheck.dto_jpa.rc_app.OwnItemJpaDto;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.model.OwnItem;
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
public class OwnItemJpaMapperImpl implements OwnItemJpaMapper {

    @Autowired
    private ItemJpaMapper itemJpaMapper;

    @Override
    public OwnItem toOwnItem(OwnItemJpaDto ownItemJpaDto) {
        if ( ownItemJpaDto == null ) {
            return null;
        }

        Integer id = null;
        Integer userId = null;
        Item item = null;

        id = ownItemJpaDto.getId();
        userId = ownItemJpaDto.getUserId();
        item = itemJpaMapper.toItem( ownItemJpaDto.getItem() );

        OwnItem ownItem = new OwnItem( id, userId, item );

        return ownItem;
    }

    @Override
    public OwnItemJpaDto toOwnItemJpaDto(OwnItem ownItem) {
        if ( ownItem == null ) {
            return null;
        }

        OwnItemJpaDto ownItemJpaDto = new OwnItemJpaDto();

        ownItemJpaDto.setId( ownItem.getId() );
        ownItemJpaDto.setUserId( ownItem.getUserId() );
        ownItemJpaDto.setItem( itemJpaMapper.toItemJpaDto( ownItem.getItem() ) );

        return ownItemJpaDto;
    }

    @Override
    public OwnItemJpaDto toOwnItemJpaDto(OwnItemToAddToUser ownItemToAddToUser) {
        if ( ownItemToAddToUser == null ) {
            return null;
        }

        OwnItemJpaDto ownItemJpaDto = new OwnItemJpaDto();

        ownItemJpaDto.setUserId( ownItemToAddToUser.getUserId() );

        return ownItemJpaDto;
    }

    @Override
    public List<OwnItem> toOwnItemList(List<OwnItemJpaDto> ownItemJpaDtoList) {
        if ( ownItemJpaDtoList == null ) {
            return null;
        }

        List<OwnItem> list = new ArrayList<OwnItem>( ownItemJpaDtoList.size() );
        for ( OwnItemJpaDto ownItemJpaDto : ownItemJpaDtoList ) {
            list.add( toOwnItem( ownItemJpaDto ) );
        }

        return list;
    }

    @Override
    public List<OwnItemJpaDto> toOwnItemJpaDtoList(List<OwnItem> ownItemList) {
        if ( ownItemList == null ) {
            return null;
        }

        List<OwnItemJpaDto> list = new ArrayList<OwnItemJpaDto>( ownItemList.size() );
        for ( OwnItem ownItem : ownItemList ) {
            list.add( toOwnItemJpaDto( ownItem ) );
        }

        return list;
    }
}
