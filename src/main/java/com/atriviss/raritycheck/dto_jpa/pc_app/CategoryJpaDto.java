package com.atriviss.raritycheck.dto_jpa.pc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class CategoryJpaDto {
    private Integer id;
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }
}
