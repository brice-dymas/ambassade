package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Montant;
import com.urservices.ambassade.service.MontantService;
import com.urservices.ambassade.web.rest.errors.BadRequestAlertException;
import com.urservices.ambassade.web.rest.util.HeaderUtil;
import com.urservices.ambassade.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Montant.
 */
@RestController
@RequestMapping("/api")
public class MontantResource {

    private final Logger log = LoggerFactory.getLogger(MontantResource.class);

    private static final String ENTITY_NAME = "montant";

    private final MontantService montantService;

    public MontantResource(MontantService montantService) {
        this.montantService = montantService;
    }

    /**
     * POST  /montants : Create a new montant.
     *
     * @param montant the montant to create
     * @return the ResponseEntity with status 201 (Created) and with body the new montant, or with status 400 (Bad Request) if the montant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/montants")
    @Timed
    public ResponseEntity<Montant> createMontant(@Valid @RequestBody Montant montant) throws URISyntaxException {
        log.debug("REST request to save Montant : {}", montant);
        if (montant.getId() != null) {
            throw new BadRequestAlertException("A new montant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Montant result = montantService.save(montant);
        return ResponseEntity.created(new URI("/api/montants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /montants : Updates an existing montant.
     *
     * @param montant the montant to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated montant,
     * or with status 400 (Bad Request) if the montant is not valid,
     * or with status 500 (Internal Server Error) if the montant couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/montants")
    @Timed
    public ResponseEntity<Montant> updateMontant(@Valid @RequestBody Montant montant) throws URISyntaxException {
        log.debug("REST request to update Montant : {}", montant);
        if (montant.getId() == null) {
            return createMontant(montant);
        }
        Montant result = montantService.save(montant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, montant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /montants : get all the montants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of montants in body
     */
    @GetMapping("/montants")
    @Timed
    public ResponseEntity<List<Montant>> getAllMontants(WebRequest webRequest, Pageable pageable) {

        log.debug("REST request to get a page of Montants");

        String monnaie = webRequest.getParameter("monnaie");
        String produit = webRequest.getParameter("produit");
        final Long montant = (webRequest.getParameter("montant") != null && !webRequest.getParameter("montant").equals(""))
            ? Long.valueOf(webRequest.getParameter("montant")) : -1;

        final Integer page = webRequest.getParameter("page") != null
            ? Integer.valueOf(webRequest.getParameter("page")) : 0;
        final Integer size = webRequest.getParameter("size") != null
            ? Integer.valueOf(webRequest.getParameter("size")) : 5;
        Page<Montant> pageMontant;

        pageMontant = montantService.findByMonnaieAndProduitAndMontant(monnaie,produit,montant,page,size);
//        System.out.println("Getting a page of Montants with monnaie= "+monnaie+ "and produit= "+ produit+ "and MONTANT= "+ montant);
//        if (montant != null) {
////            page = montantService.findAll(pageable);
//            System.out.println("Getting a page of Montants with monnaie= "+monnaie+ "and produit= "+ produit+ "and montant= "+ montant);
//            pageMontant = montantService.findByMonnaieAndProduitAndMontant(monnaie,produit,montant,pageable);
//        }else {
//            System.out.println("Getting a page of Montants with monnaie= "+monnaie+ "and produit= "+ produit+ "and montant= "+ montant);
//            pageMontant = montantService.findByMonnaieAndProduit(monnaie,produit,pageable);
//        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageMontant, "/api/montants");
        return new ResponseEntity<>(pageMontant.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /montants/:id : get the "id" montant.
     *
     * @param id the id of the montant to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the montant, or with status 404 (Not Found)
     */
    @GetMapping("/montants/{id}")
    @Timed
    public ResponseEntity<Montant> getMontant(@PathVariable Long id) {
        log.debug("REST request to get Montant : {}", id);
        Montant montant = montantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(montant));
    }

    /**
     * DELETE  /montants/:id : delete the "id" montant.
     *
     * @param id the id of the montant to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/montants/{id}")
    @Timed
    public ResponseEntity<Void> deleteMontant(@PathVariable Long id) {
        log.debug("REST request to delete Montant : {}", id);
        montantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
