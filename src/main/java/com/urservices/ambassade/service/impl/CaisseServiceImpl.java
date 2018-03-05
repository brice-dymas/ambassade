package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.service.CaisseService;
import com.urservices.ambassade.domain.Caisse;
import com.urservices.ambassade.repository.CaisseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


/**
 * Service Implementation for managing Caisse.
 */
@Service
@Transactional
public class CaisseServiceImpl implements CaisseService {

    private final Logger log = LoggerFactory.getLogger(CaisseServiceImpl.class);

    private final CaisseRepository caisseRepository;

    public CaisseServiceImpl(CaisseRepository caisseRepository) {
        this.caisseRepository = caisseRepository;
    }

    /**
     * Save a caisse.
     *
     * @param caisse the entity to save
     * @return the persisted entity
     */
    @Override
    public Caisse save(Caisse caisse) {
        log.debug("Request to save Caisse : {}", caisse);
        return caisseRepository.save(caisse);
    }

    /**
     * Get all the caisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Caisse> findAll(Pageable pageable) {
        log.debug("Request to get all Caisses");
        return caisseRepository.findAll(pageable);
    }

    /**
     * Get one caisse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Caisse findOne(Long id) {
        log.debug("Request to get Caisse : {}", id);
        return caisseRepository.findOne(id);
    }

    /**
     * Delete the caisse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Caisse : {}", id);
        caisseRepository.delete(id);
    }

    /**
     * Search Caisse using parameters
     *
     * @param dateDuJour
     * @param reference
     * @param montant
     * @param num
     * @param dateRetour
     * @param monnaie
     * @param nom
     * @param prenom
     * @param typeID
     * @param serviceConcerne
     * @param telephone
     * @param paiement
     * @param numero
     * @param page
     * @param size
     */
    @Override
    public Page<Caisse> searchAll(ZonedDateTime dateDuJour, Long reference, BigDecimal montant, Integer num,
                                  ZonedDateTime dateRetour, String monnaie, String nom, String prenom, String typeID,
                                  String serviceConcerne, String telephone, String paiement, String numero,
                                  Pageable pageable) {
    	
    	System.out.println("Date du jour "+dateDuJour.toString());
    	
    	Page<Caisse> pageCaisse = caisseRepository.searchByDateDuJour(dateDuJour, pageable);
    	
    	return pageCaisse;
        /*return caisseRepository.searchAll(dateDuJour,reference,montant,num,dateRetour,"%"+monnaie+"%","%"+nom+"%",
            "%"+prenom+"%","%"+typeID+"%","%"+serviceConcerne+"%","%"+telephone+"%",
            "%"+paiement+"%","%"+numero+"%",pageable);*/
    }
}
