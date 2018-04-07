package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.domain.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the DonneesActe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonneesActeRepository extends JpaRepository<DonneesActe, Long>, QueryDslPredicateExecutor<DonneesActe> {
    @Query("SELECT P FROM DonneesActe P WHERE P.reference LIKE :reference AND P.registreSpecialRD LIKE :registreSpecialRD " +
        "AND P.nomEnfant LIKE :nomEnfant AND P.dateDuJourChiffre BETWEEN :dateDuJourChiffreDeb AND :dateDuJourChiffreFin " +
        "AND P.registre LIKE :registre AND P.statut IN :statut AND P.numero LIKE :numero " +
        "AND P.nomPere LIKE :nomPere AND P.prenomPere LIKE :prenomPere AND P.nomMere LIKE :nomMere AND P.prenomMere LIKE :prenomMere " +
        "AND P.dateNaissanceChiffre BETWEEN :dateNaissanceChiffreDeb AND :dateNaissanceChiffreFin " +
        "AND P.annee >= :annee AND  P.sexe IN :sexe  AND  P.villeNaissance LIKE :villeNaissance AND P.adressePere LIKE :adressePere " +
        "AND P.adresseMere LIKE :adresseMere  AND P.temoins1 LIKE :temoins1 AND P.temoins2 LIKE :temoins2 AND P.idPere LIKE :idPere " +
        "AND P.idMere LIKE :idMere AND  P.juridiction LIKE :juridiction AND P.livre LIKE :livre AND P.notes LIKE :notes " +
        "AND P.feuille LIKE :feuille AND P.acte LIKE :acte "
    )
    Page<DonneesActe> searchAll(@Param("reference") String reference, @Param("registreSpecialRD") String registreSpecialRD,
                              @Param("nomEnfant") String nomEnfant, @Param("dateDuJourChiffreDeb") LocalDate dateDuJourChiffreDeb,
                              @Param("dateDuJourChiffreFin") LocalDate dateDuJourChiffreFin, @Param("registre") String registre,
                              @Param("statut") List<Statut> statut, @Param("numero") String numero,
                              @Param("nomPere") String nomPere, @Param("prenomPere") String prenomPere,
                              @Param("nomMere") String nomMere, @Param("prenomMere") String prenomMere,
                              @Param("dateNaissanceChiffreDeb") LocalDate dateNaissanceChiffreDeb,
                              @Param("dateNaissanceChiffreFin") LocalDate dateNaissanceChiffreFin,
                              @Param("annee")Integer annee, @Param("sexe")List<Sexe> sexe,
                              @Param("villeNaissance") String villeNaissance, @Param("adressePere") String adressePere,
                              @Param("adresseMere") String adresseMere, @Param("temoins1") String temoins1,
                              @Param("temoins2") String temoins2, @Param("idPere") String idPere,
                              @Param("idMere") String idMere, @Param("juridiction") String juridiction,
                              @Param("livre") String livre, @Param("notes") String notes,
                              @Param("feuille") String feuille, @Param("acte") String acte, Pageable pageable);
}
