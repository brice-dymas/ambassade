package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Rapatriement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Rapatriement.
 */
public interface RapatriementService {

    /**
     * Save a rapatriement.
     *
     * @param rapatriement the entity to save
     * @return the persisted entity
     */
    Rapatriement save(Rapatriement rapatriement);

    /**
     * Get all the rapatriements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Rapatriement> findAll(Pageable pageable);

    /**
     * Get the "id" rapatriement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Rapatriement findOne(Long id);

    /**
     * Delete the "id" rapatriement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
