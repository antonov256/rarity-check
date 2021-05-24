package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.PhotoJpaDto;
import com.atriviss.raritycheck.model.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PhotoJpaMapper {
    Photo toPhoto(PhotoJpaDto photoJpaDto);

    PhotoJpaDto toPhotoJpaDto(Photo photo);

    PhotoJpaDto toPhotoJpaDto(PhotoToCreate photoToCreate);

    List<Photo> toPhotoList (List<PhotoJpaDto> photoJpaDtos);

    List<PhotoJpaDto> toPhotoJpaDtoList (List<Photo> photos);

    List<PhotoJpaDto> toPhotoJpaDtoListFromPhotoToCreateList(List<PhotoToCreate> photoToCreateList);
}
