package com.urservices.ambassade.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.urservices.ambassade.domain.QProduit;
import com.urservices.ambassade.service.ProduitService;
import com.urservices.ambassade.domain.Produit;
import com.urservices.ambassade.repository.ProduitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Produit.
 */
@Primary
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final Logger log = LoggerFactory.getLogger(ProduitServiceImpl.class);

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Save a produit.
     *
     * @param produit the entity to save
     * @return the persisted entity
     */
    @Override
    public Produit save(Produit produit) {
        log.debug("Request to save Produit : {}", produit);
        return produitRepository.save(produit);
    }

    /**
     * Get all the produits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Produit> findAll(Pageable pageable) {
        log.debug("Request to get all Produits");
        return produitRepository.findAll(pageable);
    }

    /**
     * Get one produit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Produit findOne(Long id) {
        log.debug("Request to get Produit : {}", id);
        return produitRepository.findOne(id);
    }

    /**
     * Delete the produit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produit : {}", id);
        produitRepository.delete(id);
    }

    @Override
    public Page<Produit> searchAll(String monnaie, String nomProduit, Long montant, Pageable pageable) {
        return produitRepository.findByMonnaieAndProduitAndMontant("%" + monnaie + "%", "%" +
            nomProduit + "%", montant, pageable);
    }

    @Override
    public Page<Produit> findAll(String monnaie, String nomProduit, Long montant, Pageable pageable) {
        log.debug("Request to get all Produits");
        QProduit produit = QProduit.produit;
        BooleanExpression predicate = null;
        boolean added = false;
        if (monnaie != null && !monnaie.isEmpty()) {
            predicate = produit.monnaie.likeIgnoreCase("%"+monnaie+"%");
            added = true;
        }
        if (nomProduit != null && !nomProduit.isEmpty()) {
            if (added) {
                predicate = predicate.and(produit.nomProduit.likeIgnoreCase("%"+nomProduit+"%"));
            } else {
                predicate = produit.nomProduit.likeIgnoreCase("%"+nomProduit+"%");
                added = true;
            }
        }
        if (montant != null) {
            if (added) {
                predicate = predicate.and(produit.montant.goe(montant));
            } else {
                predicate = produit.montant.goe(montant);
            }
        }
        if (predicate != null) {
            System.out.println("Produit predicate contains parameters");
            return produitRepository.findAll(predicate, pageable);
        } else {
            System.out.println("Produit predicate do not contain parameters");
            return produitRepository.findAll(pageable);
        }
    }
}
