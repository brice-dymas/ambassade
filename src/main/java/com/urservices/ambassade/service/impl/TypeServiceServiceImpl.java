package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.TypeServiceService;
import com.urservices.ambassade.domain.TypeService;
import com.urservices.ambassade.repository.TypeServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TypeService.
 */
@Service
@Transactional
public class TypeServiceServiceImpl implements TypeServiceService {

    private final Logger log = LoggerFactory.getLogger(TypeServiceServiceImpl.class);

    private final TypeServiceRepository typeServiceRepository;

    public TypeServiceServiceImpl(TypeServiceRepository typeServiceRepository) {
        this.typeServiceRepository = typeServiceRepository;
    }

    /**
     * Save a typeService.csv.
     *
     * @param typeService the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeService save(TypeService typeService) {
        log.debug("Request to save TypeService : {}", typeService);
        return typeServiceRepository.save(typeService);
    }

    /**
     * Get all the typeServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeService> findAll(Pageable pageable) {
        log.debug("Request to get all TypeServices");
        return typeServiceRepository.findAll(pageable);
    }

    /**
     * Get one typeService.csv by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TypeService findOne(Long id) {
        log.debug("Request to get TypeService : {}", id);
        return typeServiceRepository.findOne(id);
    }

    /**
     * Delete the typeService.csv by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeService : {}", id);
        typeServiceRepository.delete(id);
    }
}
