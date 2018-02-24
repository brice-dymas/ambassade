package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Monnaie;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Monnaie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonnaieRepository extends JpaRepository<Monnaie, Long> {

}
