package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Monnaie.
 */
@Entity
@Table(name = "monnaie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Monnaie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    @Column(name = "jhi_type", length = 40)
    private String type;

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

    public String getType() {
        return type;
    }

    public Monnaie type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMontant() {
        return montant;
    }

    public Monnaie montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public String getProduit() {
        return produit;
    }

    public Monnaie produit(String produit) {
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
        Monnaie monnaie = (Monnaie) o;
        if (monnaie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monnaie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Monnaie{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", montant=" + getMontant() +
            ", produit='" + getProduit() + "'" +
            "}";
    }
}
