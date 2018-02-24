package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Visa;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Visa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VisaRepository extends JpaRepository<Visa, Long> {

}
