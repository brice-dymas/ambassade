package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Caisse;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Caisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long> {

}
