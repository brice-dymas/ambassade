package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QVisa;
import com.urservices.ambassade.service.VisaService;
import com.urservices.ambassade.domain.Visa;
import com.urservices.ambassade.repository.VisaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


/**
 * Service Implementation for managing Visa.
 */
@Service
@Transactional
public class VisaServiceImpl implements VisaService {

    private final Logger log = LoggerFactory.getLogger(VisaServiceImpl.class);

    private final VisaRepository visaRepository;

    public VisaServiceImpl(VisaRepository visaRepository) {
        this.visaRepository = visaRepository;
    }

    /**
     * Save a visa.
     *
     * @param visa the entity to save
     * @return the persisted entity
     */
    @Override
    public Visa save(Visa visa) {
        log.debug("Request to save Visa : {}", visa);
        return visaRepository.save(visa);
    }

    /**
     * Get all the visas.
     *
     *  @param nom
     * @param prenom
     * @param numeroPasseport
     * @param numeroVisa
     * @param typeService
     * @param categorie
     * @param dateEmissionDeb
     * @param dateEmissionFin
     * @param pageable the pagination information  @return the list of entities
     * */
    @Override
    @Transactional(readOnly = true)
    public Page<Visa> findAll(String nom, String prenom, String numeroPasseport, Long numeroVisa, Long typeService,
                              Long categorie, LocalDate dateEmissionDeb, LocalDate dateEmissionFin, Pageable pageable) {
        log.debug("Request to get all Visas");
        QVisa visa = QVisa.visa;
        BooleanExpression predicate = null;
        boolean added = false;

        if (nom != null) {
            predicate = visa.nom.likeIgnoreCase("%" + nom + "%");
            added = true;
        }
        if (prenom != null) {
            if (added) {
                predicate = predicate.and(visa.prenom.likeIgnoreCase("%" + prenom + "%"));
            } else {
                predicate = visa.prenom.likeIgnoreCase("%" + prenom + "%");
                added = true;
            }
        }
        if (numeroVisa != null) {
            if (added) {
                predicate = predicate.and(visa.numeroVisa.eq(numeroVisa));
            } else {
                predicate = visa.numeroVisa.eq(numeroVisa);
                added = true;
            }
        }
        if (numeroPasseport != null) {
            if (added) {
                predicate = predicate.and(visa.numeroPasseport.likeIgnoreCase("%" + numeroPasseport + "%"));
            } else {
                predicate = visa.numeroPasseport.likeIgnoreCase("%" + numeroPasseport + "%");
                added = true;
            }
        }
        if (typeService != null) {
            if (added) {
                predicate = predicate.and(visa.typeService.id.eq(typeService));
            } else {
                predicate = visa.typeService.id.eq(typeService);
                added = true;
            }
        }
        if (categorie != null) {
            if (added) {
                predicate = predicate.and(visa.categorie.id.eq(categorie));
            } else {
                predicate = visa.categorie.id.eq(categorie);
                added = true;
            }
        }
        if (dateEmissionDeb != null) {
            if (added) {
                if (dateEmissionFin != null) {
                    predicate = predicate.and(visa.dateEmission.between(dateEmissionDeb, dateEmissionFin));
                } else {
                    predicate = predicate.and(visa.dateEmission.eq(dateEmissionDeb));
                }
            } else {
                if (dateEmissionFin != null) {
                    predicate = visa.dateEmission.between(dateEmissionDeb, dateEmissionFin);
                } else {
                    predicate = visa.dateEmission.eq(dateEmissionDeb);
                }
                added = true;
            }
        }
        if (dateEmissionFin != null && dateEmissionDeb == null) {
            if (added) {
                    predicate = predicate.and(visa.dateEmission.loe(dateEmissionFin));
                } else {
                    predicate = visa.dateEmission.loe(dateEmissionFin);
                }
            }

        if (predicate != null) {
            return visaRepository.findAll(predicate, pageable);
        } else {
            return visaRepository.findAll(pageable);
        }
    }

    /**
     * Get one visa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Visa findOne(Long id) {
        log.debug("Request to get Visa : {}", id);
        return visaRepository.findOne(id);
    }

    /**
     * Delete the visa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Visa : {}", id);
        visaRepository.delete(id);
    }

    /**
     * Get all the visas using params.
     *
     * @param nom
     * @param prenom
     * @param nationalite
     * @param numeroPasseport
     * @param numeroVisa
     * @param dateEmissionDeb
     * @param dateEmissionFin
     * @param dateExpirationDeb
     * @param dateExpirationFin
     * @param type
     * @param categorie
     * @param pageable  the pagination information  @return the list of entities
     * @return
     */
    @Override
    public Page<Visa> searchAll(String nom, String prenom, String nationalite, String numeroPasseport,Long numeroVisa,
                                LocalDate dateEmissionDeb, LocalDate dateEmissionFin, LocalDate dateExpirationDeb,
                                LocalDate dateExpirationFin,String type, String categorie,String adresse, Pageable pageable) {

        //Travailler numéro visa et retirer validepour et taxes
        QVisa visa = QVisa.visa;
        Boolean added = false;
        BooleanExpression predicate = null;

        if(nom!=null && !nom.isEmpty()){
            predicate = visa.nom.likeIgnoreCase("%"+nom+"%");
        }
        if(prenom!=null && !prenom.isEmpty()){
            if(added){
                predicate = predicate.and(visa.prenom.likeIgnoreCase("%"+prenom+"%"));
            }else{
                predicate = visa.prenom.likeIgnoreCase("%"+prenom+"%");
            }
        }
        if(nationalite!=null && !nationalite.isEmpty()){
            if(added){
                predicate = predicate.and(visa.nationalite.likeIgnoreCase("%"+nationalite+"%"));
            }else{
                predicate = visa.nationalite.likeIgnoreCase("%"+nationalite+"%");
            }
        }
        if(numeroPasseport!=null && !numeroPasseport.isEmpty()){
            if(added){
                predicate = predicate.and(visa.numeroPasseport.likeIgnoreCase("%"+numeroPasseport+"%"));
            }else{
                predicate = visa.numeroPasseport.likeIgnoreCase("%"+numeroPasseport+"%");
            }
        }
//        if(cedula!=null && !cedula.isEmpty()){
//            if(added){
//                predicate = predicate.and(visa.cedula.likeIgnoreCase("%"+cedula));
//            }else{
//                predicate = visa.cedula.likeIgnoreCase("%"+cedula);
//            }
//        }
//        if(nombreEntree!=null && !nombreEntree.isEmpty()){
//            if(added){
//                predicate = predicate.and(visa.nombreEntree.likeIgnoreCase("%"+nombreEntree));
//            }else{
//                predicate = visa.nombreEntree.likeIgnoreCase("%"+nombreEntree);
//            }
//        }
        if(adresse!=null && !adresse.isEmpty()){
            if(added){
                predicate = predicate.and(visa.adresse.likeIgnoreCase("%"+adresse+"%"));
            }else{
                predicate = visa.adresse.likeIgnoreCase("%"+adresse+"%");
            }
        }
//        if(remarques!=null && !remarques.isEmpty()){
//            if(added){
//                predicate = predicate.and(visa.remarques.likeIgnoreCase("%"+remarques));
//            }else{
//                predicate = visa.remarques.likeIgnoreCase("%"+remarques);
//            }
//        }
        if(dateEmissionDeb!=null){
            if(added){
                predicate = predicate.and(visa.dateEmission.goe(dateEmissionDeb));
            }else{
                predicate = visa.dateEmission.goe(dateEmissionDeb);
            }
        }
        if(dateEmissionFin!=null){
            if(added){
                predicate = predicate.and(visa.dateEmission.loe(dateEmissionFin));
            }else{
                predicate = visa.dateEmission.loe(dateEmissionFin);
            }
        }
        if(dateExpirationDeb!=null){
            if(added){
                predicate = predicate.and(visa.dateExpiration.goe(dateExpirationDeb));
            }else{
                predicate = visa.dateExpiration.goe(dateExpirationDeb);
            }
        }
        if(dateExpirationFin!=null){
            if(added){
                predicate = predicate.and(visa.dateExpiration.loe(dateExpirationFin));
            }else{
                predicate = visa.dateExpiration.loe(dateExpirationFin);
            }
        }
        if(predicate !=null){
            return visaRepository.findAll(predicate,pageable);
        }else{
            return visaRepository.findAll(pageable);
        }
    }
}
