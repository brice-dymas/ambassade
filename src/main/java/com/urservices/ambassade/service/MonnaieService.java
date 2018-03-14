package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Monnaie;
import com.urservices.ambassade.domain.Montant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Monnaie.
 */
public interface MonnaieService {

    /**
     * Save a monnaie.
     *
     * @param monnaie the entity to save
     * @return the persisted entity
     */
    Monnaie save(Monnaie monnaie);

    /**
     * Get all the monnaies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Monnaie> findAll(Pageable pageable);





    /**
     * Get the "id" monnaie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Monnaie findOne(Long id);

    /**
     * Delete the "id" monnaie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get Monnaie using params.
     *
     * @param type the type of the entity
     * @return the entity
     */
    Page<Monnaie> searchAll(String type, String produit,Long montant, Pageable pageable);
}
