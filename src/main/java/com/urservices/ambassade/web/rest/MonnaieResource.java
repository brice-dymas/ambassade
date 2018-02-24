package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Monnaie;
import com.urservices.ambassade.service.MonnaieService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Monnaie.
 */
@RestController
@RequestMapping("/api")
public class MonnaieResource {

    private final Logger log = LoggerFactory.getLogger(MonnaieResource.class);

    private static final String ENTITY_NAME = "monnaie";

    private final MonnaieService monnaieService;

    public MonnaieResource(MonnaieService monnaieService) {
        this.monnaieService = monnaieService;
    }

    /**
     * POST  /monnaies : Create a new monnaie.
     *
     * @param monnaie the monnaie to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monnaie, or with status 400 (Bad Request) if the monnaie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monnaies")
    @Timed
    public ResponseEntity<Monnaie> createMonnaie(@Valid @RequestBody Monnaie monnaie) throws URISyntaxException {
        log.debug("REST request to save Monnaie : {}", monnaie);
        if (monnaie.getId() != null) {
            throw new BadRequestAlertException("A new monnaie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Monnaie result = monnaieService.save(monnaie);
        return ResponseEntity.created(new URI("/api/monnaies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monnaies : Updates an existing monnaie.
     *
     * @param monnaie the monnaie to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monnaie,
     * or with status 400 (Bad Request) if the monnaie is not valid,
     * or with status 500 (Internal Server Error) if the monnaie couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monnaies")
    @Timed
    public ResponseEntity<Monnaie> updateMonnaie(@Valid @RequestBody Monnaie monnaie) throws URISyntaxException {
        log.debug("REST request to update Monnaie : {}", monnaie);
        if (monnaie.getId() == null) {
            return createMonnaie(monnaie);
        }
        Monnaie result = monnaieService.save(monnaie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monnaie.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monnaies : get all the monnaies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of monnaies in body
     */
    @GetMapping("/monnaies")
    @Timed
    public ResponseEntity<List<Monnaie>> getAllMonnaies(Pageable pageable) {
        log.debug("REST request to get a page of Monnaies");
        Page<Monnaie> page = monnaieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monnaies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /monnaies/:id : get the "id" monnaie.
     *
     * @param id the id of the monnaie to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monnaie, or with status 404 (Not Found)
     */
    @GetMapping("/monnaies/{id}")
    @Timed
    public ResponseEntity<Monnaie> getMonnaie(@PathVariable Long id) {
        log.debug("REST request to get Monnaie : {}", id);
        Monnaie monnaie = monnaieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(monnaie));
    }

    /**
     * DELETE  /monnaies/:id : delete the "id" monnaie.
     *
     * @param id the id of the monnaie to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monnaies/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonnaie(@PathVariable Long id) {
        log.debug("REST request to delete Monnaie : {}", id);
        monnaieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
