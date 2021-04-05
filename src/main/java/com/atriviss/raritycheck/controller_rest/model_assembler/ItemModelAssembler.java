package com.atriviss.raritycheck.controller_rest.model_assembler;

import com.atriviss.raritycheck.controller_rest.ItemRestController;
import com.atriviss.raritycheck.dto_api.ItemApiDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<ItemApiDto, EntityModel<ItemApiDto>> {
    @Override
    public EntityModel<ItemApiDto> toModel(ItemApiDto apiDto) {
        return EntityModel.of(apiDto,
                linkTo(methodOn(ItemRestController.class).one(apiDto.getId())).withSelfRel(),
                linkTo(methodOn(ItemRestController.class).all()).withRel("items"));
    }
}
