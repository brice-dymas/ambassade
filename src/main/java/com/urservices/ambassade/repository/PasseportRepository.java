package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the Passeport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasseportRepository extends JpaRepository<Passeport, Long>, QueryDslPredicateExecutor<Passeport> {

}
