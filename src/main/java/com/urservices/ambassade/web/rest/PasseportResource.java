package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Paiement;
import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.User;
import com.urservices.ambassade.domain.enumeration.State;
import com.urservices.ambassade.domain.enumeration.Statut;
import com.urservices.ambassade.repository.UserRepository;
import com.urservices.ambassade.service.PaiementService;
import com.urservices.ambassade.service.PasseportService;
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
import org.springframework.security.core.context.SecurityContext;
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
 * REST controller for managing Passeport.
 */
@RestController
@RequestMapping("/api")
public class PasseportResource {

    private final Logger log = LoggerFactory.getLogger(PasseportResource.class);

    private static final String ENTITY_NAME = "passeport";

    private final PasseportService passeportService;

    private final UserRepository userRepository;


    private final PaiementService paiementService;

    public PasseportResource(PasseportService passeportService, PaiementService paiementService, UserRepository userRepository) {
        this.passeportService = passeportService;
        this.paiementService = paiementService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /passeports : Create a new passeport.
     *
     * @param passeport the passeport to create
     * @return the ResponseEntity with status 201 (Created) and with body the new passeport, or with status 400 (Bad Request) if the passeport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/passeports")
    @Timed
    public ResponseEntity<Passeport> createPasseport(@Valid @RequestBody Passeport passeport) throws URISyntaxException {
        log.debug("REST request to save Passeport : {}", passeport);
        if (passeport.getId() != null) {
            throw new BadRequestAlertException("A new passeport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setCreatedBy(user);
        passeport.setState(State.NOUVEAU);
        passeport.setDateCreation(LocalDate.now());
        Passeport result = passeportService.save(passeport);
        return ResponseEntity.created(new URI("/api/passeports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /passeports : Updates an existing passeport.
     *
     * @param passeport the passeport to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated passeport,
     * or with status 400 (Bad Request) if the passeport is not valid,
     * or with status 500 (Internal Server Error) if the passeport couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/passeports")
    @Timed
    public ResponseEntity<Passeport> updatePasseport(@Valid @RequestBody Passeport passeport) throws URISyntaxException {
        log.debug("REST request to update Passeport : {}", passeport);
        if (passeport.getId() == null) {
            return createPasseport(passeport);
        }

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setModifiedBy(user);
        passeport.setDateModification(LocalDate.now());
        Passeport result = passeportService.save(passeport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, passeport.getId().toString()))
            .body(result);
    }

    /**
     * GET  /passeports : get all the passeports.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of passeports in body
     */
    @GetMapping("/passeports")
    @Timed
    public ResponseEntity<List<Passeport>> getAllPasseports(WebRequest webRequest,  Pageable pageable) {

        String nom = webRequest.getParameter("nom") !=null && !webRequest.getParameter("nom").isEmpty()
            ? webRequest.getParameter("nom"): null;
        String recu = webRequest.getParameter("recu") !=null && !webRequest.getParameter("recu").isEmpty()
            ? webRequest.getParameter("recu"): null;
        String prenom = webRequest.getParameter("prenom") !=null && !webRequest.getParameter("prenom").isEmpty()
            ? webRequest.getParameter("prenom"): null;
        String numeroPasseport = webRequest.getParameter("numeroPasseport") !=null && !webRequest.getParameter("numeroPasseport").isEmpty()
            ? webRequest.getParameter("numeroPasseport"): null;
        Statut etatCivil  = webRequest.getParameter("etatCivil") !=null  && !webRequest.getParameter("etatCivil").isEmpty() ?
            Statut.valueOf(webRequest.getParameter("etatCivil")) : null;
        String delivreLeDebStr = webRequest.getParameter("delivreLe") !=null && !webRequest.getParameter("delivreLe").isEmpty()
            ? webRequest.getParameter("delivreLe"): null;
        String delivreLeFinStr = webRequest.getParameter("delivreLeFin") !=null && !webRequest.getParameter("delivreLeFin").isEmpty()
            ? webRequest.getParameter("delivreLeFin"): null;

        LocalDate delivreLeDeb = delivreLeDebStr != null ? LocalDate.parse(delivreLeDebStr) : null;
        LocalDate delivreLeFin = delivreLeFinStr != null ? LocalDate.parse(delivreLeFinStr) : null;

        Page<Passeport> page = passeportService.searchAll(recu, nom,prenom,numeroPasseport,etatCivil, delivreLeDeb,
            delivreLeFin, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/passeports");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /passeports/nouveau : get all the passeports with State=NOUVEAU.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of passeports in body
     */
    @GetMapping("/passeports/nouveau")
    @Timed
    public ResponseEntity<List<Passeport>> getAllPasseportsOfStateNouveau(WebRequest webRequest,  Pageable pageable) {

        log.debug("REST request to get Passeport with state = NOUVEAU : {}", webRequest);
        String nom = webRequest.getParameter("nom") !=null && !webRequest.getParameter("nom").isEmpty()
            ? webRequest.getParameter("nom"): null;
        String recu = webRequest.getParameter("recu") !=null && !webRequest.getParameter("recu").isEmpty()
            ? webRequest.getParameter("recu"): null;
        String prenom = webRequest.getParameter("prenom") !=null && !webRequest.getParameter("prenom").isEmpty()
            ? webRequest.getParameter("prenom"): null;
        String numeroPasseport = webRequest.getParameter("numeroPasseport") !=null && !webRequest.getParameter("numeroPasseport").isEmpty()
            ? webRequest.getParameter("numeroPasseport"): null;
        Statut etatCivil  = webRequest.getParameter("etatCivil") !=null  && !webRequest.getParameter("etatCivil").isEmpty() ?
            Statut.valueOf(webRequest.getParameter("etatCivil")) : null;
        String delivreLeDebStr = webRequest.getParameter("delivreLe") !=null && !webRequest.getParameter("delivreLe").isEmpty()
            ? webRequest.getParameter("delivreLe"): null;
        String delivreLeFinStr = webRequest.getParameter("delivreLeFin") !=null && !webRequest.getParameter("delivreLeFin").isEmpty()
            ? webRequest.getParameter("delivreLeFin"): null;

        LocalDate delivreLeDeb = delivreLeDebStr != null ? LocalDate.parse(delivreLeDebStr) : null;
        LocalDate delivreLeFin = delivreLeFinStr != null ? LocalDate.parse(delivreLeFinStr) : null;

        Page<Passeport> page = passeportService.searchAllNouveau(recu, nom,prenom,numeroPasseport,etatCivil, delivreLeDeb,
            delivreLeFin, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/passeports/nouveau");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /passeports/:id : get the "id" passeport.
     *
     * @param id the id of the passeport to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the passeport, or with status 404 (Not Found)
     */
    @GetMapping("/passeports/{id}")
    @Timed
    public ResponseEntity<Passeport> getPasseport(@PathVariable Long id) {
        log.debug("REST request to get Passeport : {}", id);
        Passeport passeport = passeportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passeport));
    }

    /**
     * GET  /passeports/:id/pret : the "id" passeport to put ready
     *
     * @param id the id of the passeport to put ready
     * @return the ResponseEntity with status 200 (OK) and with body the passeport, or with status 404 (Not Found)
     */
    @GetMapping("/passeports/{id}/pret")
    @Timed
    public ResponseEntity<Passeport> getPasseportPret(@PathVariable Long id) {
        log.debug("REST request to get Passeport : {}", id);
        Passeport passeport = passeportService.findOne(id);
        passeport.setState(State.PRET);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setModifiedBy(user);
        passeport.setDateModification(LocalDate.now());
        passeport = passeportService.save(passeport);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passeport));
    }

    /**
     * GET  /passeports/:id/retirer : the "id" passeport to set remove
     *
     * @param id the id of the passeport to set  remove
     * @return the ResponseEntity with status 200 (OK) and with body the passeport, or with status 404 (Not Found)
     */
    @GetMapping("/passeports/{id}/retirer")
    @Timed
    public ResponseEntity<Passeport> setPasseportRetirer(@PathVariable Long id) {
        log.debug("REST request to get Passeport : {}", id);
        Passeport passeport = passeportService.findOne(id);
        passeport.setState(State.RETIRER);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setModifiedBy(user);
        passeport.setDateModification(LocalDate.now());
        passeport = passeportService.save(passeport);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passeport));
    }

    /**
     * GET  /passeports/:id/payer : the "id" passeport to pay
     *
     * @param id the id of the passeport to pay
     * @return the ResponseEntity with status 200 (OK) and with body the passeport, or with status 404 (Not Found)
     */
    @GetMapping("/passeports/{id}/payer")
    @Timed
    public ResponseEntity<Passeport> getPasseportPayer(@PathVariable Long id) {
        log.debug("REST request to get Passeport : {}", id);
        Passeport passeport = passeportService.findOne(id);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setModifiedBy(user);
        passeport.setDateModification(LocalDate.now());

        Paiement paiement = new Paiement();
        paiement.setDatePaiement(LocalDate.now());
        paiement.setPasseport(passeport);
        paiement.setTypeService(passeport.getTypeService());
        paiement.setUniteOrganisationelle(passeport.getTypeService().getUniteOrganisationelle());
        paiement.setCreatedBy(user);
        paiement.setDateCreation(LocalDate.now());
        paiementService.save(paiement);

        passeport.setState(State.PAYE);
        passeport = passeportService.save(passeport);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passeport));
    }

    /**
     * GET  /passeports/:id/encours : the "id" passeport to put in progress.
     *
     * @param id the id of the passeport to put in progress
     * @return the ResponseEntity with status 200 (OK) and with body the passeport, or with status 404 (Not Found)
     */
    @GetMapping("/passeports/{id}/encours")
    @Timed
    public ResponseEntity<Passeport> getPasseportReady(@PathVariable Long id) {
        log.debug("REST request to get Passeport Ready : {}", id);
        Passeport passeport = passeportService.findOne(id);
        passeport.setState(State.ENCOURS);

        User user = userRepository.findOneByLogin(getCurrentUserLogin()).get();
        passeport.setModifiedBy(user);
        passeport.setDateModification(LocalDate.now());
        passeport = passeportService.save(passeport);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(passeport));
    }

    /**
     * DELETE  /passeports/:id : delete the "id" passeport.
     *
     * @param id the id of the passeport to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/passeports/{id}")
    @Timed
    public ResponseEntity<Void> deletePasseport(@PathVariable Long id) {
        log.debug("REST request to delete Passeport : {}", id);
        passeportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public final String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
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
