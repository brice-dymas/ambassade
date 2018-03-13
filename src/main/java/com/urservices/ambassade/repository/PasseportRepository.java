package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the Passeport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PasseportRepository extends JpaRepository<Passeport, Long> {
    @Query("SELECT P FROM Passeport P WHERE P.nom LIKE :nom AND P.prenom LIKE :prenom AND P.numeroPasseport LIKE :numeroPasseport " +
        " AND P.neLe BETWEEN :neLeDeb AND :neLeFin AND P.lieuNaissance LIKE :lieuNaissance AND P.etatCivil IN :etatCivil AND P.adresse LIKE :adresse " +
        " AND P.telephone LIKE :telephone AND P.nif LIKE :nif AND P.paysEmetteur LIKE :paysEmetteur AND P.soumisLe >= :soumisLeDeb " +
        "AND P.soumisLe <= :soumisLeFin AND P.delivreLe >= :delivreLeDeb AND P.delivreLe <= :delivreLeFin" +
        "  AND P.montant >= :montant AND P.remarques LIKE :remarques AND  P.dateEmission >= :dateEmissionDeb " +
        "AND  P.dateEmission <= :dateEmissionFin AND P.dateExpiration >= :dateExpirationDeb AND P.dateExpiration <= :dateExpirationFin " +
        "AND P.remarquesR LIKE :remarquesR AND P.sms LIKE :sms AND P.sms2 LIKE :sms2 AND P.documents LIKE :documents")
    Page<Passeport> searchAll(@Param("nom") String codeISBN, @Param("prenom") String auteur,
                              @Param("numeroPasseport") String numeroPasseport, @Param("neLeDeb") LocalDate neLeDeb,
                              @Param("neLeFin") LocalDate neLeFin,@Param("lieuNaissance") String lieuNaissance,
                              @Param("etatCivil") List<Statut> etatCivils,@Param("adresse") String adresse,
                              @Param("telephone") String telephone, @Param("nif") String nif,
                              @Param("paysEmetteur") String paysEmetteur, @Param("soumisLeDeb") LocalDate soumisLeDeb,
                              @Param("soumisLeFin") LocalDate soumisLeFin,@Param("delivreLeDeb") LocalDate delivreLeDeb,
                              @Param("delivreLeFin") LocalDate delivreLeFin, @Param("montant")BigDecimal montant,
                              @Param("remarques") String remarques,@Param("dateEmissionDeb") LocalDate dateEmissionDeb,
                              @Param("dateEmissionFin") LocalDate dateEmissionFin,
                              @Param("dateExpirationDeb") LocalDate dateExpirationDeb,
                              @Param("dateExpirationFin") LocalDate dateExpirationFin,
                              @Param("remarquesR") String remarquesR, @Param("sms") String sms,
                              @Param("sms2") String sms2, @Param("documents") String documents, Pageable pageable);

}
