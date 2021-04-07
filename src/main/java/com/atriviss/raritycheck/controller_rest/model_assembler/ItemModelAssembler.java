package com.atriviss.raritycheck.controller_rest.model_assembler;

import com.atriviss.raritycheck.controller_rest.ItemRestController;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements SimpleRepresentationModelAssembler<ItemApiDto> {
    @Override
    public void addLinks(EntityModel<ItemApiDto> resource) {
        if (resource.getContent() != null)
            resource.add(linkTo(methodOn(ItemRestController.class).one(resource.getContent().getId())).withSelfRel());

        resource.add(linkTo(methodOn(ItemRestController.class).all(Pageable.unpaged())).withRel("items"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<ItemApiDto>> resources) {
        resources.add(linkTo(methodOn(ItemRestController.class).all(Pageable.unpaged())).withRel("items"));
    }
}
