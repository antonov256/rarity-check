package com.atriviss.raritycheck.dto_jpa.pc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class ItemJpaDto {
    private Integer id;
    private String title;
    private String description;
    private CategoryJpaDto category;
    private SubcategoryJpaDto subcategory;
    private QualityJpaDto quality;

    private List<PhotoJpaDto> photos;
    private List<VideoJpaDto> videos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 10000)
    public String getDescription() {
        return description;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public CategoryJpaDto getCategory() {
        return category;
    }

    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
    public SubcategoryJpaDto getSubcategory() {
        return subcategory;
    }

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "quality_value"))
    @Column(name = "quality_value", nullable = false)
    public QualityJpaDto getQuality() {
        return quality;
    }

    @OneToMany(mappedBy = "itemId")
    public List<PhotoJpaDto> getPhotos() {
        return photos;
    }

    @OneToMany(mappedBy = "itemId")
    public List<VideoJpaDto> getVideos() {
        return videos;
    }
}
