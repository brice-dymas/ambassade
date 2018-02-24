package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Rapatriement;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Rapatriement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RapatriementRepository extends JpaRepository<Rapatriement, Long> {

}
