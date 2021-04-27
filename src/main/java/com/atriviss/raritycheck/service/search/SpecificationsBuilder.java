package com.atriviss.raritycheck.service.search;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificationsBuilder<T> {
    private final Specification<T> NO_SPECIFICATION = new SearchSpecification<>(new SearchCriteria("id", SearchOperation.GREATER_THAN_OR_EQUALS, 0));

    private final List<SearchCriteria> criteriaList;

    public SpecificationsBuilder() {
        criteriaList = new ArrayList<>();
    }

    public SpecificationsBuilder<T> with(String key, SearchOperation operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (criteriaList.size() == 0) {
            return NO_SPECIFICATION;
        }

        List<? extends Specification<T>> specsFromQuery = criteriaList.stream()
                .map(SearchSpecification<T>::new)
                .collect(Collectors.toList());

        Specification<T> resultSpec = specsFromQuery.get(0);

        for (int i = 1; i < criteriaList.size(); i++) {
            SearchCriteria searchCriteria = criteriaList.get(i);
            switch (searchCriteria.getCriteriaPredicate()){
                case OR:
                    resultSpec = Specification.where(resultSpec).or(specsFromQuery.get(i));
                    break;
                case AND:
                    resultSpec = Specification.where(resultSpec).and(specsFromQuery.get(i));
                    break;
            }
        }

        return resultSpec;
    }
}
