package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Visa> findAll(Pageable pageable);

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
}
