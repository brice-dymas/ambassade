package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service Interface for managing Passeport.
 */
public interface PasseportService {

    /**
     * Save a passeport.
     *
     * @param passeport the entity to save
     * @return the persisted entity
     */
    Passeport save(Passeport passeport);

    /**
     * Get all the passeports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Passeport> findAll(Pageable pageable);

    /**
     * Get the "id" passeport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Passeport findOne(Long id);

    /**
     * Delete the "id" passeport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<Passeport> searchAll(String nom, String prenom,
                              String numeroPasseport, String neLe,
                              String lieuNaissance, List<Statut> etatCivils,
                              String adresse, String telephone,
                              String nif, String paysEmetteur,
                              LocalDate soumisLeDeb, LocalDate soumisLeFin,
                              LocalDate delivreLeDeb, LocalDate delivreLeFin,
                              BigDecimal montant, String remarques,
                              LocalDate dateEmissionDeb, LocalDate dateEmissionFin,
                              LocalDate dateExpirationDeb, LocalDate dateExpirationFin,
                              String remarquesR, String sms,
                              String sms2, String documents, Pageable pageable);
}
