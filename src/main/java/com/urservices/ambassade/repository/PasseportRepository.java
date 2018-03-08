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
        " AND P.neLe LIKE :neLe AND P.lieuNaissance LIKE :lieuNaissance AND P.etatCivil IN :etatCivil AND P.adresse LIKE :adresse " +
        " AND P.telephone LIKE :telephone AND P.nif LIKE :nif AND P.paysEmetteur LIKE :paysEmetteur AND P.soumisLe >= :soumisLe" +
        " AND P.delivreLe >= :delivreLe AND P.montant >= :montant AND P.remarques LIKE :remarques AND  P.dateEmission >= :dateEmission " +
        "AND P.dateExpiration >= :dateExpiration AND P.remarquesR LIKE :remarquesR AND P.sms LIKE :sms " +
        "AND P.sms2 LIKE :sms2 AND P.documents LIKE :documents")
    Page<Passeport> searchAll(@Param("nom") String codeISBN, @Param("prenom") String auteur,
                              @Param("numeroPasseport") String numeroPasseport, @Param("neLe") String neLe,
                              @Param("lieuNaissance") String lieuNaissance, @Param("etatCivil") List<Statut> etatCivils,
                              @Param("adresse") String adresse, @Param("telephone") String telephone,
                              @Param("nif") String nif, @Param("paysEmetteur") String paysEmetteur,
                              @Param("soumisLe") LocalDate soumisLe, @Param("delivreLe") LocalDate delivreLe,
                              @Param("montant")BigDecimal montant, @Param("remarques") String remarques,
                              @Param("dateEmission") LocalDate dateEmission, @Param("dateExpiration") LocalDate dateExpiration,
                              @Param("remarquesR") String remarquesR, @Param("sms") String sms,
                              @Param("sms2") String sms2, @Param("documents") String documents, Pageable pageable);

}
