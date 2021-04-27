package com.atriviss.raritycheck.dto_api.mapper;

import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.S3File;
import com.atriviss.raritycheck.model.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PhotoApiMapper {
    Photo toPhoto(PhotoApiDto photoApiDto);

    Photo toPhoto(S3File file);

    PhotoApiDto toPhotoApiDto(Photo photo);

    List<Photo> toPhotoList(List<PhotoApiDto> photoApiDtos);

    List<PhotoApiDto> toPhotoApiDtoList(List<Photo> photos);
}
