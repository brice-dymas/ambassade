package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Spring Data JPA repository for the Visa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VisaRepository extends JpaRepository<Visa, Long>, QueryDslPredicateExecutor<Visa> {
    @Query("SELECT R FROM Visa R WHERE R.nom LIKE :nom AND R.prenom LIKE :prenom AND R.nationalite LIKE :nationalite " +
        "AND R.numeroPasseport LIKE :numeroPasseport AND R.cedula LIKE :cedula AND R.numeroVisa >= :numeroVisa " +
        "AND R.dateEmission >= :dateEmissionDeb AND R.dateEmission <= :dateEmissionFin " +
        "AND R.dateExpiration >= :dateExpirationDeb AND R.dateExpiration >= :dateExpirationFin AND R.validePour >= :validePour " +
        "AND R.nombreEntree LIKE :nombreEntree AND R.type LIKE :pType AND R.categorie LIKE :categorie " +
        "AND R.taxes >= :taxes AND R.adresse LIKE :adresse AND R.remarques LIKE :remarques")
    Page<Visa> searchAll(@Param("nom") String nom, @Param("prenom") String prenom,
                         @Param("nationalite") String nationalite,
                         @Param("numeroPasseport") String numeroPasseport,
                         @Param("cedula") String cedula,
                         @Param("numeroVisa") Long numeroVisa,
                         @Param("dateEmissionDeb") LocalDate dateEmissionDeb,
                         @Param("dateEmissionFin") LocalDate dateEmissionFin,
                         @Param("dateExpirationDeb") LocalDate dateExpirationDeb,
                         @Param("dateExpirationFin") LocalDate dateExpirationFin,
                         @Param("validePour") Integer validePour,
                         @Param("nombreEntree") String nombreEntree,
                         @Param("pType") String type,
                         @Param("categorie") String categorie,
                         @Param("taxes") Integer taxes,
                         @Param("adresse") String adresse,
                         @Param("remarques") String remarques, Pageable pageable);

}
