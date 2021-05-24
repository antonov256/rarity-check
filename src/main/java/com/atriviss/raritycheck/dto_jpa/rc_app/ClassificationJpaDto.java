package com.atriviss.raritycheck.dto_jpa.rc_app;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ClassificationJpaDto {
    private CategoryJpaDto category;
    private SubcategoryJpaDto subcategory;

    public ClassificationJpaDto(CategoryJpaDto category, SubcategoryJpaDto subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public CategoryJpaDto getCategory() {
        return this.category;
    }

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
    public SubcategoryJpaDto getSubcategory() {
        return this.subcategory;
    }
}
