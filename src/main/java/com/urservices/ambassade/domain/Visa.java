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

    @Column(name = "profession")
    private String profession;

    @Column(name = "nombre_de_jour")
    private Integer nombreDeJour;

    @Lob
    @Column(name = "photo_demandeur_visa")
    private byte[] photoDemandeurVisa;

    @Column(name = "photo_demandeur_visa_content_type")
    private String photoDemandeurVisaContentType;

    @Column(name = "email")
    private String email;

    @Column(name = "adresse_de_sejour")
    private String adresseDeSejour;

    @Column(name = "nom_employeur")
    private String nomEmployeur;

    @Column(name = "adresse_employeur")
    private String adresseEmployeur;

    @Column(name = "telephone_employeur")
    private String telephoneEmployeur;

    @Column(name = "email_employeur")
    private String emailEmployeur;

    @ManyToOne
    private TypeService typeService;

    @ManyToOne(optional = false)
    @NotNull
    private TypeEntree typeEntree;

    @ManyToOne
    private Categorie categorie;

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

    public String getProfession() {
        return profession;
    }

    public Visa profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getNombreDeJour() {
        return nombreDeJour;
    }

    public Visa nombreDeJour(Integer nombreDeJour) {
        this.nombreDeJour = nombreDeJour;
        return this;
    }

    public void setNombreDeJour(Integer nombreDeJour) {
        this.nombreDeJour = nombreDeJour;
    }

    public byte[] getPhotoDemandeurVisa() {
        return photoDemandeurVisa;
    }

    public Visa photoDemandeurVisa(byte[] photoDemandeurVisa) {
        this.photoDemandeurVisa = photoDemandeurVisa;
        return this;
    }

    public void setPhotoDemandeurVisa(byte[] photoDemandeurVisa) {
        this.photoDemandeurVisa = photoDemandeurVisa;
    }

    public String getPhotoDemandeurVisaContentType() {
        return photoDemandeurVisaContentType;
    }

    public Visa photoDemandeurVisaContentType(String photoDemandeurVisaContentType) {
        this.photoDemandeurVisaContentType = photoDemandeurVisaContentType;
        return this;
    }

    public void setPhotoDemandeurVisaContentType(String photoDemandeurVisaContentType) {
        this.photoDemandeurVisaContentType = photoDemandeurVisaContentType;
    }

    public String getEmail() {
        return email;
    }

    public Visa email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresseDeSejour() {
        return adresseDeSejour;
    }

    public Visa adresseDeSejour(String adresseDeSejour) {
        this.adresseDeSejour = adresseDeSejour;
        return this;
    }

    public void setAdresseDeSejour(String adresseDeSejour) {
        this.adresseDeSejour = adresseDeSejour;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public Visa nomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
        return this;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getAdresseEmployeur() {
        return adresseEmployeur;
    }

    public Visa adresseEmployeur(String adresseEmployeur) {
        this.adresseEmployeur = adresseEmployeur;
        return this;
    }

    public void setAdresseEmployeur(String adresseEmployeur) {
        this.adresseEmployeur = adresseEmployeur;
    }

    public String getTelephoneEmployeur() {
        return telephoneEmployeur;
    }

    public Visa telephoneEmployeur(String telephoneEmployeur) {
        this.telephoneEmployeur = telephoneEmployeur;
        return this;
    }

    public void setTelephoneEmployeur(String telephoneEmployeur) {
        this.telephoneEmployeur = telephoneEmployeur;
    }

    public String getEmailEmployeur() {
        return emailEmployeur;
    }

    public Visa emailEmployeur(String emailEmployeur) {
        this.emailEmployeur = emailEmployeur;
        return this;
    }

    public void setEmailEmployeur(String emailEmployeur) {
        this.emailEmployeur = emailEmployeur;
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

    public TypeEntree getTypeEntree() {
        return typeEntree;
    }

    public Visa typeEntree(TypeEntree typeEntree) {
        this.typeEntree = typeEntree;
        return this;
    }

    public void setTypeEntree(TypeEntree typeEntree) {
        this.typeEntree = typeEntree;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Visa categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
            ", taxes=" + getTaxes() +
            ", adresse='" + getAdresse() + "'" +
            ", remarques='" + getRemarques() + "'" +
            ", state='" + getState() + "'" +
            ", profession='" + getProfession() + "'" +
            ", nombreDeJour=" + getNombreDeJour() +
            ", photoDemandeurVisa='" + getPhotoDemandeurVisa() + "'" +
            ", photoDemandeurVisaContentType='" + getPhotoDemandeurVisaContentType() + "'" +
            ", email='" + getEmail() + "'" +
            ", adresseDeSejour='" + getAdresseDeSejour() + "'" +
            ", nomEmployeur='" + getNomEmployeur() + "'" +
            ", adresseEmployeur='" + getAdresseEmployeur() + "'" +
            ", telephoneEmployeur='" + getTelephoneEmployeur() + "'" +
            ", emailEmployeur='" + getEmailEmployeur() + "'" +
            "}";
    }
}
