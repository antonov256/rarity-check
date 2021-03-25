package com.atriviss.raritycheck.dto_api.converter;

import com.atriviss.raritycheck.dto_api.OwnListApiDto;
import com.atriviss.raritycheck.model.OwnList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OwnListApiDtoConverter implements Converter<OwnList, OwnListApiDto> {
    private final OwnItemApiDtoConverter ownItemApiDtoConverter;

    @Autowired
    public OwnListApiDtoConverter(OwnItemApiDtoConverter ownItemApiDtoConverter) {
        this.ownItemApiDtoConverter = ownItemApiDtoConverter;
    }

    @Override
    public OwnListApiDto convert(OwnList source) {
        OwnListApiDto target = new OwnListApiDto(
                source.getItems().stream()
                        .map(ownItemApiDtoConverter::convert)
                        .collect(Collectors.toList())
        );
        return target;
    }
}
