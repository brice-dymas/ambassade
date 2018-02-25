package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.service.DonneesActeService;
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
 * REST controller for managing DonneesActe.
 */
@RestController
@RequestMapping("/api")
public class DonneesActeResource {

    private final Logger log = LoggerFactory.getLogger(DonneesActeResource.class);

    private static final String ENTITY_NAME = "donneesActe";

    private final DonneesActeService donneesActeService;

    public DonneesActeResource(DonneesActeService donneesActeService) {
        this.donneesActeService = donneesActeService;
    }

    /**
     * POST  /donnees-actes : Create a new donneesActe.
     *
     * @param donneesActe the donneesActe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donneesActe, or with status 400 (Bad Request) if the donneesActe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/donnees-actes")
    @Timed
    public ResponseEntity<DonneesActe> createDonneesActe(@Valid @RequestBody DonneesActe donneesActe) throws URISyntaxException {
        log.debug("REST request to save DonneesActe : {}", donneesActe);
        if (donneesActe.getId() != null) {
            throw new BadRequestAlertException("A new donneesActe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonneesActe result = donneesActeService.save(donneesActe);
        return ResponseEntity.created(new URI("/api/donnees-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /donnees-actes : Updates an existing donneesActe.
     *
     * @param donneesActe the donneesActe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donneesActe,
     * or with status 400 (Bad Request) if the donneesActe is not valid,
     * or with status 500 (Internal Server Error) if the donneesActe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/donnees-actes")
    @Timed
    public ResponseEntity<DonneesActe> updateDonneesActe(@Valid @RequestBody DonneesActe donneesActe) throws URISyntaxException {
        log.debug("REST request to update DonneesActe : {}", donneesActe);
        if (donneesActe.getId() == null) {
            return createDonneesActe(donneesActe);
        }
        DonneesActe result = donneesActeService.save(donneesActe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donneesActe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /donnees-actes : get all the donneesActes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of donneesActes in body
     */
    @GetMapping("/donnees-actes")
    @Timed
    public ResponseEntity<List<DonneesActe>> getAllDonneesActes(Pageable pageable) {
        log.debug("REST request to get a page of DonneesActes");
        Page<DonneesActe> page = donneesActeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/donnees-actes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /donnees-actes/:id : get the "id" donneesActe.
     *
     * @param id the id of the donneesActe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donneesActe, or with status 404 (Not Found)
     */
    @GetMapping("/donnees-actes/{id}")
    @Timed
    public ResponseEntity<DonneesActe> getDonneesActe(@PathVariable Long id) {
        log.debug("REST request to get DonneesActe : {}", id);
        DonneesActe donneesActe = donneesActeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(donneesActe));
    }

    /**
     * DELETE  /donnees-actes/:id : delete the "id" donneesActe.
     *
     * @param id the id of the donneesActe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/donnees-actes/{id}")
    @Timed
    public ResponseEntity<Void> deleteDonneesActe(@PathVariable Long id) {
        log.debug("REST request to delete DonneesActe : {}", id);
        donneesActeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
