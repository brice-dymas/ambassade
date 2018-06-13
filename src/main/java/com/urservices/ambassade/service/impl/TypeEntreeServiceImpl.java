package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.TypeEntreeService;
import com.urservices.ambassade.domain.TypeEntree;
import com.urservices.ambassade.repository.TypeEntreeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TypeEntree.
 */
@Service
@Transactional
public class TypeEntreeServiceImpl implements TypeEntreeService {

    private final Logger log = LoggerFactory.getLogger(TypeEntreeServiceImpl.class);

    private final TypeEntreeRepository typeEntreeRepository;

    public TypeEntreeServiceImpl(TypeEntreeRepository typeEntreeRepository) {
        this.typeEntreeRepository = typeEntreeRepository;
    }

    /**
     * Save a typeEntree.
     *
     * @param typeEntree the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeEntree save(TypeEntree typeEntree) {
        log.debug("Request to save TypeEntree : {}", typeEntree);
        return typeEntreeRepository.save(typeEntree);
    }

    /**
     * Get all the typeEntrees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeEntree> findAll(Pageable pageable) {
        log.debug("Request to get all TypeEntrees");
        return typeEntreeRepository.findAll(pageable);
    }

    /**
     * Get one typeEntree by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TypeEntree findOne(Long id) {
        log.debug("Request to get TypeEntree : {}", id);
        return typeEntreeRepository.findOne(id);
    }

    /**
     * Delete the typeEntree by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeEntree : {}", id);
        typeEntreeRepository.delete(id);
    }
}
