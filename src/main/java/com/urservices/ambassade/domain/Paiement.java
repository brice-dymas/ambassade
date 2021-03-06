package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EntityListeners({AuditingEntityListener.class})
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "numero_paiement")
    private String numeroPaiement;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @ManyToOne
    private Visa visa;

    @ManyToOne
    private Passeport passeport;

    @ManyToOne
    private TypeService typeService;

    @ManyToOne
    private User user;

    @ManyToOne
    private UniteOrganisationelle uniteOrganisationelle;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User modifiedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public Paiement datePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
        return this;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getNumeroPaiement() {
        return numeroPaiement;
    }

    public Paiement numeroPaiement(String numeroPaiement) {
        this.numeroPaiement = numeroPaiement;
        return this;
    }

    public void setNumeroPaiement(String numeroPaiement) {
        this.numeroPaiement = numeroPaiement;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Paiement dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public Paiement dateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public Visa getVisa() {
        return visa;
    }

    public Paiement visa(Visa visa) {
        this.visa = visa;
        return this;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public Passeport getPasseport() {
        return passeport;
    }

    public Paiement passeport(Passeport passeport) {
        this.passeport = passeport;
        return this;
    }

    public void setPasseport(Passeport passeport) {
        this.passeport = passeport;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public Paiement typeService(TypeService typeService) {
        this.typeService = typeService;
        return this;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public User getUser() {
        return user;
    }

    public Paiement user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UniteOrganisationelle getUniteOrganisationelle() {
        return uniteOrganisationelle;
    }

    public Paiement uniteOrganisationelle(UniteOrganisationelle uniteOrganisationelle) {
        this.uniteOrganisationelle = uniteOrganisationelle;
        return this;
    }

    public void setUniteOrganisationelle(UniteOrganisationelle uniteOrganisationelle) {
        this.uniteOrganisationelle = uniteOrganisationelle;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Paiement createdBy(User user) {
        this.createdBy = user;
        return this;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public Paiement modifiedBy(User user) {
        this.modifiedBy = user;
        return this;
    }

    public void setModifiedBy(User user) {
        this.modifiedBy = user;
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
        Paiement paiement = (Paiement) o;
        if (paiement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paiement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", datePaiement='" + getDatePaiement() + "'" +
            ", numeroPaiement='" + getNumeroPaiement() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }

    @PostPersist
    public void generateNumeroPaiement() {
        LocalDate localDate = LocalDate.now();
        String numeroPaiement = "";
        numeroPaiement += localDate.getYear();
        numeroPaiement += localDate.getMonthValue() < 10 ? "0" + localDate.getMonthValue() : localDate.getMonthValue();
        numeroPaiement += this.getId() < 10 ? "0" + this.getId() : this.getId();
        this.setNumeroPaiement(numeroPaiement);
    }
}
