package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.mapper.PhotoApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToAddToItem;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.pc_app.PhotoJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.PhotoJpaMapper;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoJpaMapper photoJpaMapper;
    @Autowired
    private PhotoApiMapper photoApiMapper;

    public Optional<PhotoApiDto> findById(Long id) {
        Optional<PhotoJpaDto> optionalJpaDto = photoRepository.findById(id);
        return optionalJpaDto.map(jpaDto -> photoApiMapper.toPhotoApiDto(photoJpaMapper.toPhoto(jpaDto)));
    }

    public List<PhotoApiDto> getItemPhotos(Integer itemId) {
        List<PhotoJpaDto> jpaDtoList = photoRepository.findAllByItemId(itemId);
        List<Photo> photos = photoJpaMapper.toPhotoList(jpaDtoList);

        return photoApiMapper.toPhotoApiDtoList(photos);
    }

    public PhotoApiDto save(PhotoToCreate photoToCreate) {
        PhotoJpaDto photoJpaDto = photoJpaMapper.toPhotoJpaDto(photoToCreate);
        PhotoJpaDto saved = photoRepository.save(photoJpaDto);

        return photoApiMapper.toPhotoApiDto(photoJpaMapper.toPhoto(saved));
    }

    public PhotoApiDto savePhotoToItem(Integer itemId, PhotoToAddToItem photoToAddToItem) {
        PhotoToCreate photoToCreate = new PhotoToCreate(itemId, photoToAddToItem);
        PhotoJpaDto saved = photoRepository.save(photoJpaMapper.toPhotoJpaDto(photoToCreate));

        Photo model = photoJpaMapper.toPhoto(saved);
        PhotoApiDto apiDto = photoApiMapper.toPhotoApiDto(model);

        return apiDto;
    }

    public List<PhotoApiDto> savePhotos(List<PhotoToCreate> photosToCreate) {
        List<PhotoApiDto> savedPhotos = photosToCreate.stream()
                .map(photoJpaMapper::toPhotoJpaDto)
                .map(photoRepository::save)
                .map(photoJpaMapper::toPhoto)
                .map(photoApiMapper::toPhotoApiDto)
                .collect(Collectors.toList());

        return savedPhotos;
    }

    public void deleteById(Long id) {
        photoRepository.deleteById(id);
    }
}
