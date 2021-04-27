package com.atriviss.raritycheck.controller_rest.model_assembler;

import com.atriviss.raritycheck.controller_rest.ItemPhotosRestController;
import com.atriviss.raritycheck.controller_rest.PhotosRestController;
import com.atriviss.raritycheck.dto_api.PhotoApiDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoModelAssembler implements SimpleRepresentationModelAssembler<PhotoApiDto> {
    @Override
    public void addLinks(EntityModel<PhotoApiDto> resource) {
        PhotoApiDto content = resource.getContent();
        if (content == null || content.getItemId() == null) {
            return;
        }

        resource.add(
                linkTo(methodOn(PhotosRestController.class).one(content.getId()))
                        .withSelfRel()
        );

        resource.add(
                linkTo(methodOn(ItemPhotosRestController.class).itemPhotos(content.getItemId()))
                        .withRel("itemPhotos")
        );
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<PhotoApiDto>> resources) {
        Collection<EntityModel<PhotoApiDto>> content = resources.getContent();

        Optional<EntityModel<PhotoApiDto>> firstEntityModelOptional = content.stream().findFirst();
        if(firstEntityModelOptional.isEmpty())
            return;

        EntityModel<PhotoApiDto> entityModel = firstEntityModelOptional.get();
        PhotoApiDto photoApiDto = entityModel.getContent();

        if (photoApiDto != null && photoApiDto.getItemId() != null) {
            resources.add(
                    linkTo(methodOn(ItemPhotosRestController.class).itemPhotos(photoApiDto.getItemId()))
                            .withRel("itemPhotos")
            );
        }
    }
}
