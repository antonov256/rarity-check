package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.ItemNotFoundException;
import com.atriviss.raritycheck.controller_rest.model_assembler.ItemModelAssembler;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.dto_api.to_create.ItemToCreate;
import com.atriviss.raritycheck.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ItemRestController {
    @Autowired
    private ItemService service;

    @Autowired
    private ItemModelAssembler assembler;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PagedResourcesAssembler<ItemApiDto> pagedResourcesAssembler;


    @GetMapping("/items/{id}")
    public EntityModel<ItemApiDto> one(@PathVariable Integer id) {
        ItemApiDto apiDto = service.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

        return assembler.toModel(apiDto);
    }

    @GetMapping("/items")
    public PagedModel<EntityModel<ItemApiDto>> all(@PageableDefault Pageable pageable) {
        Page<ItemApiDto> apiDtoPage = service.findAll(pageable);
        PagedModel<EntityModel<ItemApiDto>> entityModels = pagedResourcesAssembler.toModel(apiDtoPage);

        return entityModels;
    }

    @PostMapping("/items")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ItemToCreate itemToCreate) {
        ItemApiDto apiDto = service.create(itemToCreate);
        EntityModel<ItemApiDto> entityModel = assembler.toModel(apiDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> replace(@RequestBody ItemApiDto apiDto, @PathVariable Integer id) {
        ItemApiDto replacedApiDto = service.replaceItem(id, apiDto);
        EntityModel<ItemApiDto> entityModel = assembler.toModel(replacedApiDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
