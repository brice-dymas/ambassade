package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TypeService.
 */
public interface TypeServiceService {

    /**
     * Save a typeService.
     *
     * @param typeService the entity to save
     * @return the persisted entity
     */
    TypeService save(TypeService typeService);

    /**
     * Get all the typeServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeService> findAll(Pageable pageable);

    /**
     * Get the "id" typeService.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TypeService findOne(Long id);

    /**
     * Delete the "id" typeService.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
