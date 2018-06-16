package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QPaiement;
import com.urservices.ambassade.service.PaiementService;
import com.urservices.ambassade.domain.Paiement;
import com.urservices.ambassade.repository.PaiementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


/**
 * Service Implementation for managing Paiement.
 */
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

    private final Logger log = LoggerFactory.getLogger(PaiementServiceImpl.class);

    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    /**
     * Save a paiement.
     *
     * @param paiement the entity to save
     * @return the persisted entity
     */
    @Override
    public Paiement save(Paiement paiement) {
        log.debug("Request to save Paiement : {}", paiement);
        return paiementRepository.save(paiement);
    }

    /**
     * Get all the paiements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Paiement> findAll(Pageable pageable) {
        log.debug("Request to get all Paiements");
        return paiementRepository.findAll(pageable);
    }

    /**
     * Get all the paiements using params.
     *
     * @param datePaiement
     * @param visa
     * @param uniteOrganisationelle
     * @param typeService
     * @param pageable     the pagination information  @return the list of entities
     */
    @Override
    public Page<Paiement> findAll(String numeroPaiement, LocalDate datePaiement, LocalDate datePaiementFin,
                                  Long visa, Long uniteOrganisationelle, Long typeService, Pageable pageable) {
        QPaiement paiement = QPaiement.paiement;
        BooleanExpression predicate = null;
        boolean added = false;
        if (datePaiement != null){
            if (datePaiementFin != null){
                predicate = paiement.datePaiement.between(datePaiement, datePaiementFin);
            }else {
                predicate = paiement.datePaiement.eq(datePaiement);
            }
            added= true;
        }
        if (datePaiementFin != null && datePaiement == null){
            if (added){
                predicate = predicate.and(paiement.datePaiement.eq(datePaiementFin));
            }else {
                predicate = paiement.datePaiement.eq(datePaiementFin);
            }
            added= true;
        }
        if (numeroPaiement != null) {
            if (added) {
                predicate = predicate.and(paiement.numeroPaiement.likeIgnoreCase("%"+numeroPaiement+"%"));
            } else {
                predicate = paiement.numeroPaiement.likeIgnoreCase("%"+numeroPaiement+"%");
                added = true;
            }
        }
        if (visa != null) {
            if (added) {
                predicate = predicate.and(paiement.visa.id.eq(visa));
            } else {
                predicate = paiement.visa.id.eq(visa);
                added = true;
            }
        }
        if (uniteOrganisationelle != null) {
            if (added) {
                predicate = predicate.and(paiement.uniteOrganisationelle.id.eq(uniteOrganisationelle));
            } else {
                predicate = paiement.uniteOrganisationelle.id.eq(uniteOrganisationelle);
                added = true;
            }
        }
        if (typeService != null) {
            if (added) {
                predicate = predicate.and(paiement.typeService.id.eq(typeService));
            } else {
                predicate = paiement.typeService.id.eq(typeService);
            }
        }
        return predicate != null? paiementRepository.findAll(predicate,pageable): paiementRepository.findAll(pageable);
    }

    /**
     * Get one paiement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Paiement findOne(Long id) {
        log.debug("Request to get Paiement : {}", id);
        return paiementRepository.findOne(id);
    }

    /**
     * Delete the paiement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paiement : {}", id);
        paiementRepository.delete(id);
    }
}
