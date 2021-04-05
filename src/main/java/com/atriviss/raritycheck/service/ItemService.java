package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.mapper.*;
import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.QualityJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.*;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemApiMapper apiMapper;

    @Autowired
    private ItemJpaMapper jpaMapper;


    @Autowired
    private CategoryApiMapper categoryApiMapper;

    @Autowired
    private CategoryJpaMapper categoryJpaMapper;

    @Autowired
    private SubcategoryApiMapper subcategoryApiMapper;

    @Autowired
    private SubcategoryJpaMapper subcategoryJpaMapper;

    @Autowired
    private PhotoApiMapper photoApiMapper;

    @Autowired
    private PhotoJpaMapper photoJpaMapper;

    @Autowired
    private VideoApiMapper videoApiMapper;

    @Autowired
    private VideoJpaMapper videoJpaMapper;
    

    public Optional<ItemApiDto> findById(Integer id) {
        Optional<ItemJpaDto> optionalJpaDto = repository.findById(id);
        return optionalJpaDto.map(jpaDto -> apiMapper.toItemApiDto(jpaMapper.toItem(jpaDto)));
    }

    public List<ItemApiDto> findAll() {
        List<ItemJpaDto> jpaDtoList = repository.findAll();
        List<Item> list = jpaMapper.toItemList(jpaDtoList);
        List<ItemApiDto> apiDtoList = apiMapper.toItemApiDtoList(list);

        return apiDtoList;
    }
    
    public ItemApiDto create(ItemApiDto itemApiDto) {
        ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(itemApiDto));
        ItemJpaDto savedJpaDto = repository.save(jpaDto);
        ItemApiDto savedApiDto = apiMapper.toItemApiDto(jpaMapper.toItem(savedJpaDto));

        return savedApiDto;
    }

    public ItemApiDto replaceItem(Integer id, ItemApiDto newItemApiDto) {
        return repository.findById(id)
                .map(jpaDto -> {
                    jpaDto.setTitle(newItemApiDto.getTitle());
                    jpaDto.setDescription(newItemApiDto.getDescription());
                    jpaDto.setCategory(categoryJpaMapper.toCategoryJpaDto(categoryApiMapper.toCategory(newItemApiDto.getClassification().getCategory())));
                    jpaDto.setSubcategory(subcategoryJpaMapper.toSubcategoryJpaDto(subcategoryApiMapper.toSubcategory(newItemApiDto.getClassification().getSubcategory())));
                    jpaDto.setQuality(new QualityJpaDto(newItemApiDto.getQuality().getValue()));
                    jpaDto.setPhotos(photoJpaMapper.toPhotoJpaDtoList(photoApiMapper.toPhotoList(newItemApiDto.getPhotos())));
                    jpaDto.setVideos(videoJpaMapper.toVideoJpaDtoList(videoApiMapper.toVideoList(newItemApiDto.getVideos())));
                    
                    ItemJpaDto updatedJpaDto = repository.save(jpaDto);

                    return apiMapper.toItemApiDto(jpaMapper.toItem(updatedJpaDto));
                })
                .orElseGet(() -> {
                    ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(newItemApiDto));
                    jpaDto.setId(id);
                    ItemJpaDto savedJpaDto = repository.save(jpaDto);

                    return apiMapper.toItemApiDto(jpaMapper.toItem(savedJpaDto));
                });
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
