package com.atriviss.raritycheck.dto_api.to_create;

import com.atriviss.raritycheck.dto_api.ClassificationApiDto;
import com.atriviss.raritycheck.dto_api.QualityApiDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemToCreate {
    private String title;
    private String description;

    private ClassificationApiDto classification;
    private QualityApiDto quality;

    private List<PhotoToCreate> photos;
}
