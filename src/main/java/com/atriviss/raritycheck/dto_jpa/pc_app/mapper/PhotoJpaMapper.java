package com.atriviss.raritycheck.dto_jpa.pc_app.mapper;

import com.atriviss.raritycheck.dto_jpa.pc_app.PhotoJpaDto;
import com.atriviss.raritycheck.model.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PhotoJpaMapper {
    Photo toPhoto(PhotoJpaDto photoJpaDto);

    PhotoJpaDto toPhotoJpaDto(Photo photo);

    List<Photo> toPhotoList (List<PhotoJpaDto> photoJpaDtos);

    List<PhotoJpaDto> toPhotoJpaDtoList (List<Photo> photos);
}
