package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.CategoryNotFoundException;
import com.atriviss.raritycheck.controller_rest.model_assembler.CategoryModelAssembler;
import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import com.atriviss.raritycheck.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryModelAssembler assembler;


    @GetMapping("/categories/{id}")
    public EntityModel<CategoryApiDto> one(@PathVariable Integer id) {
        CategoryApiDto apiDto = service.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        return assembler.toModel(apiDto);
    }

    @GetMapping("/categories")
    public CollectionModel<EntityModel<CategoryApiDto>> all() {
        List<CategoryApiDto> apiDtoList = service.findAll();

        List<EntityModel<CategoryApiDto>> entityModelList = apiDtoList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(CategoryRestController.class).all()).withSelfRel());
    }

    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CategoryApiDto apiDto) {
        EntityModel<CategoryApiDto> entityModel = assembler.toModel(service.create(apiDto));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> replace(@RequestBody CategoryApiDto apiDto, @PathVariable Integer id) {
        CategoryApiDto replacedApiDto = service.replaceCategory(id, apiDto);
        EntityModel<CategoryApiDto> entityModel = assembler.toModel(replacedApiDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
