package com.atriviss.raritycheck.dto_jpa.rc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subcategory", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class SubcategoryJpaDto {
    private Integer id;
    private Integer categoryId;
    private String name;
    private Integer description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "description", nullable = true)
    public Integer getDescription() {
        return description;
    }
}
