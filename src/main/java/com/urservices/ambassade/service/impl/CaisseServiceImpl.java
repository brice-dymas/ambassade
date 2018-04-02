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
import java.time.LocalDate;


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

    @Override
    public Page<Caisse> search(Long reference, LocalDate dateDuJourDeb, LocalDate dateDuJourFin, String nom, String prenom,
                               String typeID, String numeroID, String serviceConcerne, String monnaie, BigDecimal montant,
                               LocalDate dateRetourDeb, LocalDate dateRetourFin, String telephone, Integer num,
                               String paiement, Pageable pageable) {
        return caisseRepository.search(reference,dateDuJourDeb,dateDuJourFin,"%"+nom,"%"+prenom+"%",
            "%"+typeID+"%","%"+numeroID+"%","%"+serviceConcerne+"%","%"+monnaie+"%",
            montant,dateRetourDeb,dateRetourFin,"%"+telephone+"%",num,"%"+paiement+"%",pageable);
    }
}
