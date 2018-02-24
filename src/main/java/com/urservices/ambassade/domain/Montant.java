package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Montant.
 */
@Entity
@Table(name = "montant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Montant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "monnaie", length = 50)
    private String monnaie;

    @Min(value = 0L)
    @Column(name = "montant")
    private Long montant;

    @Size(max = 40)
    @Column(name = "produit", length = 40)
    private String produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public Montant monnaie(String monnaie) {
        this.monnaie = monnaie;
        return this;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public Long getMontant() {
        return montant;
    }

    public Montant montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public String getProduit() {
        return produit;
    }

    public Montant produit(String produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(String produit) {
        this.produit = produit;
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
        Montant montant = (Montant) o;
        if (montant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), montant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Montant{" +
            "id=" + getId() +
            ", monnaie='" + getMonnaie() + "'" +
            ", montant=" + getMontant() +
            ", produit='" + getProduit() + "'" +
            "}";
    }
}
