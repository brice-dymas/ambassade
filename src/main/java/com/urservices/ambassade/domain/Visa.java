package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.urservices.ambassade.domain.enumeration.State;

/**
 * A Visa.
 */
@Entity
@Table(name = "visa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Visa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    @Column(name = "nom", length = 60)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Size(max = 50)
    @Column(name = "nationalite", length = 50)
    private String nationalite;

    @Size(max = 30)
    @Column(name = "numero_passeport", length = 30)
    private String numeroPasseport;

    @Size(max = 30)
    @Column(name = "cedula", length = 30)
    private String cedula;

    @Column(name = "numero_visa")
    private Long numeroVisa;

    @Column(name = "date_emission")
    private LocalDate dateEmission;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Size(max = 20)
    @Column(name = "nombre_entree", length = 20)
    private String nombreEntree;

    @Size(max = 20)
    @Column(name = "jhi_type", length = 20)
    private String type;

    @Size(max = 25)
    @Column(name = "categorie", length = 25)
    private String categorie;

    @Column(name = "taxes")
    private Integer taxes;

    @Size(max = 30)
    @Column(name = "adresse", length = 30)
    private String adresse;

    @Column(name = "remarques")
    private String remarques;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @ManyToOne
    private TypeService typeService;

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

    public Visa nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Visa prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Visa nationalite(String nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getNumeroPasseport() {
        return numeroPasseport;
    }

    public Visa numeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
        return this;
    }

    public void setNumeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
    }

    public String getCedula() {
        return cedula;
    }

    public Visa cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Long getNumeroVisa() {
        return numeroVisa;
    }

    public Visa numeroVisa(Long numeroVisa) {
        this.numeroVisa = numeroVisa;
        return this;
    }

    public void setNumeroVisa(Long numeroVisa) {
        this.numeroVisa = numeroVisa;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public Visa dateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
        return this;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public Visa dateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
        return this;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getNombreEntree() {
        return nombreEntree;
    }

    public Visa nombreEntree(String nombreEntree) {
        this.nombreEntree = nombreEntree;
        return this;
    }

    public void setNombreEntree(String nombreEntree) {
        this.nombreEntree = nombreEntree;
    }

    public String getType() {
        return type;
    }

    public Visa type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public Visa categorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getTaxes() {
        return taxes;
    }

    public Visa taxes(Integer taxes) {
        this.taxes = taxes;
        return this;
    }

    public void setTaxes(Integer taxes) {
        this.taxes = taxes;
    }

    public String getAdresse() {
        return adresse;
    }

    public Visa adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRemarques() {
        return remarques;
    }

    public Visa remarques(String remarques) {
        this.remarques = remarques;
        return this;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public State getState() {
        return state;
    }

    public Visa state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public Visa typeService(TypeService typeService) {
        this.typeService = typeService;
        return this;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
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
        Visa visa = (Visa) o;
        if (visa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), visa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Visa{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            ", numeroPasseport='" + getNumeroPasseport() + "'" +
            ", cedula='" + getCedula() + "'" +
            ", numeroVisa=" + getNumeroVisa() +
            ", dateEmission='" + getDateEmission() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            ", nombreEntree='" + getNombreEntree() + "'" +
            ", type='" + getType() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", taxes=" + getTaxes() +
            ", adresse='" + getAdresse() + "'" +
            ", remarques='" + getRemarques() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
