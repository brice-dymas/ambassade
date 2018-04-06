package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QLivre;
import com.urservices.ambassade.service.LivreService;
import com.urservices.ambassade.domain.Livre;
import com.urservices.ambassade.repository.LivreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Livre.
 */
@Service
@Transactional
public class LivreServiceImpl implements LivreService {

    private final Logger log = LoggerFactory.getLogger(LivreServiceImpl.class);

    private final LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    /**
     * Save a livre.
     *
     * @param livre the entity to save
     * @return the persisted entity
     */
    @Override
    public Livre save(Livre livre) {
        log.debug("Request to save Livre : {}", livre);
        return livreRepository.save(livre);
    }

    /**
     * Get all the livres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Livre> findAll(Pageable pageable) {
        log.debug("Request to get all Livres");
        return livreRepository.findAll(pageable);
    }

    /**
     * Get one livre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Livre findOne(Long id) {
        log.debug("Request to get Livre : {}", id);
        return livreRepository.findOne(id);
    }

    /**
     * Delete the livre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Livre : {}", id);
        livreRepository.delete(id);
    }

    @Override
    public Page<Livre> searchAll(String codeISBN, String auteur, String titre, String edition, String etagere, Integer annee,
                          String categorie, String resume, Long quantite, String disponible, String page,
                          String consultation, String origine, String sousTitre, String collection, String impression,
                          String format, String index, String bibliographie, String lieuEdition, String lieuImpression,
                          String illustration, String observation, String prenom, String statistique, String glossaire,Pageable pageable){
//        if (quantite==null && annee==null){
//            return livreRepository.search("%"+ codeISBN+"%",auteur+"%","%"+titre+"%","%"+edition+"%",
//                "%"+etagere+"%","%"+categorie+"%","%"+resume+"%","%"+disponible+"%","%"+page+"%",
//                "%"+ consultation+"%","%"+origine+"%","%"+sousTitre+"%","%"+collection+"%",
//                "%"+impression+"%","%"+format+"%","%"+index+"%","%"+bibliographie+"%",
//                "%"+lieuEdition+"%","%"+ lieuImpression+"%","%"+illustration+"%",
//                "%"+observation+"%","%"+prenom+"%","%"+statistique+"%","%"+glossaire+"%",pageable);
//        }else {
//            if (quantite==null && annee!=null){
//                return livreRepository.searchWithAnnee(annee,"%"+ codeISBN+"%",auteur+"%","%"+titre+"%","%"+edition+"%",
//                    "%"+etagere+"%","%"+categorie+"%","%"+resume+"%","%"+disponible+"%","%"+page+"%",
//                    "%"+ consultation+"%","%"+origine+"%","%"+sousTitre+"%","%"+collection+"%",
//                    "%"+impression+"%","%"+format+"%","%"+index+"%","%"+bibliographie+"%",
//                    "%"+lieuEdition+"%","%"+ lieuImpression+"%","%"+illustration+"%",
//                    "%"+observation+"%","%"+prenom+"%","%"+statistique+"%","%"+glossaire+"%",pageable);
//            }
//            if (quantite!=null && annee==null){
//                return livreRepository.searchWithQuantite(quantite,"%"+ codeISBN+"%",auteur+"%","%"+titre+"%","%"+edition+"%",
//                    "%"+etagere+"%","%"+categorie+"%","%"+resume+"%","%"+disponible+"%","%"+page+"%",
//                    "%"+ consultation+"%","%"+origine+"%","%"+sousTitre+"%","%"+collection+"%",
//                    "%"+impression+"%","%"+format+"%","%"+index+"%","%"+bibliographie+"%",
//                    "%"+lieuEdition+"%","%"+ lieuImpression+"%","%"+illustration+"%",
//                    "%"+observation+"%","%"+prenom+"%","%"+statistique+"%","%"+glossaire+"%",pageable);
//            }else {
                return livreRepository.searchAll(quantite, annee,"%"+ codeISBN+"%",auteur+"%","%"+titre+"%","%"+edition+"%",
                    "%"+etagere+"%","%"+categorie+"%","%"+resume+"%","%"+disponible+"%","%"+page+"%",
                    "%"+ consultation+"%","%"+origine+"%","%"+sousTitre+"%","%"+collection+"%",
                    "%"+impression+"%","%"+format+"%","%"+index+"%","%"+bibliographie+"%",
                    "%"+lieuEdition+"%","%"+ lieuImpression+"%","%"+illustration+"%",
                    "%"+observation+"%","%"+prenom+"%","%"+statistique+"%","%"+glossaire+"%",pageable);
//            }
//        }
    }

    @Override
    public Page<Livre> findAll(String codeISBN, String auteur, String titre, String edition, String etagere,
                               Integer annee, String categorie, Long quantite, String disponible, String origine,
                               String collection, String format, String lieuEdition, String lieuImpression,
                               String prenom, Pageable pageable) {
        QLivre livre = QLivre.livre;
        BooleanExpression predicate = null;
        boolean added =  false;
        if (codeISBN != null){
            predicate = livre.codeISBN.likeIgnoreCase("%"+codeISBN+"%");
            added = true;
        }
        if (auteur != null) {
            if (added){
                predicate = predicate.and(livre.auteur.likeIgnoreCase("%"+auteur+"%"));
            }else {
                predicate = livre.auteur.likeIgnoreCase("%"+auteur+"%");
                added = true;
            }
        }
        if (titre != null) {
            if (added){
                predicate = predicate.and(livre.titre.likeIgnoreCase("%"+titre+"%"));
            }else {
                predicate = livre.titre.likeIgnoreCase("%"+titre+"%");
                added = true;
            }
        }
        if (edition != null) {
            if (added){
                predicate = predicate.and(livre.edition.likeIgnoreCase("%"+edition+"%"));
            }else {
                predicate = livre.edition.likeIgnoreCase("%"+edition+"%");
                added = true;
            }
        }
        if (etagere != null) {
            if (added){
                predicate = predicate.and(livre.etagere.likeIgnoreCase("%"+etagere+"%"));
            }else {
                predicate = livre.etagere.likeIgnoreCase("%"+etagere+"%");
                added = true;
            }
        }
        if (annee != null) {
            if (added){
                predicate = predicate.and(livre.annee.eq(annee));
            }else {
                predicate = livre.annee.eq(annee);
                added = true;
            }
        }
        if (categorie != null) {
            if (added){
                predicate = predicate.and(livre.categorie.likeIgnoreCase("%"+categorie+"%"));
            }else {
                predicate = livre.categorie.likeIgnoreCase("%"+categorie+"%");
                added = true;
            }
        }
        if (quantite != null) {
            if (added){
                predicate = predicate.and(livre.quantite.eq(quantite));
            }else {
                predicate = livre.quantite.eq(quantite);
                added = true;
            }
        }
        if (disponible != null) {
            if (added){
                predicate = predicate.and(livre.disponible.likeIgnoreCase("%"+disponible+"%"));
            }else {
                predicate = livre.disponible.likeIgnoreCase("%"+disponible+"%");
                added = true;
            }
        }
        if (origine != null) {
            if (added){
                predicate = predicate.and(livre.origine.likeIgnoreCase("%"+origine+"%"));
            }else {
                predicate = livre.origine.likeIgnoreCase("%"+origine+"%");
                added = true;
            }
        }
        if (collection != null) {
            if (added){
                predicate = predicate.and(livre.collection.likeIgnoreCase("%"+collection+"%"));
            }else {
                predicate = livre.collection.likeIgnoreCase("%"+collection+"%");
                added = true;
            }
        }
        if (format != null) {
            if (added){
                predicate = predicate.and(livre.format.likeIgnoreCase("%"+format+"%"));
            }else {
                predicate = livre.format.likeIgnoreCase("%"+format+"%");
                added = true;
            }
        }
        if (lieuEdition != null) {
            if (added){
                predicate = predicate.and(livre.lieuEdition.likeIgnoreCase("%"+lieuEdition+"%"));
            }else {
                predicate = livre.lieuEdition.likeIgnoreCase("%"+lieuEdition+"%");
                added = true;
            }
        }
        if (lieuImpression != null) {
            if (added){
                predicate = predicate.and(livre.lieuImpression.likeIgnoreCase("%"+lieuImpression+"%"));
            }else {
                predicate = livre.lieuImpression.likeIgnoreCase("%"+lieuImpression+"%");
                added = true;
            }
        }
        if (prenom != null) {
            if (added){
                predicate = predicate.and(livre.prenom.likeIgnoreCase("%"+prenom+"%"));
            }else {
                predicate = livre.prenom.likeIgnoreCase("%"+prenom+"%");
                added = true;
            }
        }
       if( predicate != null){
            return livreRepository.findAll(predicate,pageable);
       }else {
           return livreRepository.findAll(pageable);
       }
    }
}
