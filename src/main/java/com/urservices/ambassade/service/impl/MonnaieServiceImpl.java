package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.domain.Montant;
import com.urservices.ambassade.service.MonnaieService;
import com.urservices.ambassade.domain.Monnaie;
import com.urservices.ambassade.repository.MonnaieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Monnaie.
 */
@Service
@Transactional
public class MonnaieServiceImpl implements MonnaieService {

    private final Logger log = LoggerFactory.getLogger(MonnaieServiceImpl.class);

    private final MonnaieRepository monnaieRepository;

    public MonnaieServiceImpl(MonnaieRepository monnaieRepository) {
        this.monnaieRepository = monnaieRepository;
    }

    /**
     * Save a monnaie.
     *
     * @param monnaie the entity to save
     * @return the persisted entity
     */
    @Override
    public Monnaie save(Monnaie monnaie) {
        log.debug("Request to save Monnaie : {}", monnaie);
        return monnaieRepository.save(monnaie);
    }

    /**
     * Get all the monnaies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Monnaie> findAll(Pageable pageable) {
        log.debug("Request to get all Monnaies");
        return monnaieRepository.findAll(pageable);
    }

    /**
     * Get one monnaie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Monnaie findOne(Long id) {
        log.debug("Request to get Monnaie : {}", id);
        return monnaieRepository.findOne(id);
    }

    /**
     * Delete the monnaie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Monnaie : {}", id);
        monnaieRepository.delete(id);
    }
}
