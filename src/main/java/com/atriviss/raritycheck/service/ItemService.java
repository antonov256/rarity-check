package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.dto_api.to_create.PhotoToAddToItem;
import com.atriviss.raritycheck.service.search.SpecificationsBuilder;
import com.atriviss.raritycheck.service.search.SearchOperation;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.mapper.*;
import com.atriviss.raritycheck.dto_api.to_create.ItemToCreate;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.dto_jpa.rc_app.ItemJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.QualityJpaDto;
import com.atriviss.raritycheck.dto_jpa.rc_app.mapper.*;
import com.atriviss.raritycheck.model.Item;
import com.atriviss.raritycheck.repository.rc_app.ItemRepository;
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
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemApiMapper apiMapper;

    @Autowired
    private ItemJpaMapper jpaMapper;

    @Autowired
    private ClassificationApiMapper classificationApiMapper;

    @Autowired
    private ClassificationJpaMapper classificationJpaMapper;

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

    @Autowired
    private WishListService wishListService;
    @Autowired
    private OwnListService ownListService;
    

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
        Pattern pattern = Pattern.compile("((\\w+\\.)*\\w+)(" + codesRegex + ")(\\w+?),");
        Matcher matcher = pattern.matcher(searchQuery + ",");

        while (matcher.find()) {
            String key = matcher.group(1);
            SearchOperation operation = SearchOperation.fromCode(matcher.group(3));
            String value = matcher.group(4);

            builder.with(key, operation, value);
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

    @Transactional(transactionManager = "appTransactionManager")
    public ItemApiDto create(ItemToCreate itemToCreate) {
        ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(itemToCreate));

        ItemJpaDto createdItemJpaDto = repository.save(jpaDto);
        Item createdItem = jpaMapper.toItem(createdItemJpaDto);

        List<PhotoToCreate> photosToCreate = itemToCreate.getPhotos();
        photosToCreate.forEach(p -> p.setItemId(createdItem.getId()));
        List<PhotoApiDto> createdPhotos = photoService.createPhotos(photosToCreate);

        ItemApiDto createdApiDto = apiMapper.toItemApiDto(createdItem);
        createdApiDto.setPhotos(createdPhotos);
        return createdApiDto;
    }

    public ItemApiDto replaceItem(Integer id, ItemApiDto newItemApiDto) {
        return repository.findById(id)
                .map(jpaDto -> {
                    jpaDto.setTitle(newItemApiDto.getTitle());
                    jpaDto.setDescription(newItemApiDto.getDescription());
                    jpaDto.setClassification(classificationJpaMapper.toClassificationJpaDto(classificationApiMapper.toClassification(newItemApiDto.getClassification())));
                    jpaDto.setQuality(new QualityJpaDto(newItemApiDto.getQuality().getValue()));

                    List<PhotoApiDto> photos = newItemApiDto.getPhotos();
                    photoService.updatePhotos(id, photos);

                    jpaDto.setVideos(videoJpaMapper.toVideoJpaDtoList(videoApiMapper.toVideoList(newItemApiDto.getVideos())));
                    
                    ItemJpaDto updatedJpaDto = repository.save(jpaDto);

                    Item item = jpaMapper.toItem(updatedJpaDto);
                    return apiMapper.toItemApiDto(item);
                })
                .orElseGet(() -> {
                    ItemJpaDto jpaDto = jpaMapper.toItemJpaDto(apiMapper.toItem(newItemApiDto));
                    jpaDto.setId(id);
                    ItemJpaDto savedJpaDto = repository.save(jpaDto);

                    List<PhotoToCreate> photosToCreate = newItemApiDto.getPhotos().stream()
                            .map(p -> new PhotoToCreate(savedJpaDto.getId(), new PhotoToAddToItem(p.getBucketName(), p.getKey())))
                            .collect(Collectors.toList());

                    List<PhotoApiDto> createdPhotos = photoService.createPhotos(photosToCreate);

                    Item item = jpaMapper.toItem(savedJpaDto);
                    ItemApiDto apiDto = apiMapper.toItemApiDto(item);
                    apiDto.setPhotos(createdPhotos);

                    return apiDto;
                });
    }

    @Transactional(transactionManager = "appTransactionManager")
    public void deleteById(Integer id) {
        wishListService.deleteWishItemsByItemId(id);
        ownListService.deleteOwnItemsByItemId(id);
        photoService.deleteByItemId(id);
        repository.deleteById(id);
    }
}
