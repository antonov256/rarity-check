package com.atriviss.raritycheck.dto_jpa.rc_app;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class QualityJpaDto {
    private Integer value;

    public QualityJpaDto(Integer value) {
        this.value = value;
    }
}
