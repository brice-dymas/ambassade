package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.domain.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Service Interface for managing DonneesActe.
 */
public interface DonneesActeService {

    /**
     * Save a donneesActe.
     *
     * @param donneesActe the entity to save
     * @return the persisted entity
     */
    DonneesActe save(DonneesActe donneesActe);

    /**
     * Get all the donneesActes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DonneesActe> findAll(Pageable pageable);

    /**
     * Get the "id" donneesActe.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DonneesActe findOne(Long id);

    /**
     * Delete the "id" donneesActe.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<DonneesActe> searchAll(String reference, String registreSpecialRD, String nomEnfant, LocalDate dateDuJourChiffreDeb,
                                LocalDate dateDuJourChiffreFin, String registre, List<Statut> statut, String numero,
                                String nomPere, String prenomPere, String nomMere, String prenomMere,
                                LocalDate dateNaissanceChiffreDeb, LocalDate dateNaissanceChiffreFin,
                                Integer annee, List<Sexe> sexe, String villeNaissance, String adressePere,
                                String adresseMere, String temoins1, String temoins2, String idPere, String idMere,
                                String juridiction, String livre, String notes, String feuille, String acte, Pageable pageable);
}
