package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.S3File;
import com.atriviss.raritycheck.dto_api.mapper.PhotoApiMapper;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToAddToItem;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.PhotoJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.mapper.PhotoJpaMapper;
import com.atriviss.raritycheck.model.Photo;
import com.atriviss.raritycheck.repository.rc_app.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private FilesService filesService;

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

    @Transactional
    public List<PhotoApiDto> updatePhotos(Integer itemId, List<PhotoApiDto> photosToUpdate) {
        List<PhotoJpaDto> photosInDb = photoRepository.findAllByItemId(itemId);

        List<PhotoToAddToItem> photosToAddToItem = photosToUpdate.stream()
                .filter(p -> p.getId() == null && p.getItemId() == null)
                .map(p -> new PhotoToAddToItem(p.getBucketName(), p.getKey())).collect(Collectors.toList());

        List<PhotoJpaDto> photosToKeep = photosToUpdate.stream()
                .filter(p -> p.getId() != null && p.getItemId() != null)
                .map(photoApiMapper::toPhoto)
                .map(photoJpaMapper::toPhotoJpaDto)
                .collect(Collectors.toList());

        List<PhotoJpaDto> photosToDelete = photosInDb.stream()
                .filter(photoInDb -> photosToKeep.stream().noneMatch(p -> p.getId().equals(photoInDb.getId())))
                .collect(Collectors.toList());


        List<PhotoApiDto> addedPhotos = addPhotosToItem(itemId, photosToAddToItem);
        photosToDelete.forEach(p -> deleteById(p.getId()));

        List<PhotoApiDto> keptPhotos = photoApiMapper.toPhotoApiDtoList(
                photoJpaMapper.toPhotoList(
                        photoRepository.saveAll(photosToKeep)
                )
        );

        keptPhotos.addAll(addedPhotos);

        return keptPhotos;
    }

    public List<PhotoApiDto> addPhotosToItem(Integer itemId, List<PhotoToAddToItem> photosToAddToItem){
        List<PhotoToCreate> photosToCreate = photosToAddToItem
                .stream().map(p -> new PhotoToCreate(itemId, p))
                .collect(Collectors.toList());

        List<PhotoApiDto> createdPhotos = createPhotos(photosToCreate);
        return createdPhotos;
    }

    public List<PhotoApiDto> createPhotos(List<PhotoToCreate> photosToCreate) {
        List<PhotoJpaDto> jpaDtos = photoJpaMapper.toPhotoJpaDtoListFromPhotoToCreateList(photosToCreate);

        List<PhotoJpaDto> savedJpaDtos = photoRepository.saveAll(jpaDtos);
        List<Photo> photos = photoJpaMapper.toPhotoList(savedJpaDtos);
        List<PhotoApiDto> apiDtos = photoApiMapper.toPhotoApiDtoList(photos);

        return apiDtos;
    }

    public void deleteById(Long id) {
        Optional<PhotoJpaDto> photoOptional = photoRepository.findById(id);
        if(photoOptional.isPresent()) {
            PhotoJpaDto photo = photoOptional.get();
            S3File s3File = new S3File(photo.getBucketName(), photo.getKey());
            photoRepository.deleteById(id);
            filesService.deleteS3File(s3File);
        }
    }

    public void deleteByItemId(Integer itemId) {
        List<PhotoJpaDto> photosByItemId = photoRepository.findAllByItemId(itemId);
        photosByItemId.forEach(p -> deleteById(p.getId()));
    }
}
