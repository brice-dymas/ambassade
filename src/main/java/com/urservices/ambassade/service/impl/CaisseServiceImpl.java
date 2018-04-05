package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.service.CaisseService;
import com.urservices.ambassade.domain.Caisse;
import com.urservices.ambassade.domain.QCaisse;
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
        
        
        //TO DO Reference doit être string
        //retirer typeID, numeroId, montant,num,monnaie,paiement
        
        QCaisse caisse = QCaisse.caisse;
        Boolean added = false;
        BooleanExpression predicate = null;
        
        if(nom!=null && !nom.isEmpty()){
            predicate = caisse.nom.likeIgnoreCase(nom);
        }
        
        if(prenom!=null && !prenom.isEmpty()){
            if(added){
                predicate = predicate.and(caisse.prenom.likeIgnoreCase(prenom));
            }else{
                predicate = caisse.prenom.likeIgnoreCase(prenom);
            }
        }
        
        if(serviceConcerne!=null && !serviceConcerne.isEmpty()){
            if(added){
                predicate = predicate.and(caisse.serviceConcerne.likeIgnoreCase(serviceConcerne));
            }else{
                predicate = caisse.serviceConcerne.likeIgnoreCase(serviceConcerne);
            }
        }
        
        if(telephone!=null && !telephone.isEmpty()){
            if(added){
                predicate = predicate.and(caisse.telephone.likeIgnoreCase(telephone));
            }else{
                predicate = caisse.telephone.likeIgnoreCase(telephone);
            }
        }
        
        if(dateDuJourDeb!=null){
            if(added){
                predicate = predicate.and(caisse.dateDuJour.goe(dateDuJourDeb));
            }else{
                predicate = caisse.dateDuJour.goe(dateDuJourDeb);
            }
        }
        if(dateDuJourFin!=null){
            if(added){
                predicate = predicate.and(caisse.dateDuJour.loe(dateDuJourFin));
            }else{
                predicate = caisse.dateDuJour.loe(dateDuJourFin);
            }
        }
        if(dateRetourDeb!=null){
            if(added){
                predicate = predicate.and(caisse.dateRetour.goe(dateRetourDeb));
            }else{
                predicate = caisse.dateRetour.goe(dateRetourDeb);
            }
        }
        if(dateRetourFin!=null){
            if(added){
                predicate = predicate.and(caisse.dateRetour.loe(dateRetourFin));
            }else{
                predicate = caisse.dateRetour.loe(dateRetourFin);
            }
        }
        
        if(predicate !=null){
            return caisseRepository.findAll(predicate,pageable);
        }else{
            return caisseRepository.findAll(pageable);
        }
    }
}
