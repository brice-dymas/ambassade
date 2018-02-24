package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.RapatriementService;
import com.urservices.ambassade.domain.Rapatriement;
import com.urservices.ambassade.repository.RapatriementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Rapatriement.
 */
@Service
@Transactional
public class RapatriementServiceImpl implements RapatriementService {

    private final Logger log = LoggerFactory.getLogger(RapatriementServiceImpl.class);

    private final RapatriementRepository rapatriementRepository;

    public RapatriementServiceImpl(RapatriementRepository rapatriementRepository) {
        this.rapatriementRepository = rapatriementRepository;
    }

    /**
     * Save a rapatriement.
     *
     * @param rapatriement the entity to save
     * @return the persisted entity
     */
    @Override
    public Rapatriement save(Rapatriement rapatriement) {
        log.debug("Request to save Rapatriement : {}", rapatriement);
        return rapatriementRepository.save(rapatriement);
    }

    /**
     * Get all the rapatriements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Rapatriement> findAll(Pageable pageable) {
        log.debug("Request to get all Rapatriements");
        return rapatriementRepository.findAll(pageable);
    }

    /**
     * Get one rapatriement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Rapatriement findOne(Long id) {
        log.debug("Request to get Rapatriement : {}", id);
        return rapatriementRepository.findOne(id);
    }

    /**
     * Delete the rapatriement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rapatriement : {}", id);
        rapatriementRepository.delete(id);
    }
}
