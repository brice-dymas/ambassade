package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Paiement;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Paiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    @Query("select paiement from Paiement paiement where paiement.user.login = ?#{principal.username}")
    List<Paiement> findByUserIsCurrentUser();

}
