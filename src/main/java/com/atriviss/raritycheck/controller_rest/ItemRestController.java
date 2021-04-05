package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.ItemNotFoundException;
import com.atriviss.raritycheck.controller_rest.model_assembler.ItemModelAssembler;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import com.atriviss.raritycheck.service.ItemService;
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
public class ItemRestController {
    @Autowired
    private ItemService service;

    @Autowired
    private ItemModelAssembler assembler;


    @GetMapping("/items/{id}")
    public EntityModel<ItemApiDto> one(@PathVariable Integer id) {
        ItemApiDto apiDto = service.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

        return assembler.toModel(apiDto);
    }

    @GetMapping("/items")
    public CollectionModel<EntityModel<ItemApiDto>> all() {
        List<ItemApiDto> apiDtoList = service.findAll();

        List<EntityModel<ItemApiDto>> entityModelList = apiDtoList.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(ItemRestController.class).all()).withSelfRel());
    }

    @PostMapping("/items")
    public ResponseEntity<?> create(@RequestBody ItemApiDto apiDto) {
        EntityModel<ItemApiDto> entityModel = assembler.toModel(service.create(apiDto));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> replace(@RequestBody ItemApiDto apiDto, @PathVariable Integer id) {
        ItemApiDto replacedApiDto = service.replaceItem(id, apiDto);
        EntityModel<ItemApiDto> entityModel = assembler.toModel(replacedApiDto);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
