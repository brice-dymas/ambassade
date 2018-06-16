package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.UniteOrganisationelleService;
import com.urservices.ambassade.domain.UniteOrganisationelle;
import com.urservices.ambassade.repository.UniteOrganisationelleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing UniteOrganisationelle.
 */
@Service
@Transactional
public class UniteOrganisationelleServiceImpl implements UniteOrganisationelleService {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationelleServiceImpl.class);

    private final UniteOrganisationelleRepository uniteOrganisationelleRepository;

    public UniteOrganisationelleServiceImpl(UniteOrganisationelleRepository uniteOrganisationelleRepository) {
        this.uniteOrganisationelleRepository = uniteOrganisationelleRepository;
    }

    /**
     * Save a uniteOrganisationelle.
     *
     * @param uniteOrganisationelle the entity to save
     * @return the persisted entity
     */
    @Override
    public UniteOrganisationelle save(UniteOrganisationelle uniteOrganisationelle) {
        log.debug("Request to save UniteOrganisationelle : {}", uniteOrganisationelle);
        return uniteOrganisationelleRepository.save(uniteOrganisationelle);
    }

    /**
     * Get all the uniteOrganisationelles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UniteOrganisationelle> findAll(Pageable pageable) {
        log.debug("Request to get all UniteOrganisationelles");
        return uniteOrganisationelleRepository.findAll(pageable);
    }

    /**
     * Get one uniteOrganisationelle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UniteOrganisationelle findOne(Long id) {
        log.debug("Request to get UniteOrganisationelle : {}", id);
        return uniteOrganisationelleRepository.findOne(id);
    }

    /**
     * Delete the uniteOrganisationelle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UniteOrganisationelle : {}", id);
        uniteOrganisationelleRepository.delete(id);
    }
}
