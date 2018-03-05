package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Caisse;
import com.urservices.ambassade.service.CaisseService;
import com.urservices.ambassade.web.rest.errors.BadRequestAlertException;
import com.urservices.ambassade.web.rest.util.HeaderUtil;
import com.urservices.ambassade.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Caisse.
 */
@RestController
@RequestMapping("/api")
public class CaisseResource {

    private final Logger log = LoggerFactory.getLogger(CaisseResource.class);

    private static final String ENTITY_NAME = "caisse";

    private final CaisseService caisseService;

    public CaisseResource(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    /**
     * POST  /caisses : Create a new caisse.
     *
     * @param caisse the caisse to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caisse, or with status 400 (Bad Request) if the caisse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/caisses")
    @Timed
    public ResponseEntity<Caisse> createCaisse(@Valid @RequestBody Caisse caisse) throws URISyntaxException {
        log.debug("REST request to save Caisse : {}", caisse);
        if (caisse.getId() != null) {
            throw new BadRequestAlertException("A new caisse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Caisse result = caisseService.save(caisse);
        return ResponseEntity.created(new URI("/api/caisses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /caisses : Updates an existing caisse.
     *
     * @param caisse the caisse to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caisse,
     * or with status 400 (Bad Request) if the caisse is not valid,
     * or with status 500 (Internal Server Error) if the caisse couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/caisses")
    @Timed
    public ResponseEntity<Caisse> updateCaisse(@Valid @RequestBody Caisse caisse) throws URISyntaxException {
        log.debug("REST request to update Caisse : {}", caisse);
        if (caisse.getId() == null) {
            return createCaisse(caisse);
        }
        Caisse result = caisseService.save(caisse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caisse.getId().toString()))
            .body(result);
    }

    /**
     * GET  /caisses : get all the caisses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caisses in body
     */
    @GetMapping("/caisses")
    @Timed
    public ResponseEntity<List<Caisse>> getAllCaisses(WebRequest webRequest, Pageable pageable) {
        log.debug("REST request to get a page of Caisses");

        
        //String pattern = "dd-MM-yyyy";
        String pattern = "yyyy-MM-dd";
        Page<Caisse> pageCaisse;
        DateTimeFormatter Parser = DateTimeFormatter.ofPattern(pattern);
        final String dateRetourString = webRequest.getParameter("dateRetour") != null
            ? webRequest.getParameter("dateRetour") : "01/01/1960";
        String dateDuJourString  = webRequest.getParameter("dateDuJour") != null
            ? webRequest.getParameter("dateDuJour") : "01/01/1960";
            
        LocalDate localDate = LocalDate.parse(dateDuJourString);

        ZonedDateTime dateDuJour = ZonedDateTime.parse(dateDuJourString);
        ZonedDateTime dateRetour = ZonedDateTime.parse(dateRetourString, Parser);
        //ZonedDateTime dateDuJour = localDate.atStartOfDay(ZoneId.systemDefault());

        Long reference = webRequest.getParameter("reference") != null ? Long.valueOf(webRequest.getParameter("reference")) : 0L;
        BigDecimal montant = webRequest.getParameter("montant") != null ?
            BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("montant"))) : new BigDecimal(0);
        Integer num = webRequest.getParameter("num") != null ? Integer.valueOf(webRequest.getParameter("num")) : 0;
        String monnaie = webRequest.getParameter("monnaie") != null ? webRequest.getParameter("monnaie") : "";
        String nom = webRequest.getParameter("nom") != null ? webRequest.getParameter("nom") : "";
        String prenom = webRequest.getParameter("prenom") != null ? webRequest.getParameter("prenom") : "";
        String typeID = webRequest.getParameter("typeID") != null ? webRequest.getParameter("typeID") : "";
        String serviceConcerne = webRequest.getParameter("serviceConcerne") != null ? webRequest.getParameter("serviceConcerne") : "";
        String telephone = webRequest.getParameter("telephone") != null ? webRequest.getParameter("telephone") : "";
        String paiement = webRequest.getParameter("paiement") != null ? webRequest.getParameter("paiement") : "";
        String numero = webRequest.getParameter("numero") != null ? webRequest.getParameter("numero") : "";

        pageCaisse = caisseService.searchAll(dateDuJour,reference,montant,num,dateRetour,monnaie,nom,prenom,
            typeID,serviceConcerne,telephone,paiement,numero,pageable);
//        Page<Caisse> pageCaisse = caisseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageCaisse, "/api/caisses");
        return new ResponseEntity<>(pageCaisse.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /caisses/:id : get the "id" caisse.
     *
     * @param id the id of the caisse to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caisse, or with status 404 (Not Found)
     */
    @GetMapping("/caisses/{id}")
    @Timed
    public ResponseEntity<Caisse> getCaisse(@PathVariable Long id) {
        log.debug("REST request to get Caisse : {}", id);
        Caisse caisse = caisseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(caisse));
    }

    /**
     * DELETE  /caisses/:id : delete the "id" caisse.
     *
     * @param id the id of the caisse to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/caisses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaisse(@PathVariable Long id) {
        log.debug("REST request to delete Caisse : {}", id);
        caisseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
