package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Spring Data JPA repository for the Visa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VisaRepository extends JpaRepository<Visa, Long>, QueryDslPredicateExecutor<Visa> {
    

}
