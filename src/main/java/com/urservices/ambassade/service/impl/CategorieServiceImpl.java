package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.CategorieService;
import com.urservices.ambassade.domain.Categorie;
import com.urservices.ambassade.repository.CategorieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Categorie.
 */
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final Logger log = LoggerFactory.getLogger(CategorieServiceImpl.class);

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    /**
     * Save a categorie.
     *
     * @param categorie the entity to save
     * @return the persisted entity
     */
    @Override
    public Categorie save(Categorie categorie) {
        log.debug("Request to save Categorie : {}", categorie);
        return categorieRepository.save(categorie);
    }

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Categorie> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categorieRepository.findAll(pageable);
    }

    /**
     * Get one categorie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Categorie findOne(Long id) {
        log.debug("Request to get Categorie : {}", id);
        return categorieRepository.findOne(id);
    }

    /**
     * Delete the categorie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Categorie : {}", id);
        categorieRepository.delete(id);
    }

    @Override
    public Page<Categorie> search(String nomCategorie, Pageable pageable) {
        return categorieRepository.search("%"+nomCategorie+"%",pageable);
    }
}
