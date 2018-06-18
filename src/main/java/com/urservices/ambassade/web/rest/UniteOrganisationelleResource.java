package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.UniteOrganisationelle;
import com.urservices.ambassade.service.UniteOrganisationelleService;
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
 * REST controller for managing UniteOrganisationelle.
 */
@RestController
@RequestMapping("/api")
public class UniteOrganisationelleResource {

    private final Logger log = LoggerFactory.getLogger(UniteOrganisationelleResource.class);

    private static final String ENTITY_NAME = "uniteOrganisationelle";

    private final UniteOrganisationelleService uniteOrganisationelleService;

    public UniteOrganisationelleResource(UniteOrganisationelleService uniteOrganisationelleService) {
        this.uniteOrganisationelleService = uniteOrganisationelleService;
    }

    /**
     * POST  /unite-organisationelles : Create a new uniteOrganisationelle.
     *
     * @param uniteOrganisationelle the uniteOrganisationelle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uniteOrganisationelle, or with status 400 (Bad Request) if the uniteOrganisationelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/unite-organisationelles")
    @Timed
    public ResponseEntity<UniteOrganisationelle> createUniteOrganisationelle(@Valid @RequestBody UniteOrganisationelle uniteOrganisationelle) throws URISyntaxException {
        log.debug("REST request to save UniteOrganisationelle : {}", uniteOrganisationelle);
        if (uniteOrganisationelle.getId() != null) {
            throw new BadRequestAlertException("A new uniteOrganisationelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniteOrganisationelle result = uniteOrganisationelleService.save(uniteOrganisationelle);
        return ResponseEntity.created(new URI("/api/unite-organisationelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /unite-organisationelles : Updates an existing uniteOrganisationelle.
     *
     * @param uniteOrganisationelle the uniteOrganisationelle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uniteOrganisationelle,
     * or with status 400 (Bad Request) if the uniteOrganisationelle is not valid,
     * or with status 500 (Internal Server Error) if the uniteOrganisationelle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/unite-organisationelles")
    @Timed
    public ResponseEntity<UniteOrganisationelle> updateUniteOrganisationelle(@Valid @RequestBody UniteOrganisationelle uniteOrganisationelle) throws URISyntaxException {
        log.debug("REST request to update UniteOrganisationelle : {}", uniteOrganisationelle);
        if (uniteOrganisationelle.getId() == null) {
            return createUniteOrganisationelle(uniteOrganisationelle);
        }
        UniteOrganisationelle result = uniteOrganisationelleService.save(uniteOrganisationelle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uniteOrganisationelle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /unite-organisationelles : get all the uniteOrganisationelles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uniteOrganisationelles in body
     */
    @GetMapping("/unite-organisationelles")
    @Timed
    public ResponseEntity<List<UniteOrganisationelle>> getAllUniteOrganisationelles(Pageable pageable) {
        log.debug("REST request to get a page of UniteOrganisationelles");
        Page<UniteOrganisationelle> page = uniteOrganisationelleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/unite-organisationelles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /unite-organisationelles/:id : get the "id" uniteOrganisationelle.
     *
     * @param id the id of the uniteOrganisationelle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uniteOrganisationelle, or with status 404 (Not Found)
     */
    @GetMapping("/unite-organisationelles/{id}")
    @Timed
    public ResponseEntity<UniteOrganisationelle> getUniteOrganisationelle(@PathVariable Long id) {
        log.debug("REST request to get UniteOrganisationelle : {}", id);
        UniteOrganisationelle uniteOrganisationelle = uniteOrganisationelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uniteOrganisationelle));
    }

    /**
     * DELETE  /unite-organisationelles/:id : delete the "id" uniteOrganisationelle.
     *
     * @param id the id of the uniteOrganisationelle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/unite-organisationelles/{id}")
    @Timed
    public ResponseEntity<Void> deleteUniteOrganisationelle(@PathVariable Long id) {
        log.debug("REST request to delete UniteOrganisationelle : {}", id);
        uniteOrganisationelleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
