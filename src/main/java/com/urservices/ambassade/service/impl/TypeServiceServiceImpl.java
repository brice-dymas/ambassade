package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.config.Constants;
import com.urservices.ambassade.domain.QTypeService;
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
     * Save a typeService.
     *
     * @param typeService the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeService save(TypeService typeService) {
        log.debug("Request to save TypeService : {}", typeService);
        return typeServiceRepository.save(typeService);
    }

    @Override
    public Page<TypeService> findAllForVisa(Pageable pageable) {
        log.debug("Request to get all TypeServices for Visa");
        QTypeService typeService = QTypeService.typeService;
        BooleanExpression predicate = typeService.uniteOrganisationelle.id.eq(Constants.UNITE_ORGANISATIONNELLE_VISA);
        return typeServiceRepository.findAll(predicate,pageable);
    }

    @Override
    public Page<TypeService> findAllForPasseport(Pageable pageable) {
        log.debug("Request to get all TypeServices for Passeport");
        QTypeService typeService = QTypeService.typeService;
        BooleanExpression predicate = typeService.uniteOrganisationelle.id.eq(Constants.UNITE_ORGANISATIONNELLE_PASSEPORT);
        return typeServiceRepository.findAll(predicate,pageable);
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
     * Get one typeService by id.
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
     * Delete the typeService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeService : {}", id);
        typeServiceRepository.delete(id);
    }
}
