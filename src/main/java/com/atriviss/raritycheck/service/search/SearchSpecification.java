package com.atriviss.raritycheck.service.search;

import com.atriviss.raritycheck.controller_rest.exception.SearchQueryException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

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
                    return criteriaBuilder.equal(getPath(root, criteria.getKey()), criteria.getValue());
                case NEGATION:
                    return criteriaBuilder.notEqual(getPath(root, criteria.getKey()), criteria.getValue());
                case GREATER_THAN:
                    return criteriaBuilder.greaterThan(getPathString(root, criteria.getKey()), criteria.getValue().toString());
                case GREATER_THAN_OR_EQUALS:
                    return criteriaBuilder.greaterThanOrEqualTo(getPathString(root, criteria.getKey()), criteria.getValue().toString());
                case LESS_THAN:
                    return criteriaBuilder.lessThan(getPathString(root, criteria.getKey()), criteria.getValue().toString());
                case LESS_THAN_OR_EQUALS:
                    return criteriaBuilder.lessThanOrEqualTo(getPathString(root, criteria.getKey()), criteria.getValue().toString());
                case LIKE:
                    Path<T> path = getPath(root, criteria.getKey());
                    if (path.getJavaType() == String.class) {
                        String likePattern = "%" + criteria.getValue().toString().toLowerCase() + "%";
                        return criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), likePattern);
                    } else {
                        return criteriaBuilder.equal(path, criteria.getValue());
                    }
                default:
                    throw new SearchQueryException("Wrong criteria operation: " + criteria.getOperation());
            }
        } catch (IllegalArgumentException e) {
            throw new SearchQueryException("Something wrong with searchCriteria: " + criteria.toString() + ". Error message:" + e.getMessage(), e);
        }
    }

    private Path<String> getPathString(Root<T> root, String attributeName) {
        Path<String> path = (Path<String>) getPath(root, attributeName);
        return path;
    }

    private Path<T> getPath(Root<T> root, String attributeName) {
        Path<T> path = root;
        for (String part : attributeName.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }
}
