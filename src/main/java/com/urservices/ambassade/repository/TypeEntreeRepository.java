package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.TypeEntree;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TypeEntree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEntreeRepository extends JpaRepository<TypeEntree, Long> {

}
