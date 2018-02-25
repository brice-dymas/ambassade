package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.DonneesActe;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DonneesActe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonneesActeRepository extends JpaRepository<DonneesActe, Long> {

}
