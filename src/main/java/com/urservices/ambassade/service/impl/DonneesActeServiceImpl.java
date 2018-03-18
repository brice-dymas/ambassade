package com.urservices.ambassade.service.impl;

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
}
