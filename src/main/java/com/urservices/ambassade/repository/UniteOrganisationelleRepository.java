package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.UniteOrganisationelle;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UniteOrganisationelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UniteOrganisationelleRepository extends JpaRepository<UniteOrganisationelle, Long> {

}
