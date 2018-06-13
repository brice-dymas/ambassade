package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.TypeEntree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TypeEntree.
 */
public interface TypeEntreeService {

    /**
     * Save a typeEntree.
     *
     * @param typeEntree the entity to save
     * @return the persisted entity
     */
    TypeEntree save(TypeEntree typeEntree);

    /**
     * Get all the typeEntrees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeEntree> findAll(Pageable pageable);

    /**
     * Get the "id" typeEntree.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TypeEntree findOne(Long id);

    /**
     * Delete the "id" typeEntree.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
