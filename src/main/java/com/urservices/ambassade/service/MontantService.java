package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Montant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Montant.
 */
public interface MontantService {

    /**
     * Save a montant.
     *
     * @param montant the entity to save
     * @return the persisted entity
     */
    Montant save(Montant montant);

    /**
     * Get all the montants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Montant> findAll(Pageable pageable);

    /**
     * Get the "id" montant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Montant findOne(Long id);

    /**
     * Delete the "id" montant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get monnaies using some parameters.
     *
     * @param monnaie the monnaie
     * @param produit
     * @return the list of entities
     */
    Page<Montant> findByMonnaieAndProduitAndMontant(String monnaie, String produit, Long montant, Pageable pageable);
}
