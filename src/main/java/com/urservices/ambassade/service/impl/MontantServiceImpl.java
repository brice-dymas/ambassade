package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.service.MontantService;
import com.urservices.ambassade.domain.Montant;
import com.urservices.ambassade.domain.QMontant;
import com.urservices.ambassade.repository.MontantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Montant.
 */
@Service
@Transactional
public class MontantServiceImpl implements MontantService {

    private final Logger log = LoggerFactory.getLogger(MontantServiceImpl.class);

    private final MontantRepository montantRepository;

    public MontantServiceImpl(MontantRepository montantRepository) {
        this.montantRepository = montantRepository;
    }

    /**
     * Save a montant.
     *
     * @param montant the entity to save
     * @return the persisted entity
     */
    @Override
    public Montant save(Montant montant) {
        log.debug("Request to save Montant : {}", montant);
        return montantRepository.save(montant);
    }

    /**
     * Get all the montants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Montant> findAll(Pageable pageable) {
        log.debug("Request to get all Montants");
        return montantRepository.findAll(pageable);
    }

    /**
     * Get one montant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Montant findOne(Long id) {
        log.debug("Request to get Montant : {}", id);
        return montantRepository.findOne(id);
    }

    /**
     * Delete the montant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Montant : {}", id);
        montantRepository.delete(id);
    }

    @Override
    public Page<Montant> findByMonnaieAndProduitAndMontant(String monnaie, String produit, Long montant, Pageable pageable) {
        log.debug("Request to get Monnaie with monnaie: {}, produit: {}, and montant: {}", monnaie, produit, montant);
        QMontant montant1 = QMontant.montant1;
        Boolean added = false;
        BooleanExpression predicate = null;

        if(monnaie!=null && !monnaie.isEmpty()){
            predicate = montant1.monnaie.likeIgnoreCase(monnaie);
        }

        if(produit!=null && !produit.isEmpty()){
            if(added){
                predicate = predicate.and(montant1.produit.likeIgnoreCase("%"+produit+"%"));
            }else{
                predicate = montant1.produit.likeIgnoreCase("%"+produit+"%");
                added = true;
            }
        }

        if(montant!=null){
            if(added){
                predicate = predicate.and(montant1.montant.goe(montant));
            }else{
                predicate = montant1.montant.goe(montant);
            }
        }

        if(predicate !=null){
            return montantRepository.findAll(predicate,pageable);
        }else{
            return montantRepository.findAll(pageable);
        }
    }
}
