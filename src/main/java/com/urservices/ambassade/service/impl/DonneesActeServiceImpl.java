package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QDonneesActe;
import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.service.DonneesActeService;
import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.repository.DonneesActeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


/**
 * Service Implementation for managing DonneesActe.
 */
@Service
@Transactional
public class DonneesActeServiceImpl implements DonneesActeService {

    private final Logger log = LoggerFactory.getLogger(DonneesActeServiceImpl.class);

    private final DonneesActeRepository donneesActeRepository;

    public DonneesActeServiceImpl(DonneesActeRepository donneesActeRepository) {
        this.donneesActeRepository = donneesActeRepository;
    }

    /**
     * Save a donneesActe.
     *
     * @param donneesActe the entity to save
     * @return the persisted entity
     */
    @Override
    public DonneesActe save(DonneesActe donneesActe) {
        log.debug("Request to save DonneesActe : {}", donneesActe);
        return donneesActeRepository.save(donneesActe);
    }

    /**
     * Get all the donneesActes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DonneesActe> findAll(Pageable pageable) {
        log.debug("Request to get all DonneesActes");
        return donneesActeRepository.findAll(pageable);
    }

    /**
     * Get one donneesActe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DonneesActe findOne(Long id) {
        log.debug("Request to get DonneesActe : {}", id);
        return donneesActeRepository.findOne(id);
    }

    /**
     * Delete the donneesActe by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DonneesActe : {}", id);
        donneesActeRepository.delete(id);
    }

    @Override
    public Page<DonneesActe> searchAll(String reference, String registreSpecialRD, String nomEnfant, LocalDate dateDuJourChiffreDeb,
                                       LocalDate dateDuJourChiffreFin, String registre, List<Statut> statut, String numero,
                                       String nomPere, String prenomPere, String nomMere, String prenomMere,
                                       LocalDate dateNaissanceChiffreDeb, LocalDate dateNaissanceChiffreFin, Integer annee,
                                       List<Sexe> sexe, String villeNaissance, String adressePere, String adresseMere,
                                       String temoins1, String temoins2, String idPere, String idMere, String juridiction,
                                       String livre, String notes, String feuille, String acte, Pageable pageable) {
        return donneesActeRepository.searchAll("%"+reference+"%","%"+registreSpecialRD+"%",
            "%"+nomEnfant+"%",dateDuJourChiffreDeb,dateDuJourChiffreFin,"%"+ registre+"%",statut,
            "%"+numero+"%","%"+nomPere+"%","%"+prenomPere+"%","%"+nomMere+"%",
            "%"+prenomMere+"%",dateNaissanceChiffreDeb,dateNaissanceChiffreFin,annee,sexe,
            "%"+villeNaissance+"%","%"+adressePere+"%","%"+adresseMere+"%",
            "%"+temoins1+"%","%"+temoins2+"%","%"+idPere+"%","%"+idMere+"%",
            "%"+juridiction+"%","%"+livre+"%","%"+notes+"%","%"+feuille+"%","%"+acte+"%",
            pageable);
    }

    @Override
    public Page<DonneesActe> findAll(String reference, String nomEnfant, LocalDate dateDuJourChiffre,
                                     String registre, Statut statut, String nomPere, String prenomPere,
                                     String nomMere, String prenomMere, LocalDate dateNaissanceChiffre, Integer annee,
                                     Sexe sexe, String villeNaissance, String adressePere, String adresseMere,
                                     String juridiction, String livre, String acte, Pageable pageable) {
        QDonneesActe donneesActe = QDonneesActe.donneesActe;
        BooleanExpression predicate = null;
        boolean added = true;
        if (reference != null){
            predicate = donneesActe.reference.likeIgnoreCase("%"+reference+"%");
            added = true;
        }
        if (nomEnfant  !=  null){
            if(added){
                predicate = predicate.and(donneesActe.nomEnfant.likeIgnoreCase("%"+nomEnfant+"%"));
            }else {
                predicate = donneesActe.nomEnfant.likeIgnoreCase("%"+nomEnfant+"%");
                added =  true;
            }
        }
        if (dateDuJourChiffre != null){
            if(added){
                predicate = predicate.and(donneesActe.dateDuJourChiffre.eq(dateDuJourChiffre));
            }else {
                predicate = donneesActe.dateDuJourChiffre.eq(dateDuJourChiffre);
                added =  true;
            }
        }
        if (registre != null){
            if(added){
                predicate = predicate.and(donneesActe.registre.likeIgnoreCase("%"+registre+"%"));
            }else {
                predicate = donneesActe.registre.likeIgnoreCase("%"+registre+"%");
                added =  true;
            }
        }
        if (statut != null){
            if(added){
                predicate = predicate.and(donneesActe.statut.eq(statut));
            }else {
                predicate = donneesActe.statut.eq(statut);
                added =  true;
            }
        }
        if (nomPere != null){
            if(added){
                predicate = predicate.and(donneesActe.nomPere.likeIgnoreCase("%"+nomPere+"%"));
            }else {
                predicate = donneesActe.nomPere.likeIgnoreCase("%"+nomPere+"%");
                added =  true;
            }
        }
        if (prenomPere != null){
            if(added){
                predicate = predicate.and(donneesActe.prenomPere.likeIgnoreCase("%"+prenomPere+"%"));
            }else {
                predicate = donneesActe.prenomPere.likeIgnoreCase("%"+prenomPere+"%");
                added =  true;
            }
        }
        if (nomMere != null){
            if(added){
                predicate = predicate.and(donneesActe.nomMere.likeIgnoreCase("%"+nomMere+"%"));
            }else {
                predicate = donneesActe.nomMere.likeIgnoreCase("%"+nomMere+"%");
                added =  true;
            }
        }
        if (prenomMere != null){
            if(added){
                predicate = predicate.and(donneesActe.prenomMere.likeIgnoreCase("%"+prenomMere+"%"));
            }else {
                predicate = donneesActe.prenomMere.likeIgnoreCase("%"+prenomMere+"%");
                added =  true;
            }
        }
        if (dateNaissanceChiffre != null){
            if(added){
                predicate = predicate.and(donneesActe.dateNaissanceChiffre.eq(dateNaissanceChiffre));
            }else {
                predicate = donneesActe.dateNaissanceChiffre.eq(dateNaissanceChiffre);
                added =  true;
            }
        }
        if (sexe != null){
            if(added){
                predicate = predicate.and(donneesActe.sexe.eq(sexe));
            }else {
                predicate = donneesActe.sexe.eq(sexe);
                added =  true;
            }
        }
        if (annee != null){
            if(added){
                predicate = predicate.and(donneesActe.annee.eq(annee));
            }else {
                predicate = donneesActe.annee.eq(annee);
                added =  true;
            }
        }
        if (villeNaissance != null){
            if(added){
                predicate = predicate.and(donneesActe.villeNaissance.likeIgnoreCase("%"+villeNaissance+"%"));
            }else {
                predicate = donneesActe.villeNaissance.likeIgnoreCase("%"+villeNaissance+"%");
                added =  true;
            }
        }
        if (adressePere != null){
            if(added){
                predicate = predicate.and(donneesActe.adressePere.likeIgnoreCase("%"+adressePere+"%"));
            }else {
                predicate = donneesActe.adressePere.likeIgnoreCase("%"+adressePere+"%");
                added =  true;
            }
        }
        if (adresseMere != null){
            if(added){
                predicate = predicate.and(donneesActe.adresseMere.likeIgnoreCase("%"+adresseMere+"%"));
            }else {
                predicate = donneesActe.adresseMere.likeIgnoreCase("%"+adresseMere+"%");
                added =  true;
            }
        }
        if (juridiction != null){
            if(added){
                predicate = predicate.and(donneesActe.juridiction.likeIgnoreCase("%"+juridiction+"%"));
            }else {
                predicate = donneesActe.juridiction.likeIgnoreCase("%"+juridiction+"%");
                added =  true;
            }
        }
        if (livre != null){
            if(added){
                predicate = predicate.and(donneesActe.livre.likeIgnoreCase("%"+livre+"%"));
            }else {
                predicate = donneesActe.livre.likeIgnoreCase("%"+livre+"%");
                added =  true;
            }
        }
        if (acte != null){
            if(added){
                predicate = predicate.and(donneesActe.acte.likeIgnoreCase("%"+acte+"%"));
            }else {
                predicate = donneesActe.acte.likeIgnoreCase("%"+acte+"%");
            }
        }

        if (predicate  !=  null){
            return donneesActeRepository.findAll(predicate, pageable);
        }else {
            return donneesActeRepository.findAll(pageable);
        }
    }
}
