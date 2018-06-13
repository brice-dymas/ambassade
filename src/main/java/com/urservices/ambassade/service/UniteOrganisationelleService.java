package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.UniteOrganisationelle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UniteOrganisationelle.
 */
public interface UniteOrganisationelleService {

    /**
     * Save a uniteOrganisationelle.
     *
     * @param uniteOrganisationelle the entity to save
     * @return the persisted entity
     */
    UniteOrganisationelle save(UniteOrganisationelle uniteOrganisationelle);

    /**
     * Get all the uniteOrganisationelles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UniteOrganisationelle> findAll(Pageable pageable);

    /**
     * Get the "id" uniteOrganisationelle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UniteOrganisationelle findOne(Long id);

    /**
     * Delete the "id" uniteOrganisationelle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
