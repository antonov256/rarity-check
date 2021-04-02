package com.atriviss.raritycheck.dto_jpa.pc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "own_item", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class OwnItemJpaDto {
    private Integer id;
    private Integer itemId;
    private Integer userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public Integer getItemId() {
        return itemId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }
}
