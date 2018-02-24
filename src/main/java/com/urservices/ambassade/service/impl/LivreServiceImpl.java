package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.LivreService;
import com.urservices.ambassade.domain.Livre;
import com.urservices.ambassade.repository.LivreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Livre.
 */
@Service
@Transactional
public class LivreServiceImpl implements LivreService {

    private final Logger log = LoggerFactory.getLogger(LivreServiceImpl.class);

    private final LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    /**
     * Save a livre.
     *
     * @param livre the entity to save
     * @return the persisted entity
     */
    @Override
    public Livre save(Livre livre) {
        log.debug("Request to save Livre : {}", livre);
        return livreRepository.save(livre);
    }

    /**
     * Get all the livres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Livre> findAll(Pageable pageable) {
        log.debug("Request to get all Livres");
        return livreRepository.findAll(pageable);
    }

    /**
     * Get one livre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Livre findOne(Long id) {
        log.debug("Request to get Livre : {}", id);
        return livreRepository.findOne(id);
    }

    /**
     * Delete the livre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Livre : {}", id);
        livreRepository.delete(id);
    }
}
