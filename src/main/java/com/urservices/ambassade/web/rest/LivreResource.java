package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Livre;
import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.service.LivreService;
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
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Livre.
 */
@RestController
@RequestMapping("/api")
public class LivreResource {

    private final Logger log = LoggerFactory.getLogger(LivreResource.class);

    private static final String ENTITY_NAME = "livre";

    private final LivreService livreService;

    public LivreResource(LivreService livreService) {
        this.livreService = livreService;
    }

    /**
     * POST  /livres : Create a new livre.
     *
     * @param livre the livre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new livre, or with status 400 (Bad Request) if the livre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/livres")
    @Timed
    public ResponseEntity<Livre> createLivre(@Valid @RequestBody Livre livre) throws URISyntaxException {
        log.debug("REST request to save Livre : {}", livre);
        if (livre.getId() != null) {
            throw new BadRequestAlertException("A new livre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Livre result = livreService.save(livre);
        return ResponseEntity.created(new URI("/api/livres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /livres : Updates an existing livre.
     *
     * @param livre the livre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated livre,
     * or with status 400 (Bad Request) if the livre is not valid,
     * or with status 500 (Internal Server Error) if the livre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/livres")
    @Timed
    public ResponseEntity<Livre> updateLivre(@Valid @RequestBody Livre livre) throws URISyntaxException {
        log.debug("REST request to update Livre : {}", livre);
        if (livre.getId() == null) {
            return createLivre(livre);
        }
        Livre result = livreService.save(livre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, livre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /livres : get all the livres.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of livres in body
     */
    @GetMapping("/livres")
    @Timed
    public ResponseEntity<List<Livre>> getAllLivres(WebRequest webRequest, Pageable pageable) {
        log.debug("REST request to get a page of Livres");

        Long quantite =  webRequest.getParameter("quantite") !=null && !webRequest.getParameter("quantite").isEmpty() ?
            Long.valueOf(webRequest.getParameter("quantite")): null;
        Integer annee =  webRequest.getParameter("annee") !=null && !webRequest.getParameter("annee").isEmpty() ?
            Integer.valueOf(webRequest.getParameter("annee")): null;
        String codeISBN = webRequest.getParameter("codeISBN") !=null && !webRequest.getParameter("codeISBN").isEmpty()
            ? webRequest.getParameter("codeISBN"):  null;
        String prenom = webRequest.getParameter("prenom") !=null && !webRequest.getParameter("prenom").isEmpty()
            ? webRequest.getParameter("prenom"):  null;
        String auteur = webRequest.getParameter("auteur") !=null && !webRequest.getParameter("auteur").isEmpty()
            ? webRequest.getParameter("auteur"):  null;
        String titre = webRequest.getParameter("titre") !=null && !webRequest.getParameter("titre").isEmpty()
            ? webRequest.getParameter("titre"):  null;
        String edition = webRequest.getParameter("edition") !=null && !webRequest.getParameter("edition").isEmpty()
            ? webRequest.getParameter("edition"):  null;
        String etagere = webRequest.getParameter("etagere") !=null && !webRequest.getParameter("etagere").isEmpty()
            ? webRequest.getParameter("etagere"):  null;
        String categorie = webRequest.getParameter("categorie") !=null && !webRequest.getParameter("categorie").isEmpty()
            ? webRequest.getParameter("categorie"):  null;
        String resume = webRequest.getParameter("resume") !=null && !webRequest.getParameter("resume").isEmpty()
            ? webRequest.getParameter("resume"):  null;
        String disponible = webRequest.getParameter("disponible") !=null && !webRequest.getParameter("disponible").isEmpty()
            ? webRequest.getParameter("disponible"):  null;
        String page = webRequest.getParameter("page") !=null && !webRequest.getParameter("page").isEmpty()
            ? webRequest.getParameter("page"):  null;
        String consultation = webRequest.getParameter("consultation") !=null && !webRequest.getParameter("consultation").isEmpty()
            ? webRequest.getParameter("consultation"):  null;
        String origine = webRequest.getParameter("origine") !=null && !webRequest.getParameter("origine").isEmpty()
            ? webRequest.getParameter("origine"):  null;
        String sousTitre = webRequest.getParameter("sousTitre") !=null && !webRequest.getParameter("sousTitre").isEmpty()
            ? webRequest.getParameter("sousTitre"):  null;
        String collection = webRequest.getParameter("collection") !=null && !webRequest.getParameter("collection").isEmpty()
            ? webRequest.getParameter("collection"):  null;
        String impression = webRequest.getParameter("impression") !=null && !webRequest.getParameter("impression").isEmpty()
            ? webRequest.getParameter("impression"):  null;
        String format = webRequest.getParameter("format") !=null && !webRequest.getParameter("format").isEmpty()
            ? webRequest.getParameter("format"):  null;
        String index = webRequest.getParameter("index") !=null && !webRequest.getParameter("index").isEmpty()
            ? webRequest.getParameter("index"):  null;
        String bibliographie = webRequest.getParameter("bibliographie") !=null && !webRequest.getParameter("bibliographie").isEmpty()
            ? webRequest.getParameter("bibliographie"):  null;
        String lieuEdition = webRequest.getParameter("lieuEdition") !=null && !webRequest.getParameter("lieuEdition").isEmpty()
            ? webRequest.getParameter("lieuEdition"):  null;
        String lieuImpression = webRequest.getParameter("lieuImpression") !=null && !webRequest.getParameter("lieuImpression").isEmpty()
            ? webRequest.getParameter("lieuImpression"):  null;
        String illustration = webRequest.getParameter("illustration") !=null && !webRequest.getParameter("illustration").isEmpty()
            ? webRequest.getParameter("illustration"):  null;
        String observation= webRequest.getParameter("observation") !=null && !webRequest.getParameter("observation").isEmpty()
            ? webRequest.getParameter("observation"):  null;
        String statistique= webRequest.getParameter("statistique") !=null && !webRequest.getParameter("statistique").isEmpty()
            ? webRequest.getParameter("statistique"):  null;
        String glossaire = webRequest.getParameter("glossaire") !=null && !webRequest.getParameter("glossaire").isEmpty()
            ? webRequest.getParameter("glossaire"):  null;

//        Page<Livre> pageLivre = livreService.findAll(pageable);
        Page<Livre> pageLivre = livreService.findAll(codeISBN,auteur,titre,edition,etagere,annee,categorie,
            quantite,disponible,origine,collection,format,lieuEdition,lieuImpression,prenom,pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageLivre, "/api/livres");
        return new ResponseEntity<>(pageLivre.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /livres/:id : get the "id" livre.
     *
     * @param id the id of the livre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the livre, or with status 404 (Not Found)
     */
    @GetMapping("/livres/{id}")
    @Timed
    public ResponseEntity<Livre> getLivre(@PathVariable Long id) {
        log.debug("REST request to get Livre : {}", id);
        Livre livre = livreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(livre));
    }

    /**
     * DELETE  /livres/:id : delete the "id" livre.
     *
     * @param id the id of the livre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/livres/{id}")
    @Timed
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        log.debug("REST request to delete Livre : {}", id);
        livreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
