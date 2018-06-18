package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeService.
 */
@Entity
@Table(name = "type_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Min(value = 0L)
    @Column(name = "montant")
    private Long montant;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne(optional = false)
    @NotNull
    private UniteOrganisationelle uniteOrganisationelle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public TypeService nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getMontant() {
        return montant;
    }

    public TypeService montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public TypeService deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public UniteOrganisationelle getUniteOrganisationelle() {
        return uniteOrganisationelle;
    }

    public TypeService uniteOrganisationelle(UniteOrganisationelle uniteOrganisationelle) {
        this.uniteOrganisationelle = uniteOrganisationelle;
        return this;
    }

    public void setUniteOrganisationelle(UniteOrganisationelle uniteOrganisationelle) {
        this.uniteOrganisationelle = uniteOrganisationelle;
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
        TypeService typeService = (TypeService) o;
        if (typeService.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeService.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeService{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", montant=" + getMontant() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
