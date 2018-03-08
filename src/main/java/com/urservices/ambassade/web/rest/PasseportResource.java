package com.urservices.ambassade.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.urservices.ambassade.domain.Passeport;
import com.urservices.ambassade.domain.enumeration.Statut;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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

    public PasseportResource(PasseportService passeportService) {
        this.passeportService = passeportService;
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
    public ResponseEntity<List<Passeport>> getAllPasseports(WebRequest webRequest,Pageable pageable) {
        log.debug("REST request to get a page of Passeports");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nom = webRequest.getParameter("nom") !=null ? webRequest.getParameter("nom"):"";
        String prenom = webRequest.getParameter("prenom") !=null ? webRequest.getParameter("prenom"):"";
        String numeroPasseport = webRequest.getParameter("numeroPasseport") !=null ? webRequest.getParameter("numeroPasseport"):"";
        String neLe = webRequest.getParameter("neLe") !=null ? webRequest.getParameter("neLe"):"";
        String lieuNaissance = webRequest.getParameter("lieuNaissance") !=null ? webRequest.getParameter("lieuNaissance"):"";
        List<Statut> etatCivils  = webRequest.getParameter("etatCivil") !=null  && !webRequest.getParameter("etatCivil").isEmpty() ?
            Arrays.asList(Statut.valueOf(webRequest.getParameter("etatCivil"))):Arrays.asList(Statut.values());
        String adresse = webRequest.getParameter("adresse") !=null ? webRequest.getParameter("adresse"):"";
        String telephone = webRequest.getParameter("telephone") !=null ? webRequest.getParameter("telephone"):"";
        String nif = webRequest.getParameter("nif") !=null ? webRequest.getParameter("nif"):"";
        String paysEmetteur = webRequest.getParameter("paysEmetteur") !=null ? webRequest.getParameter("paysEmetteur"):"";
        BigDecimal montant =  webRequest.getParameter("montant") !=null && webRequest.getParameter("montant") !="" ?
            new BigDecimal(webRequest.getParameter("montant")):new BigDecimal(0.0);
        String remarques = webRequest.getParameter("remarques") !=null ? webRequest.getParameter("remarques"):"";

        String soumisLeDebStr = webRequest.getParameter("soumisLeDeb") !=null ? webRequest.getParameter("soumisLeDeb"):"1970-01-01";
        String delivreLeDebStr = webRequest.getParameter("delivreLeDeb") !=null ? webRequest.getParameter("delivreLeDeb"):"1970-01-01";
        String dateEmissionDebStr = webRequest.getParameter("dateEmissionDeb") !=null ? webRequest.getParameter("dateEmissionDeb"):"1970-01-01";
        String dateExpirationDebStr= webRequest.getParameter("dateExpirationDeb") !=null ? webRequest.getParameter("dateExpirationDeb"):"1970-01-01";

        String soumisLeFinStr = webRequest.getParameter("soumisLeFin") !=null ? webRequest.getParameter("soumisLeFin"): LocalDate.now().toString();
        String delivreLeFinStr = webRequest.getParameter("delivreLeFin") !=null ? webRequest.getParameter("delivreLeFin"):LocalDate.now().toString();
        String dateEmissionFinStr = webRequest.getParameter("dateEmissionFin") !=null ? webRequest.getParameter("dateEmissionFin"):LocalDate.now().toString();
        String dateExpirationFinStr= webRequest.getParameter("dateExpirationFin") !=null ? webRequest.getParameter("dateExpirationFin"):LocalDate.now().toString();

        LocalDate soumisLeDeb = LocalDate.parse(soumisLeDebStr,formatter);
        LocalDate delivreLeDeb = LocalDate.parse(delivreLeDebStr,formatter);
        LocalDate dateEmissionDeb = LocalDate.parse(dateEmissionDebStr,formatter);
        LocalDate dateExpirationDeb = LocalDate.parse(dateExpirationDebStr,formatter);

        LocalDate soumisLeFin = LocalDate.parse(soumisLeFinStr,formatter);
        LocalDate delivreLeFin = LocalDate.parse(delivreLeFinStr,formatter);
        LocalDate dateEmissionFin = LocalDate.parse(dateEmissionFinStr,formatter);
        LocalDate dateExpirationFin = LocalDate.parse(dateExpirationFinStr,formatter);

        System.out.println("soumisLeDeb = "+soumisLeDeb);
        System.out.println("delivreLeDeb = "+delivreLeDeb);
        System.out.println("dateEmissionDeb = "+dateEmissionDeb);
        System.out.println("dateExpirationDeb = "+dateExpirationDeb);

        System.out.println("soumisLeFin = "+soumisLeFin);
        System.out.println("delivreLeFin = "+delivreLeFin);
        System.out.println("dateEmissionFin = "+dateEmissionFin);
        System.out.println("dateExpirationFin = "+dateExpirationFin);


        String remarquesR = webRequest.getParameter("remarquesR") !=null ? webRequest.getParameter("remarquesR"):"";
        String sms = webRequest.getParameter("sms") !=null ? webRequest.getParameter("sms"):"";
        String sms2 = webRequest.getParameter("sms2") !=null ? webRequest.getParameter("sms2"):"";
        String documents = webRequest.getParameter("documents") !=null ? webRequest.getParameter("documents"):"";

//        Page<Passeport> page = passeportService.findAll(pageable);
        Page<Passeport> page = passeportService.searchAll(nom,prenom,numeroPasseport,neLe,lieuNaissance,etatCivils,
            adresse,telephone,nif,paysEmetteur,soumisLeDeb, soumisLeFin, delivreLeDeb, delivreLeFin, montant,remarques,
            dateEmissionDeb, dateEmissionFin, dateExpirationDeb, dateExpirationFin, remarquesR,sms,sms2,documents,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/passeports");
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
}
