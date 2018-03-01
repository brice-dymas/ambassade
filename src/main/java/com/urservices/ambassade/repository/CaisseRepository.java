package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Caisse;
import com.urservices.ambassade.domain.Montant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


/**
 * Spring Data JPA repository for the Caisse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaisseRepository extends JpaRepository<Caisse, Long> {

    @Query("SELECT C FROM Caisse C WHERE C.monnaie LIKE :monnaie AND C.nom LIKE :nom AND C.prenom LIKE :prenom " +
        "AND C.typeID LIKE :typeID AND C.serviceConcerne LIKE :serviceConcerne AND  C.telephone LIKE :telephone AND " +
        " C.paiement LIKE :paiement AND C.numeroID LIKE :numeroID")
    Page<Caisse> search(@Param("monnaie") String monnaie, @Param("nom") String nom, @Param("prenom") String prenom,
                        @Param("typeID") String typeID, @Param("serviceConcerne") String serviceConcerne,
                        @Param("telephone") String telephone, @Param("paiement") String paiement,
                        @Param("numero") String numero, Pageable pageable);

    @Query("SELECT C FROM Caisse C WHERE C.monnaie LIKE :monnaie AND C.nom LIKE :nom AND C.prenom LIKE :prenom " +
        "AND C.typeID LIKE :typeID AND C.serviceConcerne LIKE :serviceConcerne AND C.telephone LIKE :telephone AND " +
        " C.paiement LIKE :paiement AND C.numeroID LIKE :numeroID AND C.dateDuJour = :dateJour")
    Page<Caisse> searchWithDateDuJour(@Param("dateJour") ZonedDateTime dateDuJour, @Param("monnaie") String monnaie,
                                      @Param("nom") String nom, @Param("prenom") String prenom,
                                      @Param("typeID") String typeID, @Param("serviceConcerne") String serviceConcerne,
                                      @Param("telephone") String telephone, @Param("paiement") String paiement,
                                      @Param("numero") String numero, Pageable pageable);

    @Query("SELECT C FROM Caisse C WHERE C.monnaie LIKE :monnaie AND C.nom LIKE :nom AND C.prenom LIKE :prenom " +
        "AND C.typeID LIKE :typeID AND C.serviceConcerne LIKE :serviceConcerne AND C.telephone LIKE :telephone AND " +
        " C.paiement LIKE :paiement AND C.numeroID LIKE :numeroID AND C.dateRetour = :dateRetour")
    Page<Caisse> searchWithDateRetour(@Param("dateRetour") ZonedDateTime dateRetour, @Param("monnaie") String monnaie,
                                      @Param("nom") String nom, @Param("prenom") String prenom,
                                      @Param("typeID") String typeID, @Param("serviceConcerne") String serviceConcerne,
                                      @Param("telephone") String telephone, @Param("paiement") String paiement,
                                      @Param("numero") String numero, Pageable pageable);

    @Query("SELECT C FROM Caisse C WHERE C.monnaie LIKE :monnaie AND C.nom LIKE :nom AND C.prenom LIKE :prenom " +
        "AND C.typeID LIKE :typeID AND C.serviceConcerne LIKE :serviceConcerne AND C.telephone LIKE :telephone AND " +
        " C.paiement LIKE :paiement AND C.numeroID LIKE :numeroID AND C.dateRetour = :dateRetour " +
        "  AND C.dateDuJour = :dateJour")
    Page<Caisse> searchWithDateDuJourAndDateRetour(@Param("dateJour") ZonedDateTime dateDuJour,
                                                   @Param("dateRetour") ZonedDateTime dateRetour,
                                                   @Param("monnaie") String monnaie,
                                      @Param("nom") String nom, @Param("prenom") String prenom,
                                      @Param("typeID") String typeID, @Param("serviceConcerne") String serviceConcerne,
                                      @Param("telephone") String telephone, @Param("paiement") String paiement,
                                      @Param("numero") String numero, Pageable pageable);

    @Query("SELECT C FROM Caisse C WHERE C.monnaie LIKE :monnaie AND C.reference >= :reference AND C.montant >= :montant " +
        "AND C.nom LIKE :nom AND C.prenom LIKE :prenom AND C.num >= :num AND C.typeID LIKE :typeID " +
        " AND C.serviceConcerne LIKE :serviceConcerne AND C.telephone LIKE :telephone AND C.paiement LIKE :paiement " +
        " AND C.numeroID LIKE :numeroID AND C.dateRetour = :dateRetour AND C.dateDuJour = :dateJour")
    Page<Caisse> searchAll(@Param("dateJour") ZonedDateTime dateDuJour,
                           @Param("reference") Long reference,
                           @Param("montant") BigDecimal montant,
                           @Param("num") Integer num,
                           @Param("dateRetour") ZonedDateTime dateRetour,
                           @Param("monnaie") String monnaie,
                           @Param("nom") String nom,
                           @Param("prenom") String prenom,
                           @Param("typeID") String typeID,
                           @Param("serviceConcerne") String serviceConcerne,
                           @Param("telephone") String telephone,
                           @Param("paiement") String paiement,
                           @Param("numero") String numero, Pageable pageable);



}
