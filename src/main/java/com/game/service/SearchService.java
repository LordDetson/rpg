package com.game.service;

import com.game.service.criteria.PageCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SearchService<C, R> {

    default Page<R> search(C criteria, PageCriteria pageCriteria) {
        Specification<R> specification = buildSpecification(criteria);
        Pageable pageable = buildPageable(pageCriteria);
        return getSpecificationExecutor().findAll(specification, pageable);
    }

    default long count(C criteria) {
        Specification<R> specification = buildSpecification(criteria);
        return getSpecificationExecutor().count(specification);
    }

    Specification<R> buildSpecification(C criteria);

    Pageable buildPageable(PageCriteria pageCriteria);

    JpaSpecificationExecutor<R> getSpecificationExecutor();
}
