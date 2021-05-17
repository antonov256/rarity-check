package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.ResourceNotFoundException;
import com.atriviss.raritycheck.controller_rest.model_assembler.PhotoModelAssembler;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import com.atriviss.raritycheck.dto_api.to_create.PhotoToCreate;
import com.atriviss.raritycheck.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PhotosRestController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoModelAssembler assembler;

    @GetMapping("/photos/{id}")
    public EntityModel<PhotoApiDto> one(@PathVariable("id") Long id) {
        PhotoApiDto photo = photoService.findById(id).orElseThrow(() -> new ResourceNotFoundException("photo", id));
        return assembler.toModel(photo);
    }

    @PostMapping("/photos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EntityModel<PhotoApiDto> addPhoto(@RequestBody PhotoToCreate photoToCreate) {
        PhotoApiDto createdPhotoApiDto = photoService.save(photoToCreate);
        return assembler.toModel(createdPhotoApiDto);
    }

    @DeleteMapping("/photos/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        photoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
