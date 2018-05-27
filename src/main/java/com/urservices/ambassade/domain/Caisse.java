package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Caisse.
 */
@Entity
@Table(name = "caisse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Caisse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "date_du_jour")
    private LocalDate dateDuJour;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Size(max = 50)
    @Column(name = "type_id", length = 50)
    private String typeID;

    @Size(max = 50)
    @Column(name = "numero_id", length = 50)
    private String numeroID;

    @Size(max = 50)
    @Column(name = "service_concerne", length = 50)
    private String serviceConcerne;

    @Size(max = 50)
    @Column(name = "monnaie", length = 50)
    private String monnaie;

    @Column(name = "montant", precision=10, scale=2)
    private BigDecimal montant;

    @Column(name = "date_retour")
    private LocalDate dateRetour;

    @Size(max = 50)
    @Column(name = "telephone", length = 50)
    private String telephone;

    @NotNull
    @Column(name = "num", nullable = false)
    private Integer num;

    @Size(max = 50)
    @Column(name = "paiement", length = 50)
    private String paiement;

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

    public Caisse reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateDuJour() {
        return dateDuJour;
    }

    public Caisse dateDuJour(LocalDate dateDuJour) {
        this.dateDuJour = dateDuJour;
        return this;
    }

    public void setDateDuJour(LocalDate dateDuJour) {
        this.dateDuJour = dateDuJour;
    }

    public String getNom() {
        return nom;
    }

    public Caisse nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Caisse prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTypeID() {
        return typeID;
    }

    public Caisse typeID(String typeID) {
        this.typeID = typeID;
        return this;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getNumeroID() {
        return numeroID;
    }

    public Caisse numeroID(String numeroID) {
        this.numeroID = numeroID;
        return this;
    }

    public void setNumeroID(String numeroID) {
        this.numeroID = numeroID;
    }

    public String getServiceConcerne() {
        return serviceConcerne;
    }

    public Caisse serviceConcerne(String serviceConcerne) {
        this.serviceConcerne = serviceConcerne;
        return this;
    }

    public void setServiceConcerne(String serviceConcerne) {
        this.serviceConcerne = serviceConcerne;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public Caisse monnaie(String monnaie) {
        this.monnaie = monnaie;
        return this;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Caisse montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public Caisse dateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
        return this;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getTelephone() {
        return telephone;
    }

    public Caisse telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getNum() {
        return num;
    }

    public Caisse num(Integer num) {
        this.num = num;
        return this;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPaiement() {
        return paiement;
    }

    public Caisse paiement(String paiement) {
        this.paiement = paiement;
        return this;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
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
        Caisse caisse = (Caisse) o;
        if (caisse.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caisse.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Caisse{" +
            "id=" + getId() +
            ", reference=" + getReference() +
            ", dateDuJour='" + getDateDuJour() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", typeID='" + getTypeID() + "'" +
            ", numeroID='" + getNumeroID() + "'" +
            ", serviceConcerne='" + getServiceConcerne() + "'" +
            ", monnaie='" + getMonnaie() + "'" +
            ", montant=" + getMontant() +
            ", dateRetour='" + getDateRetour() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", num=" + getNum() +
            ", paiement='" + getPaiement() + "'" +
            "}";
    }
}
