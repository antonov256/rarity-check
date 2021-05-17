package com.atriviss.raritycheck.dto_jpa.pc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wish_item", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class WishItemJpaDto {
    private Integer id;
    private Integer userId;
    private ItemJpaDto item;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemJpaDto getItem() {
        return item;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }
}
