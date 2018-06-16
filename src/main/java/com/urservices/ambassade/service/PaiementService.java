package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Paiement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * Service Interface for managing Paiement.
 */
public interface PaiementService {

    /**
     * Save a paiement.
     *
     * @param paiement the entity to save
     * @return the persisted entity
     */
    Paiement save(Paiement paiement);

    /**
     * Get all the paiements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Paiement> findAll(Pageable pageable);

    /**
     * Get all the paiements using params.
     *
     *
     * @param numeroPaiement
     *@param pageable the pagination information  @return the list of entities
     */
    Page<Paiement> findAll(String numeroPaiement, LocalDate datePaiement, LocalDate datePaiementFin,
                           Long visa, Long uniteOrganisationelle, Long typeService, Pageable pageable);

    /**
     * Get the "id" paiement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Paiement findOne(Long id);

    /**
     * Delete the "id" paiement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
