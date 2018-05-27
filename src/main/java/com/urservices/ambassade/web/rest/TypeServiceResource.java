package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.TypeService;
import com.urservices.ambassade.service.TypeServiceService;
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
 * REST controller for managing TypeService.
 */
@RestController
@RequestMapping("/api")
public class TypeServiceResource {

    private final Logger log = LoggerFactory.getLogger(TypeServiceResource.class);

    private static final String ENTITY_NAME = "typeService";

    private final TypeServiceService typeServiceService;

    public TypeServiceResource(TypeServiceService typeServiceService) {
        this.typeServiceService = typeServiceService;
    }

    /**
     * POST  /type-services : Create a new typeService.
     *
     * @param typeService the typeService to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeService, or with status 400 (Bad Request) if the typeService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-services")
    @Timed
    public ResponseEntity<TypeService> createTypeService(@Valid @RequestBody TypeService typeService) throws URISyntaxException {
        log.debug("REST request to save TypeService : {}", typeService);
        if (typeService.getId() != null) {
            throw new BadRequestAlertException("A new typeService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeService result = typeServiceService.save(typeService);
        return ResponseEntity.created(new URI("/api/type-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-services : Updates an existing typeService.
     *
     * @param typeService the typeService to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeService,
     * or with status 400 (Bad Request) if the typeService is not valid,
     * or with status 500 (Internal Server Error) if the typeService couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-services")
    @Timed
    public ResponseEntity<TypeService> updateTypeService(@Valid @RequestBody TypeService typeService) throws URISyntaxException {
        log.debug("REST request to update TypeService : {}", typeService);
        if (typeService.getId() == null) {
            return createTypeService(typeService);
        }
        TypeService result = typeServiceService.save(typeService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeService.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-services : get all the typeServices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeServices in body
     */
    @GetMapping("/type-services")
    @Timed
    public ResponseEntity<List<TypeService>> getAllTypeServices(Pageable pageable) {
        log.debug("REST request to get a page of TypeServices");
        Page<TypeService> page = typeServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/type-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /type-services/:id : get the "id" typeService.
     *
     * @param id the id of the typeService to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeService, or with status 404 (Not Found)
     */
    @GetMapping("/type-services/{id}")
    @Timed
    public ResponseEntity<TypeService> getTypeService(@PathVariable Long id) {
        log.debug("REST request to get TypeService : {}", id);
        TypeService typeService = typeServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(typeService));
    }

    /**
     * DELETE  /type-services/:id : delete the "id" typeService.
     *
     * @param id the id of the typeService to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeService(@PathVariable Long id) {
        log.debug("REST request to delete TypeService : {}", id);
        typeServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
