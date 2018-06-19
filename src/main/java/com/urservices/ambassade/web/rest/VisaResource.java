package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Paiement;
import com.urservices.ambassade.domain.User;
import com.urservices.ambassade.domain.Visa;
import com.urservices.ambassade.domain.enumeration.State;
import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.repository.UserRepository;
import com.urservices.ambassade.security.SecurityUtils;
import com.urservices.ambassade.service.PaiementService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
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

    private final UserRepository userRepository;

    private final PaiementService paiementService;

    public VisaResource(VisaService visaService, PaiementService paiementService, UserRepository userRepository) {
        this.visaService = visaService;
        this.paiementService = paiementService;
        this.userRepository = userRepository;
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

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        visa.setCreatedBy(user);
        visa.setState(State.NOUVEAU);
        visa.setDateCreation(LocalDate.now());
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

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        visa.setModifiedBy(user);
        visa.setDateModification(LocalDate.now());
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
        String nom = webRequest.getParameter("nom") != null && !webRequest.getParameter("nom").isEmpty()
            ? webRequest.getParameter("nom") : null;
        String prenom = webRequest.getParameter("prenom") != null && !webRequest.getParameter("prenom").isEmpty()
            ? webRequest.getParameter("prenom") : null;
        String numeroPasseport = webRequest.getParameter("numeroPasseport") != null && !webRequest.getParameter("numeroPasseport").isEmpty()
            ? webRequest.getParameter("numeroPasseport") : null;
        Long numeroVisa = webRequest.getParameter("numeroVisa") != null && !webRequest.getParameter("numeroVisa").isEmpty()
            ? Long.valueOf(webRequest.getParameter("numeroVisa")) : null;
        Long typeService = webRequest.getParameter("typeService") != null && !webRequest.getParameter("typeService").isEmpty()
            ? Long.valueOf(webRequest.getParameter("typeService")) : null;
        Long categorie = webRequest.getParameter("categorie") != null && !webRequest.getParameter("categorie").isEmpty()
            ? Long.valueOf(webRequest.getParameter("categorie")) : null;

        String dateEmissionDebStr = webRequest.getParameter("dateEmission") != null && !webRequest.getParameter("dateEmission").isEmpty()
            ? webRequest.getParameter("dateEmission") : null;
        String dateEmissionFinStr = webRequest.getParameter("dateEmissionFin") != null && !webRequest.getParameter("dateEmissionFin").isEmpty()
            ? webRequest.getParameter("dateEmissionFin") : null;

        LocalDate dateEmissionDeb = dateEmissionDebStr != null ? LocalDate.parse(dateEmissionDebStr) : null;
        LocalDate dateEmissionFin = dateEmissionFinStr != null ? LocalDate.parse(dateEmissionFinStr) : null;

        Page<Visa> page = visaService.findAll(nom, prenom, numeroPasseport, numeroVisa, typeService, categorie,
            dateEmissionDeb, dateEmissionFin, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/visas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /visas/nouveau : get all the visas wirh State = NOUVEAU.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of visas in body
     */
    @GetMapping("/visas/nouveau")
    @Timed
    public ResponseEntity<List<Visa>> getAllVisasByStateNouveau(WebRequest webRequest, Pageable pageable) {

        log.debug("REST request to get Visas with state = NOUVEAU : {}", webRequest);

        String nom = webRequest.getParameter("nom") != null && !webRequest.getParameter("nom").isEmpty()
            ? webRequest.getParameter("nom") : null;
        String prenom = webRequest.getParameter("prenom") != null && !webRequest.getParameter("prenom").isEmpty()
            ? webRequest.getParameter("prenom") : null;
        String numeroPasseport = webRequest.getParameter("numeroPasseport") != null && !webRequest.getParameter("numeroPasseport").isEmpty()
            ? webRequest.getParameter("numeroPasseport") : null;
        Long numeroVisa = webRequest.getParameter("numeroVisa") != null && !webRequest.getParameter("numeroVisa").isEmpty()
            ? Long.valueOf(webRequest.getParameter("numeroVisa")) : null;
        Long typeService = webRequest.getParameter("typeService") != null && !webRequest.getParameter("typeService").isEmpty()
            ? Long.valueOf(webRequest.getParameter("typeService")) : null;
        Long categorie = webRequest.getParameter("categorie") != null && !webRequest.getParameter("categorie").isEmpty()
            ? Long.valueOf(webRequest.getParameter("categorie")) : null;

        String dateEmissionDebStr = webRequest.getParameter("dateEmission") != null && !webRequest.getParameter("dateEmission").isEmpty()
            ? webRequest.getParameter("dateEmission") : null;
        String dateEmissionFinStr = webRequest.getParameter("dateEmissionFin") != null && !webRequest.getParameter("dateEmissionFin").isEmpty()
            ? webRequest.getParameter("dateEmissionFin") : null;

        LocalDate dateEmissionDeb = dateEmissionDebStr != null ? LocalDate.parse(dateEmissionDebStr) : null;
        LocalDate dateEmissionFin = dateEmissionFinStr != null ? LocalDate.parse(dateEmissionFinStr) : null;

        Page<Visa> page = visaService.findAllNouveau(nom, prenom, numeroPasseport, numeroVisa, typeService, categorie,
            dateEmissionDeb, dateEmissionFin, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/visas/nouveau");
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
     * GET  /visas/:id/pret : the "id" visa to put ready
     *
     * @param id the id of the visa to put ready
     * @return the ResponseEntity with status 200 (OK) and with body the visa, or with status 404 (Not Found)
     */
    @GetMapping("/visas/{id}/pret")
    @Timed
    public ResponseEntity<Visa> getVisaPret(@PathVariable Long id) {
        log.debug("REST request to get Visa : {}", id);
        Visa visa = visaService.findOne(id);
        visa.setState(State.PRET);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        visa.setModifiedBy(user);
        visa.setDateModification(LocalDate.now());
        visa = visaService.save(visa);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(visa));
    }

    /**
     * GET  /visas/:id/retirer : the "id" visa to set remove
     *
     * @param id the id of the visa to set  remove
     * @return the ResponseEntity with status 200 (OK) and with body the visa, or with status 404 (Not Found)
     */
    @GetMapping("/visas/{id}/retirer")
    @Timed
    public ResponseEntity<Visa> setVisaRetirer(@PathVariable Long id) {
        log.debug("REST request to get Visa : {}", id);
        Visa visa = visaService.findOne(id);
        visa.setState(State.RETIRER);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        visa.setModifiedBy(user);
        visa.setDateModification(LocalDate.now());
        visa = visaService.save(visa);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(visa));
    }

    /**
     * GET  /visas/:id/payer : the "id" visa to pay
     *
     * @param id the id of the visa to pay
     * @return the ResponseEntity with status 200 (OK) and with body the visa, or with status 404 (Not Found)
     */
    @GetMapping("/visas/{id}/payer")
    @Timed
    public ResponseEntity<Visa> getVisaPayer(@PathVariable Long id) {
        log.debug("REST request to get Visa : {}", id);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        Visa visa = visaService.findOne(id);
        Paiement paiement = new Paiement();

        paiement.setDatePaiement(LocalDate.now());
        paiement.setVisa(visa);
        paiement.setTypeService(visa.getTypeService());
        paiement.setDateCreation(LocalDate.now());
        paiement.setUniteOrganisationelle(visa.getTypeService().getUniteOrganisationelle());
        paiement.setCreatedBy(user);
        paiementService.save(paiement);

        visa.setState(State.PAYE);
        visa.setModifiedBy(user);
        visa.setDateModification(LocalDate.now());
        visa = visaService.save(visa);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(visa));
    }

    /**
     * GET  /visas/:id/encours : the "id" visa to put in progress.
     *
     * @param id the id of the visa to put in progress
     * @return the ResponseEntity with status 200 (OK) and with body the visa, or with status 404 (Not Found)
     */
    @GetMapping("/visas/{id}/encours")
    @Timed
    public ResponseEntity<Visa> getVisaReady(@PathVariable Long id) {
        log.debug("REST request to get Visa Ready : {}", id);
        Visa visa = visaService.findOne(id);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        visa.setModifiedBy(user);
        visa.setState(State.ENCOURS);
        visa.setDateModification(LocalDate.now());
        visa = visaService.save(visa);
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

    public String getCurrentUserLogin() {
        org.springframework.security.core.context.SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                login = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                login = (String) authentication.getPrincipal();

        return login;
    }
}
