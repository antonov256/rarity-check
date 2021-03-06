package com.atriviss.raritycheck.controller_rest.model_assembler;

import com.atriviss.raritycheck.controller_rest.CategoryRestController;
import com.atriviss.raritycheck.dto_api.CategoryApiDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryModelAssembler  implements RepresentationModelAssembler<CategoryApiDto, EntityModel<CategoryApiDto>> {
    @Override
    public EntityModel<CategoryApiDto> toModel(CategoryApiDto apiDto) {
        return EntityModel.of(apiDto,
                linkTo(methodOn(CategoryRestController.class).one(apiDto.getId())).withSelfRel(),
                linkTo(methodOn(CategoryRestController.class).all(null)).withRel("categories"));
    }
}