package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Paiement;
import com.urservices.ambassade.service.PaiementService;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Paiement.
 */
@RestController
@RequestMapping("/api")
public class PaiementResource {

    private final Logger log = LoggerFactory.getLogger(PaiementResource.class);

    private static final String ENTITY_NAME = "paiement";

    private final PaiementService paiementService;

    public PaiementResource(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    /**
     * POST  /paiements : Create a new paiement.
     *
     * @param paiement the paiement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paiement, or with status 400 (Bad Request) if the paiement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paiements")
    @Timed
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to save Paiement : {}", paiement);
        if (paiement.getId() != null) {
            throw new BadRequestAlertException("A new paiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Paiement result = paiementService.save(paiement);
        return ResponseEntity.created(new URI("/api/paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paiements : Updates an existing paiement.
     *
     * @param paiement the paiement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paiement,
     * or with status 400 (Bad Request) if the paiement is not valid,
     * or with status 500 (Internal Server Error) if the paiement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paiements")
    @Timed
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to update Paiement : {}", paiement);
        if (paiement.getId() == null) {
            return createPaiement(paiement);
        }
        Paiement result = paiementService.save(paiement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paiement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paiements : get all the paiements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of paiements in body
     */
    @GetMapping("/paiements")
    @Timed
    public ResponseEntity<List<Paiement>> getAllPaiements(WebRequest webRequest, Pageable pageable) {
        log.debug("REST request to get a page of Paiements");

        String datePaiementStr = webRequest.getParameter("datePaiement") !=null && !webRequest.getParameter("datePaiement").isEmpty()
            ? webRequest.getParameter("datePaiement"): null;
        String datePaiementFinStr = webRequest.getParameter("datePaiementFin") !=null && !webRequest.getParameter("datePaiementFin").isEmpty()
            ? webRequest.getParameter("datePaiementFin"): null;
        String numeroPaiement = webRequest.getParameter("numeroPaiement") !=null && !webRequest.getParameter("numeroPaiement").isEmpty()
            ? webRequest.getParameter("numeroPaiement"): null;
        Long visa = webRequest.getParameter("visa") !=null && !webRequest.getParameter("visa").isEmpty()
            ? Long.valueOf(webRequest.getParameter("visa")): null;
        Long passeport = webRequest.getParameter("passeport") !=null && !webRequest.getParameter("passeport").isEmpty()
            ? Long.valueOf(webRequest.getParameter("passeport")): null;
        Long typeService = webRequest.getParameter("typeService") !=null && !webRequest.getParameter("typeService").isEmpty()
            ? Long.valueOf(webRequest.getParameter("typeService")): null;
        LocalDate datePaiement = datePaiementStr != null ? LocalDate.parse(datePaiementStr) : null;
        LocalDate datePaiementFin = datePaiementStr != null ? LocalDate.parse(datePaiementStr) : null;

        Page<Paiement> page = paiementService.findAll(numeroPaiement, datePaiement,datePaiementFin,visa,passeport,typeService,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/paiements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /paiements/:id : get the "id" paiement.
     *
     * @param id the id of the paiement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the paiement, or with status 404 (Not Found)
     */
    @GetMapping("/paiements/{id}")
    @Timed
    public ResponseEntity<Paiement> getPaiement(@PathVariable Long id) {
        log.debug("REST request to get Paiement : {}", id);
        Paiement paiement = paiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paiement));
    }

    /**
     * DELETE  /paiements/:id : delete the "id" paiement.
     *
     * @param id the id of the paiement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paiements/{id}")
    @Timed
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        log.debug("REST request to delete Paiement : {}", id);
        paiementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
