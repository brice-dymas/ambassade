package com.urservices.ambassade.service;

import com.urservices.ambassade.domain.Caisse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Service Interface for managing Caisse.
 */
public interface CaisseService {

    /**
     * Save a caisse.
     *
     * @param caisse the entity to save
     * @return the persisted entity
     */
    Caisse save(Caisse caisse);

    /**
     * Get all the caisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Caisse> findAll(Pageable pageable);

    /**
     * Get the "id" caisse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Caisse findOne(Long id);

    /**
     * Delete the "id" caisse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<Caisse> search(Long reference, LocalDate dateDuJourDeb, LocalDate dateDuJourFin,String nom, String prenom, String typeID,
                        String numeroID, String serviceConcerne, String monnaie, BigDecimal montant,
                        LocalDate dateRetourDeb,LocalDate dateRetourFin, String telephone, Integer num, String paiement,
                        Pageable pageable);
}
