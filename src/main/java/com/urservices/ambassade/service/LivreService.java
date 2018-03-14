package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Livre.
 */
public interface LivreService {

    /**
     * Save a livre.
     *
     * @param livre the entity to save
     * @return the persisted entity
     */
    Livre save(Livre livre);

    /**
     * Get all the livres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Livre> findAll(Pageable pageable);

    /**
     * Get the "id" livre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Livre findOne(Long id);

    /**
     * Delete the "id" livre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<Livre> searchAll(String codeISBN, String auteur, String titre, String edition, String etagere, Integer annee,
                          String categorie, String resume, Long quantite, String disponible, String page,
                          String consultation, String origine, String sousTitre, String collection, String impression,
                          String format, String index, String bibliographie, String lieuEdition, String lieuImpression,
                          String illustration, String observation, String prenom, String statistique, String glossaire,Pageable pageable);
}
