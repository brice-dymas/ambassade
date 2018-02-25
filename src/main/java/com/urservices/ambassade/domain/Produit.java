package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nom_produit", length = 50)
    private String nomProduit;

    @Size(max = 50)
    @Column(name = "monnaie", length = 50)
    private String monnaie;

    @Min(value = 0L)
    @Column(name = "montant")
    private Long montant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public Produit nomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
        return this;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public Produit monnaie(String monnaie) {
        this.monnaie = monnaie;
        return this;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public Long getMontant() {
        return montant;
    }

    public Produit montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
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
        Produit produit = (Produit) o;
        if (produit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", nomProduit='" + getNomProduit() + "'" +
            ", monnaie='" + getMonnaie() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}
