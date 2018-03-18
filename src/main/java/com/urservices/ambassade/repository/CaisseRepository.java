package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Caisse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Spring Data JPA repository for the Caisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    @Query("SELECT C FROM Caisse C WHERE C.reference >= :reference AND C.dateRetour BETWEEN :dateRetourDeb AND :dateRetourFin " +
        "AND C.dateDuJour BETWEEN :dateDuJourDeb AND :dateDuJourFin AND C.nom LIKE :nom AND C.prenom LIKE :prenom AND C.typeID LIKE :typeID " +
        "AND C.numeroID LIKE :numeroID AND C.serviceConcerne LIKE :serviceConcerne AND C.monnaie LIKE :monnaie AND C.montant >= :montant " +
        "AND C.telephone LIKE :telephone AND C.num >= :num AND C.paiement LIKE :paiement")
    Page<Caisse> search(@Param("reference") Long reference,
                        @Param("dateDuJourDeb")LocalDate dateDuJourDeb,
                        @Param("dateDuJourFin")LocalDate dateDuJourFin,
                        @Param("nom")String nom,
                        @Param("prenom")String prenom,
                        @Param("typeID")String typeID,
                        @Param("numeroID")String numeroID,
                        @Param("serviceConcerne")String serviceConcerne,
                        @Param("monnaie")String monnaie,
                        @Param("montant")BigDecimal montant,
                        @Param("dateRetourDeb")LocalDate dateRetourDeb,
                        @Param("dateRetourFin")LocalDate dateRetourFin,
                        @Param("telephone")String telephone,
                        @Param("num")Integer num,
                        @Param("paiement")String paiement, Pageable pageable);
}
