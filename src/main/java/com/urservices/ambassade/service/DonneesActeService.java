package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.DonneesActe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
}
