package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.SubcategoryNotFoundException;
import com.atriviss.raritycheck.controller_rest.model_assembler.SubcategoryModelAssembler;
import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import com.atriviss.raritycheck.service.SubcategoryService;
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
public class SubcategoryRestController {
    @Autowired
    private SubcategoryService service;

    @Autowired
    private SubcategoryModelAssembler assembler;


    @GetMapping("/subcategories/{id}")
    public EntityModel<SubcategoryApiDto> one(@PathVariable Integer id) {
        SubcategoryApiDto apiDto = service.findById(id).orElseThrow(() -> new SubcategoryNotFoundException(id));

        return assembler.toModel(apiDto);
    }

    @GetMapping("/subcategories")
    public CollectionModel<EntityModel<SubcategoryApiDto>> all() {
        List<SubcategoryApiDto> apiDtoList = service.findAll();

        List<EntityModel<SubcategoryApiDto>> entityModelList = apiDtoList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(SubcategoryRestController.class).all()).withSelfRel());
    }

    @PostMapping("/subcategories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody SubcategoryApiDto apiDto) {
        EntityModel<SubcategoryApiDto> entityModel = assembler.toModel(service.create(apiDto));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/subcategories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> replace(@RequestBody SubcategoryApiDto apiDto, @PathVariable Integer id) {
        SubcategoryApiDto replacedApiDto = service.replaceSubcategory(id, apiDto);
        EntityModel<SubcategoryApiDto> entityModel = assembler.toModel(replacedApiDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/subcategories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
