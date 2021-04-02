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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryModelAssembler assembler;

    @GetMapping("/categories/{id}")
    public EntityModel<CategoryApiDto> one(@PathVariable Integer id) {
        CategoryApiDto categoryApiDto = categoryService.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        return assembler.toModel(categoryApiDto);
    }

    @GetMapping("/categories")
    public CollectionModel<EntityModel<CategoryApiDto>> all() {
        List<CategoryApiDto> categoryDtos = categoryService.findAll();

        List<EntityModel<CategoryApiDto>> entityModels = categoryDtos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModels, linkTo(methodOn(CategoryRestController.class).all()).withSelfRel());
    }

    @PostMapping("/categories")
    public ResponseEntity<?> newCategory(@RequestBody CategoryApiDto newCategoryApiDto) {
        EntityModel<CategoryApiDto> entityModel = assembler.toModel(categoryService.createCategory(newCategoryApiDto));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> replaceCategory(@RequestBody CategoryApiDto newCategoryApiDto, @PathVariable Integer id) {
        CategoryApiDto updatedCategory = categoryService.replaceCategory(id, newCategoryApiDto);
        EntityModel<CategoryApiDto> entityModel = assembler.toModel(updatedCategory);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
