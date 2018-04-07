package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QRapatriement;
import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.service.RapatriementService;
import com.urservices.ambassade.domain.Rapatriement;
import com.urservices.ambassade.repository.RapatriementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


/**
 * Service Implementation for managing Rapatriement.
 */
@Service
@Transactional
public class RapatriementServiceImpl implements RapatriementService {

    private final Logger log = LoggerFactory.getLogger(RapatriementServiceImpl.class);

    private final RapatriementRepository rapatriementRepository;

    public RapatriementServiceImpl(RapatriementRepository rapatriementRepository) {
        this.rapatriementRepository = rapatriementRepository;
    }

    /**
     * Save a rapatriement.
     *
     * @param rapatriement the entity to save
     * @return the persisted entity
     */
    @Override
    public Rapatriement save(Rapatriement rapatriement) {
        log.debug("Request to save Rapatriement : {}", rapatriement);
        return rapatriementRepository.save(rapatriement);
    }

    /**
     * Get all the rapatriements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Rapatriement> findAll(Pageable pageable) {
        log.debug("Request to get all Rapatriements");
        return rapatriementRepository.findAll(pageable);
    }

    /**
     * Get one rapatriement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Rapatriement findOne(Long id) {
        log.debug("Request to get Rapatriement : {}", id);
        return rapatriementRepository.findOne(id);
    }

    /**
     * Delete the rapatriement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rapatriement : {}", id);
        rapatriementRepository.delete(id);
    }

    /**
     * searchAll the rapatriement with all parameters.
     *
     * @param reference
     * @param numeroDossier
     * @param nom
     * @param prenom
     * @param dateNaissanceDeb
     * @param dateNaissanceFin
     * @param documentID
     * @param sexe
     * @param motif
     * @param dateRapatriementDeb
     * @param dateRapatriementFin
     * @param frontiere
     * @param pageable
     * @return 
     */
    @Override
    public Page<Rapatriement> searchAll(Integer reference, String numeroDossier, String nom, String prenom,
                                        LocalDate dateNaissanceDeb, LocalDate dateNaissanceFin, String documentID,
                                        Sexe sexe, String motif, LocalDate dateRapatriementDeb, LocalDate dateRapatriementFin,
                                        String frontiere, Pageable pageable) {
        
        //To DO
        //Mettre reference String
        
        QRapatriement rapatriement = QRapatriement.rapatriement;
        Boolean added = false;
        BooleanExpression predicate = null;
        
        if(nom!=null && !nom.isEmpty()){
            predicate = rapatriement.nom.likeIgnoreCase(nom);
        }
        if(prenom!=null && !prenom.isEmpty()){
            if(added){
                predicate = predicate.and(rapatriement.prenom.likeIgnoreCase(prenom));
            }else{
                predicate = rapatriement.prenom.likeIgnoreCase(prenom);
            }
        }
        if(sexe!=null){
            if(added){
                predicate = predicate.and(rapatriement.sexe.eq(sexe));
            }else{
                predicate = rapatriement.sexe.eq(sexe);
            }
        }
        if(numeroDossier!=null && !numeroDossier.isEmpty()){
            if(added){
                predicate = predicate.and(rapatriement.numeroDossier.likeIgnoreCase(numeroDossier));
            }else{
                predicate = rapatriement.numeroDossier.likeIgnoreCase(numeroDossier);
            }
        }
        if(documentID!=null && !documentID.isEmpty()){
            if(added){
                predicate = predicate.and(rapatriement.documentID.likeIgnoreCase(documentID));
            }else{
                predicate = rapatriement.documentID.likeIgnoreCase(documentID);
            }
        }
        if(motif!=null && !motif.isEmpty()){
            if(added){
                predicate = predicate.and(rapatriement.motif.likeIgnoreCase(motif));
            }else{
                predicate = rapatriement.motif.likeIgnoreCase(motif);
            }
        }
        if(frontiere!=null && !frontiere.isEmpty()){
            if(added){
                predicate = predicate.and(rapatriement.frontiere.likeIgnoreCase(frontiere));
            }else{
                predicate = rapatriement.frontiere.likeIgnoreCase(frontiere);
            }
        }
        if(dateNaissanceDeb!=null){
            if(added){
                predicate = predicate.and(rapatriement.dateNaissance.goe(dateNaissanceDeb));
            }else{
                predicate = rapatriement.dateNaissance.goe(dateNaissanceDeb);
            }
        }
        if(dateNaissanceFin!=null){
            if(added){
                predicate = predicate.and(rapatriement.dateNaissance.loe(dateNaissanceFin));
            }else{
                predicate = rapatriement.dateNaissance.loe(dateNaissanceFin);
            }
        }
        if(dateRapatriementDeb!=null){
            if(added){
                predicate = predicate.and(rapatriement.dateRapatriement.goe(dateRapatriementDeb));
            }else{
                predicate = rapatriement.dateRapatriement.goe(dateRapatriementDeb);
            }
        }
        if(dateRapatriementFin!=null){
            if(added){
                predicate = predicate.and(rapatriement.dateRapatriement.loe(dateRapatriementFin));
            }else{
                predicate = rapatriement.dateRapatriement.loe(dateRapatriementFin);
            }
        }
        
        if(predicate !=null){
            return rapatriementRepository.findAll(predicate,pageable);
        }else{
            return rapatriementRepository.findAll(pageable);
        }
    }
}
