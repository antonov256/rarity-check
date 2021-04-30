package com.atriviss.raritycheck.service.search;

import com.atriviss.raritycheck.controller_rest.exception.SearchQueryException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchSpecification<T> implements Specification<T> {
    private final SearchCriteria criteria;

    public SearchSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        try {
            switch (criteria.getOperation()) {
                case EQUALITY:
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                case NEGATION:
                    return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
                case GREATER_THAN_OR_EQUALS:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                case LESS_THAN:
                    return criteriaBuilder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
                case LESS_THAN_OR_EQUALS:
                    return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                case LIKE:
                    if (root.get(criteria.getKey()).getJavaType() == String.class) {
                        return criteriaBuilder.like(
                                criteriaBuilder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"
                        );
                    } else {
                        return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                    }
                default:
                    throw new SearchQueryException("Wrong criteria operation: " + criteria.getOperation());
            }
        } catch (IllegalArgumentException e) {
            throw new SearchQueryException("Something wrong with searchCriteria: " + criteria.toString(), e);
        }
    }
}
