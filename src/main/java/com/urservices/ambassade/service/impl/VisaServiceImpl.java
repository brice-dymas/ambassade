package com.urservices.ambassade.service.impl;

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
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Visa> findAll(Pageable pageable) {
        log.debug("Request to get all Visas");
        return visaRepository.findAll(pageable);
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
     * @param cedula
     * @param numeroVisa
     * @param nombreEntree
     * @param type
     * @param categorie
     * @param taxes
     * @param adresse
     * @param remarques
     * @param pageable  the pagination information  @return the list of entities
     */
    @Override
    public Page<Visa> searchAll(String nom, String prenom, String nationalite, String numeroPasseport, String cedula,
                                Long numeroVisa, LocalDate dateEmissionDeb, LocalDate dateEmissionFin, LocalDate dateExpirationDeb,
                                LocalDate dateExpirationFin,  Integer validePour, String nombreEntree, String type, String categorie,
                                Integer taxes, String adresse, String remarques, Pageable pageable) {
        return visaRepository.searchAll("%"+nom+"%","%"+prenom+"%","%"+nationalite+"%",
            "%"+numeroPasseport+"%","%"+cedula+"%",numeroVisa,dateEmissionDeb,dateEmissionFin,
            dateExpirationDeb, dateExpirationFin, validePour,"%"+nombreEntree+"%","%"+type+"%","%"+categorie+"%",taxes,
            "%"+adresse+"%","%"+remarques+"%",pageable);
    }
}
