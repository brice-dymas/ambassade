package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Passeport;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Passeport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasseportRepository extends JpaRepository<Passeport, Long> {

}
