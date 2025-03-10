package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class PrestamoSpecification implements Specification<Prestamo> {

    private final SearchCriteria criteria;

    public PrestamoSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Prestamo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }

        if (criteria.getOperation().equalsIgnoreCase(":>")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(java.time.LocalDate.class), (java.time.LocalDate) criteria.getValue());
        }

        if (criteria.getOperation().equalsIgnoreCase(":<")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(java.time.LocalDate.class), (java.time.LocalDate) criteria.getValue());
        }

        return null;
    }

    private Path<String> getPath(Root<Prestamo> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}