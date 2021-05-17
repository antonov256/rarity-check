package com.atriviss.raritycheck.service.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private CriteriaPredicate criteriaPredicate;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.criteriaPredicate = CriteriaPredicate.AND;
    }
}
