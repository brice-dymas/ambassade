package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.urservices.ambassade.domain.enumeration.Sexe;

/**
 * A Rapatriement.
 */
@Entity
@Table(name = "rapatriement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rapatriement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @Size(max = 50)
    @Column(name = "numero_dossier", length = 50)
    private String numeroDossier;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Size(max = 50)
    @Column(name = "document_id", length = 50)
    private String documentID;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Size(max = 50)
    @Column(name = "motif", length = 50)
    private String motif;

    @Column(name = "date_rapatriement")
    private LocalDate dateRapatriement;

    @Size(max = 50)
    @Column(name = "frontiere", length = 50)
    private String frontiere;

    @Lob
    @Column(name = "document_scanne")
    private byte[] documentScanne;

    @Column(name = "document_scanne_content_type")
    private String documentScanneContentType;

    @Column(name = "created_by_php_runner")
    private Integer createdByPHPRunner;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public Rapatriement reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public Rapatriement numeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
        return this;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getNom() {
        return nom;
    }

    public Rapatriement nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Rapatriement prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Rapatriement dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDocumentID() {
        return documentID;
    }

    public Rapatriement documentID(String documentID) {
        this.documentID = documentID;
        return this;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public Rapatriement sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getMotif() {
        return motif;
    }

    public Rapatriement motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDate getDateRapatriement() {
        return dateRapatriement;
    }

    public Rapatriement dateRapatriement(LocalDate dateRapatriement) {
        this.dateRapatriement = dateRapatriement;
        return this;
    }

    public void setDateRapatriement(LocalDate dateRapatriement) {
        this.dateRapatriement = dateRapatriement;
    }

    public String getFrontiere() {
        return frontiere;
    }

    public Rapatriement frontiere(String frontiere) {
        this.frontiere = frontiere;
        return this;
    }

    public void setFrontiere(String frontiere) {
        this.frontiere = frontiere;
    }

    public byte[] getDocumentScanne() {
        return documentScanne;
    }

    public Rapatriement documentScanne(byte[] documentScanne) {
        this.documentScanne = documentScanne;
        return this;
    }

    public void setDocumentScanne(byte[] documentScanne) {
        this.documentScanne = documentScanne;
    }

    public String getDocumentScanneContentType() {
        return documentScanneContentType;
    }

    public Rapatriement documentScanneContentType(String documentScanneContentType) {
        this.documentScanneContentType = documentScanneContentType;
        return this;
    }

    public void setDocumentScanneContentType(String documentScanneContentType) {
        this.documentScanneContentType = documentScanneContentType;
    }

    public Integer getCreatedByPHPRunner() {
        return createdByPHPRunner;
    }

    public Rapatriement createdByPHPRunner(Integer createdByPHPRunner) {
        this.createdByPHPRunner = createdByPHPRunner;
        return this;
    }

    public void setCreatedByPHPRunner(Integer createdByPHPRunner) {
        this.createdByPHPRunner = createdByPHPRunner;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rapatriement rapatriement = (Rapatriement) o;
        if (rapatriement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rapatriement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Rapatriement{" +
            "id=" + getId() +
            ", reference=" + getReference() +
            ", numeroDossier='" + getNumeroDossier() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", documentID='" + getDocumentID() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", motif='" + getMotif() + "'" +
            ", dateRapatriement='" + getDateRapatriement() + "'" +
            ", frontiere='" + getFrontiere() + "'" +
            ", documentScanne='" + getDocumentScanne() + "'" +
            ", documentScanneContentType='" + getDocumentScanneContentType() + "'" +
            ", createdByPHPRunner=" + getCreatedByPHPRunner() +
            "}";
    }
}
