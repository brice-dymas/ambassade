package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Rapatriement;
import com.urservices.ambassade.domain.enumeration.Sexe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Spring Data JPA repository for the Rapatriement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RapatriementRepository extends JpaRepository<Rapatriement, Long>, QueryDslPredicateExecutor<Rapatriement> {
    @Query("SELECT R FROM Rapatriement R WHERE R.reference >= :reference " +
        "AND R.numeroDossier LIKE :numeroDossier AND R.nom LIKE :nom AND R.prenom LIKE :prenom " +
        "AND R.dateNaissance >= :dateNaissanceDeb AND R.dateNaissance <= :dateNaissanceFin " +
        "AND R.documentID LIKE :documentID AND R.sexe IN :sexe AND R.motif LIKE :motif " +
        "AND R.dateRapatriement >= :dateRapatriementDeb AND R.dateRapatriement <= :dateRapatriementFin " +
        "AND R.frontiere LIKE :frontiere")
    Page<Rapatriement> searchAll(@Param("reference") Integer reference,
                                 @Param("numeroDossier") String numeroDossier,
                                 @Param("nom") String nom,
                                 @Param("prenom") String prenom,
                                 @Param("dateNaissanceDeb") LocalDate dateNaissanceDeb,
                                 @Param("dateNaissanceFin") LocalDate dateNaissanceFin,
                                 @Param("documentID") String documentID,
                                 @Param("sexe") List<Sexe> sexe,
                                 @Param("motif") String motif,
                                 @Param("dateRapatriementDeb") LocalDate dateRapatriementDeb,
                                 @Param("dateRapatriementFin") LocalDate dateRapatriementFin,
                                 @Param("frontiere") String frontiere, Pageable pageable);

}
