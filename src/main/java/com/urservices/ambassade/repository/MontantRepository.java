package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Montant;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Montant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MontantRepository extends JpaRepository<Montant, Long> {

}
