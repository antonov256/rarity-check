package com.atriviss.raritycheck.dto_jpa.rc_app.mapper;

import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.PhotoJpaDto;
import com.atriviss.raritycheck.model.Photo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-21T21:39:08+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.10 (Azul Systems, Inc.)"
)
@Component
public class PhotoJpaMapperImpl implements PhotoJpaMapper {

    @Override
    public Photo toPhoto(PhotoJpaDto photoJpaDto) {
        if ( photoJpaDto == null ) {
            return null;
        }

        Long id = null;
        Integer itemId = null;
        String bucketName = null;
        String key = null;

        id = photoJpaDto.getId();
        itemId = photoJpaDto.getItemId();
        bucketName = photoJpaDto.getBucketName();
        key = photoJpaDto.getKey();

        Photo photo = new Photo( id, itemId, bucketName, key );

        return photo;
    }

    @Override
    public PhotoJpaDto toPhotoJpaDto(Photo photo) {
        if ( photo == null ) {
            return null;
        }

        PhotoJpaDto photoJpaDto = new PhotoJpaDto();

        photoJpaDto.setId( photo.getId() );
        photoJpaDto.setItemId( photo.getItemId() );
        photoJpaDto.setBucketName( photo.getBucketName() );
        photoJpaDto.setKey( photo.getKey() );

        return photoJpaDto;
    }

    @Override
    public PhotoJpaDto toPhotoJpaDto(PhotoToCreate photoToCreate) {
        if ( photoToCreate == null ) {
            return null;
        }

        PhotoJpaDto photoJpaDto = new PhotoJpaDto();

        photoJpaDto.setItemId( photoToCreate.getItemId() );
        photoJpaDto.setBucketName( photoToCreate.getBucketName() );
        photoJpaDto.setKey( photoToCreate.getKey() );

        return photoJpaDto;
    }

    @Override
    public List<Photo> toPhotoList(List<PhotoJpaDto> photoJpaDtos) {
        if ( photoJpaDtos == null ) {
            return null;
        }

        List<Photo> list = new ArrayList<Photo>( photoJpaDtos.size() );
        for ( PhotoJpaDto photoJpaDto : photoJpaDtos ) {
            list.add( toPhoto( photoJpaDto ) );
        }

        return list;
    }

    @Override
    public List<PhotoJpaDto> toPhotoJpaDtoList(List<Photo> photos) {
        if ( photos == null ) {
            return null;
        }

        List<PhotoJpaDto> list = new ArrayList<PhotoJpaDto>( photos.size() );
        for ( Photo photo : photos ) {
            list.add( toPhotoJpaDto( photo ) );
        }

        return list;
    }

    @Override
    public List<PhotoJpaDto> toPhotoJpaDtoListFromPhotoToCreateList(List<PhotoToCreate> photoToCreateList) {
        if ( photoToCreateList == null ) {
            return null;
        }

        List<PhotoJpaDto> list = new ArrayList<PhotoJpaDto>( photoToCreateList.size() );
        for ( PhotoToCreate photoToCreate : photoToCreateList ) {
            list.add( toPhotoJpaDto( photoToCreate ) );
        }

        return list;
    }
}
