package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * Service Interface for managing Visa.
 */
public interface VisaService {

    /**
     * Save a visa.
     *
     * @param visa the entity to save
     * @return the persisted entity
     */
    Visa save(Visa visa);

    /**
     * Get all the visas.
     *
     *  @param nom
     * @param prenom
     * @param numeroPasseport
     * @param numeroVisa
     * @param typeService
     * @param categorie
     * @param dateEmissionDeb
     * @param dateEmissionFin
     * @param pageable the pagination information  @return the list of entities
     * */
    Page<Visa> findAll(String nom, String prenom, String numeroPasseport, Long numeroVisa, Long typeService,
                       Long categorie, LocalDate dateEmissionDeb, LocalDate dateEmissionFin, Pageable pageable);

    /**
     * Get the "id" visa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Visa findOne(Long id);

    /**
     * Delete the "id" visa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    Page<Visa> searchAll(String nom, String prenom, String nationalite, String numeroPasseport, Long numeroVisa,
                         LocalDate dateEmissionDeb, LocalDate dateEmissionFin, LocalDate dateExpirationDeb,
                         LocalDate dateExpirationFin, String type, String categorie, String adresse,Pageable pageable);
}
