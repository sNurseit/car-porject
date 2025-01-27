package com.nurseit.carproject.specification;

import com.nurseit.carproject.dto.CarFilterDto;
import com.nurseit.carproject.entity.Car;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class CarSpecification implements Specification<Car> {
    private final CarFilterDto requestFilter;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (requestFilter.getMake() != null && !requestFilter.getMake().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("make")),
                    "%" + requestFilter.getMake().toLowerCase() + "%"
            ));
        }

        if (requestFilter.getModel() != null && !requestFilter.getModel().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("model")),
                    "%" + requestFilter.getModel().toLowerCase() + "%"
            ));
        }

        if (requestFilter.getYear() != null) {
            predicates.add(criteriaBuilder.equal(root.get("year"), requestFilter.getYear()));
        }

        if (requestFilter.getPrice() != null) {
            predicates.add(criteriaBuilder.equal(root.get("price"), requestFilter.getPrice()));
        }

        if (requestFilter.getVin() != null && !requestFilter.getVin().isEmpty()) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("vin")),
                    "%" + requestFilter.getVin().toLowerCase() + "%"
            ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
