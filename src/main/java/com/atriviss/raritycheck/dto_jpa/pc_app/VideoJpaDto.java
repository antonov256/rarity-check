package com.atriviss.raritycheck.dto_jpa.pc_app;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video", schema = "public", catalog = "rc_app")
@Setter
@EqualsAndHashCode
public class VideoJpaDto {
    private Long id;
    private Integer itemId;
    private String bucketName;
    private String key;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public Integer getItemId() {
        return itemId;
    }

    @Basic
    @Column(name = "bucket_name", nullable = false, length = 10)
    public String getBucketName() {
        return bucketName;
    }

    @Basic
    @Column(name = "key", nullable = false, length = 100)
    public String getKey() {
        return key;
    }
}
