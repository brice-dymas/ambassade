package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.TypeService;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TypeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Long>, QueryDslPredicateExecutor<TypeService> {

}
