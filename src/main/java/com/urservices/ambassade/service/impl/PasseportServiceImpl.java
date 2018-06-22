package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QPasseport;
import com.urservices.ambassade.domain.enumeration.State;
import com.urservices.ambassade.service.PasseportService;
import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.repository.PasseportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;


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
        passeport.setPaysEmetteur("HAITI");
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
    public Page<Passeport> searchAll(String recu, String nom, String prenom, String numeroPasseport, Statut etatCivil,
                                     LocalDate delivreLeDeb, LocalDate delivreLeFin, Pageable pageable) {
        QPasseport passeport = QPasseport.passeport;
        BooleanExpression predicate = null;
        boolean added = false;
        if (nom != null) {
            predicate = passeport.nom.likeIgnoreCase("%" + nom + "%");
            added = true;
        }
        if (recu != null) {
            if (added) {
                predicate = predicate.and(passeport.recu.likeIgnoreCase("%" + recu + "%"));
            } else {
                predicate = passeport.recu.likeIgnoreCase("%" + recu + "%");
                added = true;
            }
        }
        if (prenom != null) {
            if (added) {
                predicate = predicate.and(passeport.prenom.likeIgnoreCase("%" + prenom + "%"));
            } else {
                predicate = passeport.prenom.likeIgnoreCase("%" + prenom + "%");
                added = true;
            }
        }
        if (numeroPasseport != null) {
            if (added) {
                predicate = predicate.and(passeport.numeroPasseport.likeIgnoreCase("%" + numeroPasseport + "%"));
            } else {
                predicate = passeport.numeroPasseport.likeIgnoreCase("%" + numeroPasseport + "%");
                added = true;
            }
        }
        if (etatCivil != null) {
            if (added) {
                predicate = predicate.and(passeport.etatCivil.eq(etatCivil));
            } else {
                predicate = passeport.etatCivil.eq(etatCivil);
                added = true;
            }
        }
        if (delivreLeDeb != null) {
            if (added) {
                if (delivreLeFin != null) {
                    predicate = predicate.and(passeport.delivreLe.between(delivreLeDeb, delivreLeFin));
                } else {
                    predicate = predicate.and(passeport.delivreLe.eq(delivreLeDeb));
                }
            } else {
                if (delivreLeFin != null) {
                    predicate = passeport.delivreLe.between(delivreLeDeb, delivreLeFin);
                } else {
                    predicate = passeport.delivreLe.eq(delivreLeDeb);
                }
                added = true;
            }
        }
        if (delivreLeFin != null && delivreLeDeb==null) {
            if (added) {
                predicate = predicate.and(passeport.delivreLe.loe(delivreLeFin));
            } else {
                predicate = passeport.delivreLe.loe(delivreLeFin);
            }
        }
        return predicate != null ? passeportRepository.findAll(predicate, pageable): passeportRepository.findAll(pageable);
    }

    @Override
    public Page<Passeport> searchAllNouveau(String recu, String nom, String prenom, String numeroPasseport,
                                            Statut etatCivil, LocalDate delivreLeDeb, LocalDate delivreLeFin,
                                            Pageable pageable) {
        System.out.println("Searching Passeport of State=NOUVEAU");
        QPasseport passeport = QPasseport.passeport;
        BooleanExpression predicate = passeport.state.eq(State.NOUVEAU);
        if (nom != null) {
            predicate = predicate.and(passeport.nom.likeIgnoreCase("%" + nom + "%"));
        }
        if (recu != null) {
                predicate = predicate.and(passeport.recu.likeIgnoreCase("%" + recu + "%"));
        }
        if (prenom != null) {
                predicate = predicate.and(passeport.prenom.likeIgnoreCase("%" + prenom + "%"));
        }
        if (numeroPasseport != null) {
                predicate = predicate.and(passeport.numeroPasseport.likeIgnoreCase("%" + numeroPasseport + "%"));
        }
        if (etatCivil != null) {
                predicate = predicate.and(passeport.etatCivil.eq(etatCivil));
        }
        if (delivreLeDeb != null) {
                if (delivreLeFin != null) {
                    predicate = predicate.and(passeport.delivreLe.between(delivreLeDeb, delivreLeFin));
                } else {
                    predicate = predicate.and(passeport.delivreLe.eq(delivreLeDeb));
                }
        }
        if (delivreLeFin != null && delivreLeDeb==null) {
                predicate = predicate.and(passeport.delivreLe.loe(delivreLeFin));
        }
        System.out.println("Predicate = "+ predicate);
        return predicate != null ? passeportRepository.findAll(predicate, pageable) : passeportRepository.findAll(pageable);
    }
}
