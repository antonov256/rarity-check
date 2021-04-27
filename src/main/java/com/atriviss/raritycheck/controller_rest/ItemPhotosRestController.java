package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.model_assembler.PhotoModelAssembler;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToAddToItem;
import com.atriviss.raritycheck.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemPhotosRestController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoModelAssembler assembler;

    @GetMapping("/items/{item_id}/photos")
    public CollectionModel<EntityModel<PhotoApiDto>> itemPhotos(@PathVariable("item_id") Integer itemId) {
        List<PhotoApiDto> photos = photoService.getItemPhotos(itemId);
        return assembler.toCollectionModel(photos);
    }

    @PostMapping("/items/{item_id}/photos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EntityModel<PhotoApiDto> savePhotoToItem(@PathVariable("item_id") Integer itemId, @RequestBody PhotoToAddToItem photoToAddToItem) {
        PhotoApiDto createdPhotoApiDto = photoService.savePhotoToItem(itemId, photoToAddToItem);
        return assembler.toModel(createdPhotoApiDto);
    }
}
