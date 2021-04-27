package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.service.search.SpecificationsBuilder;
import com.atriviss.raritycheck.service.search.SearchOperation;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.mapper.*;
import com.atriviss.raritycheck.dto_api.to_create.ItemToCreate;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.pc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.QualityJpaDto;
import com.atriviss.raritycheck.dto_jpa.pc_app.mapper.*;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private PhotoService photoService;
    

    public Optional<ItemApiDto> findById(Integer id) {
        Optional<ItemJpaDto> optionalJpaDto = repository.findById(id);
        return optionalJpaDto.map(jpaDto -> apiMapper.toItemApiDto(jpaMapper.toItem(jpaDto)));
    }

    public Page<ItemApiDto> findAll(Pageable pageable) {
        Page<ItemJpaDto> jpaDtoPage = repository.findAll(pageable);
        Page<ItemApiDto> apiDtoPage = jpaDtoPage.map(jpaMapper::toItem).map(apiMapper::toItemApiDto);

        return apiDtoPage;
    }

    public Page<ItemApiDto> findAllWithSearch(Pageable pageable, String searchQuery) {
        SpecificationsBuilder<ItemJpaDto> builder = new SpecificationsBuilder<>();
        List<String> codes = SearchOperation.codes();
        String codesRegex = String.join("|", codes);
        Pattern pattern = Pattern.compile("(\\w+?)(" + codesRegex + ")(\\w+?),");
        Matcher matcher = pattern.matcher(searchQuery + ",");

        while (matcher.find()) {
            builder.with(matcher.group(1), SearchOperation.fromCode(matcher.group(2)), matcher.group(3));
        }

        Specification<ItemJpaDto> specification = builder.build();

        Page<ItemJpaDto> jpaDtoPage = repository.findAll(specification, pageable);
        Page<ItemApiDto> apiDtoPage = jpaDtoPage.map(jpaMapper::toItem).map(apiMapper::toItemApiDto);

        return apiDtoPage;
    }

    public Page<ItemApiDto> findAllByTitleContains(Pageable pageable, String titleFragment) {
        Page<ItemJpaDto> jpaDtoPage = repository.findByTitleContains(titleFragment, pageable);
        Page<ItemApiDto> apiDtoPage = jpaDtoPage.map(jpaMapper::toItem).map(apiMapper::toItemApiDto);

        return apiDtoPage;
    }

    @Transactional
    public ItemApiDto create(ItemToCreate itemToCreate) {
        ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(itemToCreate));

        ItemJpaDto createdItemJpaDto = repository.save(jpaDto);
        Item createdItem = jpaMapper.toItem(createdItemJpaDto);

        List<PhotoToCreate> photosToCreate = itemToCreate.getPhotos();
        photosToCreate.forEach(p -> p.setItemId(createdItem.getId()));
        List<PhotoApiDto> createdPhotos = photoService.savePhotos(photosToCreate);

        ItemApiDto createdApiDto = apiMapper.toItemApiDto(createdItem);
        createdApiDto.setPhotos(createdPhotos);
        return createdApiDto;
    }

    public ItemApiDto create(ItemApiDto apiDto) {
        ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(apiDto));
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
