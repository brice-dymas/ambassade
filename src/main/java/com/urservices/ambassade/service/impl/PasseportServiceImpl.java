package com.urservices.ambassade.service.impl;

import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.service.PasseportService;
import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.repository.PasseportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Service Implementation for managing Passeport.
 */
@Service
@Transactional
public class PasseportServiceImpl implements PasseportService {

    private final Logger log = LoggerFactory.getLogger(PasseportServiceImpl.class);

    private final PasseportRepository passeportRepository;

    public PasseportServiceImpl(PasseportRepository passeportRepository) {
        this.passeportRepository = passeportRepository;
    }

    /**
     * Save a passeport.
     *
     * @param passeport the entity to save
     * @return the persisted entity
     */
    @Override
    public Passeport save(Passeport passeport) {
        log.debug("Request to save Passeport : {}", passeport);
        return passeportRepository.save(passeport);
    }

    /**
     * Get all the passeports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Passeport> findAll(Pageable pageable) {
        log.debug("Request to get all Passeports");
        return passeportRepository.findAll(pageable);
    }

    /**
     * Get one passeport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Passeport findOne(Long id) {
        log.debug("Request to get Passeport : {}", id);
        return passeportRepository.findOne(id);
    }

    /**
     * Delete the passeport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Passeport : {}", id);
        passeportRepository.delete(id);
    }

    @Override
    public Page<Passeport> searchAll(String nom, String prenom, String numeroPasseport, String neLe,
                                     String lieuNaissance, List<Statut> etatCivils, String adresse, String telephone,
                                     String nif, String paysEmetteur, LocalDate soumisLe, LocalDate delivreLe,
                                     BigDecimal montant, String remarques, LocalDate dateEmission,
                                     LocalDate dateExpiration, String remarquesR, String sms, String sms2,
                                     String documents, Pageable pageable) {
        return passeportRepository.searchAll("%" + nom + "%", "%" + prenom + "%", "%" + numeroPasseport + "%",
            "%" + neLe + "%", "%" + lieuNaissance + "%",  etatCivils, "%" + adresse + "%",
            "%" + telephone + "%", "%" + nif + "%", "%" + paysEmetteur + "%", soumisLe, delivreLe, montant,
            "%" + remarques + "%", dateEmission, dateExpiration, "%" + remarquesR + "%", "%" + sms + "%",
            "%" + sms2 + "%", "%" + documents + "%", pageable);
    }
}
