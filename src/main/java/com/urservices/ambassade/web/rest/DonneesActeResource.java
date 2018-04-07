package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.DonneesActe;
import com.urservices.ambassade.domain.enumeration.Sexe;
import com.urservices.ambassade.domain.enumeration.Statut;
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
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.Arrays;
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
    public ResponseEntity<List<DonneesActe>> getAllDonneesActes(WebRequest webRequest, Pageable pageable) {
        log.debug("REST request to get a page of DonneesActes");

        String reference = webRequest.getParameter("reference") !=null && !webRequest.getParameter("reference").isEmpty() ?
            webRequest.getParameter("reference"): null;
        String registreSpecialRD = webRequest.getParameter("registreSpecialRD") !=null &&
            !webRequest.getParameter("registreSpecialRD").isEmpty() ? webRequest.getParameter("registreSpecialRD"): null;
        String nomEnfant = webRequest.getParameter("nomEnfant") !=null && !webRequest.getParameter("nomEnfant").isEmpty() ?
            webRequest.getParameter("nomEnfant"): null;
        String registre = webRequest.getParameter("registre") !=null && !webRequest.getParameter("registre").isEmpty() ?
            webRequest.getParameter("registre"): null;
        String numero = webRequest.getParameter("numero") !=null && !webRequest.getParameter("numero").isEmpty() ?
            webRequest.getParameter("numero"): null;
        String nomPere = webRequest.getParameter("nomPere") !=null && !webRequest.getParameter("nomPere").isEmpty() ?
            webRequest.getParameter("nomPere"): null;
        String prenomPere = webRequest.getParameter("prenomPere") !=null && !webRequest.getParameter("prenomPere").isEmpty() ?
            webRequest.getParameter("prenomPere"):null;
        String nomMere = webRequest.getParameter("nomMere") !=null && webRequest.getParameter("nomMere").isEmpty() ?
            webRequest.getParameter("nomMere"):null;
        String prenomMere = webRequest.getParameter("prenomMere") !=null && !webRequest.getParameter("prenomMere").isEmpty() ?
            webRequest.getParameter("prenomMere"):null;
        String villeNaissance = webRequest.getParameter("villeNaissance") !=null && !webRequest.getParameter("villeNaissance").isEmpty()
            ? webRequest.getParameter("villeNaissance"):null;
        String adressePere = webRequest.getParameter("adressePere") !=null && !webRequest.getParameter("adressePere").isEmpty()
            ? webRequest.getParameter("adressePere"):null;
        String adresseMere = webRequest.getParameter("adresseMere") !=null && !webRequest.getParameter("adresseMere").isEmpty()
            ? webRequest.getParameter("adresseMere"):null;
        String temoins1 = webRequest.getParameter("temoins1") !=null && !webRequest.getParameter("temoins1").isEmpty()
            ? webRequest.getParameter("temoins1"):null;
        String temoins2 = webRequest.getParameter("temoins2") !=null && !webRequest.getParameter("temoins2").isEmpty()
            ? webRequest.getParameter("temoins2"):null;
        String idPere = webRequest.getParameter("idPere") !=null && !webRequest.getParameter("idPere").isEmpty()
            ? webRequest.getParameter("idPere"):null;
        String idMere = webRequest.getParameter("idMere") !=null && !webRequest.getParameter("idMere").isEmpty()
            ? webRequest.getParameter("idMere"):null;
        String juridiction = webRequest.getParameter("juridiction") !=null && !webRequest.getParameter("juridiction").isEmpty()
            ? webRequest.getParameter("juridiction"):null;
        String livre = webRequest.getParameter("livre") !=null && !webRequest.getParameter("livre").isEmpty()
            ? webRequest.getParameter("livre"):null;
        String notes = webRequest.getParameter("notes") !=null && !webRequest.getParameter("notes").isEmpty()
            ? webRequest.getParameter("notes"):null;
        String feuille = webRequest.getParameter("feuille") !=null && !webRequest.getParameter("feuille").isEmpty()
            ? webRequest.getParameter("feuille"):null;
        String acte = webRequest.getParameter("acte") !=null && !webRequest.getParameter("acte").isEmpty()
            ? webRequest.getParameter("acte"):null;
        Integer annee = webRequest.getParameter("annee") !=null && !webRequest.getParameter("annee").isEmpty()
            ? Integer.valueOf(webRequest.getParameter("annee")): null;

        Sexe sexe = webRequest.getParameter("sexe") !=null && !webRequest.getParameter("sexe").isEmpty()
            ?Sexe.valueOf(webRequest.getParameter("sexe")) : null;
        Statut statut  = webRequest.getParameter("statut") !=null  && !webRequest.getParameter("statut").isEmpty() ?
            Statut.valueOf(webRequest.getParameter("statut")):null;
        String  dateNaissanceChiffreDebStr = webRequest.getParameter("dateNaissanceChiffre") !=null &&
            !webRequest.getParameter("dateNaissanceChiffre").isEmpty()? webRequest.getParameter("dateNaissanceChiffre"): null;

        String dateDuJourChiffreDebStr = webRequest.getParameter("dateDuJourChiffre") !=null &&
            !webRequest.getParameter("dateDuJourChiffre").isEmpty()? webRequest.getParameter("dateDuJourChiffre"): null;

        String  dateNaissanceChiffreFinStr = webRequest.getParameter("dateNaissanceChiffreFin") !=null &&
            !webRequest.getParameter("dateNaissanceChiffreFin").isEmpty()? webRequest.getParameter("dateNaissanceChiffreFin"): null;

        String dateDuJourChiffreFinStr = webRequest.getParameter("dateDuJourChiffreFin") !=null &&
            !webRequest.getParameter("dateDuJourChiffreFin").isEmpty()? webRequest.getParameter("dateDuJourChiffreFin"): null;

        LocalDate dateDuJourChiffreDeb = null;
        LocalDate dateNaissanceChiffreDeb = null;
        LocalDate dateDuJourChiffreFin = null;
        LocalDate dateNaissanceChiffreFin = null;

        if ( dateDuJourChiffreDebStr != null && !dateDuJourChiffreDebStr.isEmpty()){
            dateDuJourChiffreDeb = LocalDate.parse(dateDuJourChiffreDebStr);

        }
        if (dateNaissanceChiffreDebStr != null && !dateNaissanceChiffreDebStr.isEmpty()){
            dateNaissanceChiffreDeb = LocalDate.parse(dateNaissanceChiffreDebStr);
        }
        if ( dateDuJourChiffreFinStr != null && !dateDuJourChiffreFinStr.isEmpty()){
            dateDuJourChiffreFin = LocalDate.parse(dateDuJourChiffreFinStr);

        }
        if (dateNaissanceChiffreFinStr != null && !dateNaissanceChiffreFinStr.isEmpty()){
            dateNaissanceChiffreFin = LocalDate.parse(dateNaissanceChiffreFinStr);
        }

        Page<DonneesActe> page = donneesActeService.findAll(reference,nomEnfant,dateDuJourChiffreDeb,dateDuJourChiffreFin,
            registre,statut,nomPere,prenomPere,nomMere,prenomMere,dateNaissanceChiffreDeb,dateNaissanceChiffreFin,
            annee,sexe,villeNaissance,adressePere,adresseMere,juridiction,livre,acte,pageable);

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
