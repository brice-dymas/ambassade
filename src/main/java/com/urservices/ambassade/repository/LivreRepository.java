package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Livre;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Livre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
