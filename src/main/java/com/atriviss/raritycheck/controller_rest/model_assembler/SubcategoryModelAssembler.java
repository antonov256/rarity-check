package com.atriviss.raritycheck.controller_rest.model_assembler;

import com.atriviss.raritycheck.controller_rest.SubcategoryRestController;
import com.atriviss.raritycheck.dto_api.SubcategoryApiDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubcategoryModelAssembler implements RepresentationModelAssembler<SubcategoryApiDto, EntityModel<SubcategoryApiDto>> {
    @Override
    public EntityModel<SubcategoryApiDto> toModel(SubcategoryApiDto apiDto) {
        return EntityModel.of(apiDto,
                linkTo(methodOn(SubcategoryRestController.class).one(apiDto.getId())).withSelfRel(),
                linkTo(methodOn(SubcategoryRestController.class).all()).withRel("subcategories"));
    }
}