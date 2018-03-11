package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Visa;
import com.urservices.ambassade.service.VisaService;
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
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Visa.
 */
@RestController
@RequestMapping("/api")
public class VisaResource {

    private final Logger log = LoggerFactory.getLogger(VisaResource.class);

    private static final String ENTITY_NAME = "visa";

    private final VisaService visaService;

    public VisaResource(VisaService visaService) {
        this.visaService = visaService;
    }

    /**
     * POST  /visas : Create a new visa.
     *
     * @param visa the visa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new visa, or with status 400 (Bad Request) if the visa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/visas")
    @Timed
    public ResponseEntity<Visa> createVisa(@Valid @RequestBody Visa visa) throws URISyntaxException {
        log.debug("REST request to save Visa : {}", visa);
        if (visa.getId() != null) {
            throw new BadRequestAlertException("A new visa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Visa result = visaService.save(visa);
        return ResponseEntity.created(new URI("/api/visas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /visas : Updates an existing visa.
     *
     * @param visa the visa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated visa,
     * or with status 400 (Bad Request) if the visa is not valid,
     * or with status 500 (Internal Server Error) if the visa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/visas")
    @Timed
    public ResponseEntity<Visa> updateVisa(@Valid @RequestBody Visa visa) throws URISyntaxException {
        log.debug("REST request to update Visa : {}", visa);
        if (visa.getId() == null) {
            return createVisa(visa);
        }
        Visa result = visaService.save(visa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, visa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /visas : get all the visas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of visas in body
     */
    @GetMapping("/visas")
    @Timed
    public ResponseEntity<List<Visa>> getAllVisas(WebRequest webRequest, Pageable pageable) {
        log.debug("REST request to get a page of Visas");

        String nom = webRequest.getParameter("nom") !=null ? webRequest.getParameter("nom"):"";
        String prenom = webRequest.getParameter("prenom") !=null ? webRequest.getParameter("prenom"):"";
        String nationalite = webRequest.getParameter("nationalite") !=null ? webRequest.getParameter("nationalite"):"";
        String numeroPasseport = webRequest.getParameter("numeroPasseport") !=null ? webRequest.getParameter("numeroPasseport"):"";
        String cedula = webRequest.getParameter("cedula") !=null ? webRequest.getParameter("cedula"):"";
        Long numeroVisa =  (webRequest.getParameter("numeroVisa") != null && !webRequest.getParameter("numeroVisa").equals(""))
            ? Long.valueOf(webRequest.getParameter("numeroVisa")) : -1;
        Integer validePour =  (webRequest.getParameter("validePour") != null && !webRequest.getParameter("validePour").equals(""))
            ? Integer.valueOf(webRequest.getParameter("validePour")) : -1;
        String nombreEntree = webRequest.getParameter("nombreEntree") !=null ? webRequest.getParameter("nombreEntree"):"";
        String type = webRequest.getParameter("type") !=null ? webRequest.getParameter("type"):"";
        String categorie = webRequest.getParameter("categorie") !=null ? webRequest.getParameter("categorie"):"";
        Integer taxes =  (webRequest.getParameter("taxes") != null && !webRequest.getParameter("taxes").equals(""))
            ? Integer.valueOf(webRequest.getParameter("taxes")) : -1;
        String adresse = webRequest.getParameter("adresse") !=null ? webRequest.getParameter("adresse"):"";
        String remarques = webRequest.getParameter("remarques") !=null ? webRequest.getParameter("remarques"):"";

        String dateEmissionDebStr = webRequest.getParameter("dateEmission") !=null &&
            !webRequest.getParameter("dateEmission").isEmpty() ? webRequest.getParameter("dateEmission"): "1970-01-01";
        String dateEmissionFinStr = webRequest.getParameter("dateEmissionFin") !=null &&
            !webRequest.getParameter("dateEmissionFin").isEmpty() ? webRequest.getParameter("dateEmissionFin"):LocalDate.now().toString();
        String dateExpirationDebStr = webRequest.getParameter("dateExpiration") !=null &&
            !webRequest.getParameter("dateExpiration").isEmpty() ? webRequest.getParameter("dateExpiration"): "1970-01-01";
        String dateExpirationFinStr = webRequest.getParameter("dateExpirationFin") !=null &&
            !webRequest.getParameter("dateExpirationFin").isEmpty() ? webRequest.getParameter("dateExpirationFin"):LocalDate.now().toString();

        LocalDate dateEmissionDeb= LocalDate.parse(dateEmissionDebStr);
        LocalDate dateEmissionFin= LocalDate.parse(dateEmissionFinStr);
        LocalDate dateExpirationDeb= LocalDate.parse(dateExpirationDebStr);
        LocalDate dateExpirationFin= LocalDate.parse(dateExpirationFinStr);

        Page<Visa> page = visaService.searchAll(nom,prenom,nationalite,numeroPasseport,cedula,numeroVisa,dateEmissionDeb, dateEmissionFin,
            dateExpirationDeb, dateExpirationFin, validePour,nombreEntree,type,categorie,taxes,adresse,remarques,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/visas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /visas/:id : get the "id" visa.
     *
     * @param id the id of the visa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the visa, or with status 404 (Not Found)
     */
    @GetMapping("/visas/{id}")
    @Timed
    public ResponseEntity<Visa> getVisa(@PathVariable Long id) {
        log.debug("REST request to get Visa : {}", id);
        Visa visa = visaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(visa));
    }

    /**
     * DELETE  /visas/:id : delete the "id" visa.
     *
     * @param id the id of the visa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/visas/{id}")
    @Timed
    public ResponseEntity<Void> deleteVisa(@PathVariable Long id) {
        log.debug("REST request to delete Visa : {}", id);
        visaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
