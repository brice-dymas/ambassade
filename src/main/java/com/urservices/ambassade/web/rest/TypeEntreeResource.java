package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.TypeEntree;
import com.urservices.ambassade.service.TypeEntreeService;
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
 * REST controller for managing TypeEntree.
 */
@RestController
@RequestMapping("/api")
public class TypeEntreeResource {

    private final Logger log = LoggerFactory.getLogger(TypeEntreeResource.class);

    private static final String ENTITY_NAME = "typeEntree";

    private final TypeEntreeService typeEntreeService;

    public TypeEntreeResource(TypeEntreeService typeEntreeService) {
        this.typeEntreeService = typeEntreeService;
    }

    /**
     * POST  /type-entrees : Create a new typeEntree.
     *
     * @param typeEntree the typeEntree to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeEntree, or with status 400 (Bad Request) if the typeEntree has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-entrees")
    @Timed
    public ResponseEntity<TypeEntree> createTypeEntree(@Valid @RequestBody TypeEntree typeEntree) throws URISyntaxException {
        log.debug("REST request to save TypeEntree : {}", typeEntree);
        if (typeEntree.getId() != null) {
            throw new BadRequestAlertException("A new typeEntree cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeEntree result = typeEntreeService.save(typeEntree);
        return ResponseEntity.created(new URI("/api/type-entrees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-entrees : Updates an existing typeEntree.
     *
     * @param typeEntree the typeEntree to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeEntree,
     * or with status 400 (Bad Request) if the typeEntree is not valid,
     * or with status 500 (Internal Server Error) if the typeEntree couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-entrees")
    @Timed
    public ResponseEntity<TypeEntree> updateTypeEntree(@Valid @RequestBody TypeEntree typeEntree) throws URISyntaxException {
        log.debug("REST request to update TypeEntree : {}", typeEntree);
        if (typeEntree.getId() == null) {
            return createTypeEntree(typeEntree);
        }
        TypeEntree result = typeEntreeService.save(typeEntree);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeEntree.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-entrees : get all the typeEntrees.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeEntrees in body
     */
    @GetMapping("/type-entrees")
    @Timed
    public ResponseEntity<List<TypeEntree>> getAllTypeEntrees(Pageable pageable) {
        log.debug("REST request to get a page of TypeEntrees");
        Page<TypeEntree> page = typeEntreeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/type-entrees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /type-entrees/:id : get the "id" typeEntree.
     *
     * @param id the id of the typeEntree to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeEntree, or with status 404 (Not Found)
     */
    @GetMapping("/type-entrees/{id}")
    @Timed
    public ResponseEntity<TypeEntree> getTypeEntree(@PathVariable Long id) {
        log.debug("REST request to get TypeEntree : {}", id);
        TypeEntree typeEntree = typeEntreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(typeEntree));
    }

    /**
     * DELETE  /type-entrees/:id : delete the "id" typeEntree.
     *
     * @param id the id of the typeEntree to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-entrees/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeEntree(@PathVariable Long id) {
        log.debug("REST request to delete TypeEntree : {}", id);
        typeEntreeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
