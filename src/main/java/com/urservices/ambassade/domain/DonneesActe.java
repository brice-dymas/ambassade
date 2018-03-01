package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.urservices.ambassade.domain.enumeration.Sexe;

import com.urservices.ambassade.domain.enumeration.Statut;

/**
 * A DonneesActe.
 */
@Entity
@Table(name = "donnees_acte")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonneesActe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10)
    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "date_du_jour_chiffre")
    private ZonedDateTime dateDuJourChiffre;

    @Size(max = 50)
    @Column(name = "registre_special_rd", length = 50)
    private String registreSpecialRD;

    @Size(max = 40)
    @Column(name = "nom_enfant", length = 40)
    private String nomEnfant;

    @Size(max = 10)
    @Column(name = "registre", length = 10)
    private String registre;

    @Min(value = 1900)
    @Column(name = "annee")
    private Integer annee;

    @Size(max = 15)
    @Column(name = "numero", length = 15)
    private String numero;

    @Column(name = "date_naissance_chiffre")
    private ZonedDateTime dateNaissanceChiffre;

    @Size(max = 20)
    @Column(name = "nom_pere", length = 20)
    private String nomPere;

    @Size(max = 40)
    @Column(name = "prenom_pere", length = 40)
    private String prenomPere;

    @Size(max = 20)
    @Column(name = "nom_mere", length = 20)
    private String nomMere;

    @Size(max = 40)
    @Column(name = "prenom_mere", length = 40)
    private String prenomMere;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Sexe sexe;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @Size(max = 35)
    @Column(name = "ville_naissance", length = 35)
    private String villeNaissance;

    @Size(max = 150)
    @Column(name = "adresse_pere", length = 150)
    private String adressePere;

    @Size(max = 150)
    @Column(name = "adresse_mere", length = 150)
    private String adresseMere;

    @Size(max = 35)
    @Column(name = "temoins_1", length = 35)
    private String temoins1;

    @Size(max = 35)
    @Column(name = "temoins_2", length = 35)
    private String temoins2;

    @Size(max = 35)
    @Column(name = "id_pere", length = 35)
    private String idPere;

    @Size(max = 35)
    @Column(name = "id_mere", length = 35)
    private String idMere;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Size(max = 40)
    @Column(name = "juridiction", length = 40)
    private String juridiction;

    @Size(max = 50)
    @Column(name = "livre", length = 50)
    private String livre;

    @Size(max = 50)
    @Column(name = "notes", length = 50)
    private String notes;

    @Size(max = 50)
    @Column(name = "feuille", length = 50)
    private String feuille;

    @Size(max = 50)
    @Column(name = "acte", length = 50)
    private String acte;

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

    public DonneesActe reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ZonedDateTime getDateDuJourChiffre() {
        return dateDuJourChiffre;
    }

    public DonneesActe dateDuJourChiffre(ZonedDateTime dateDuJourChiffre) {
        this.dateDuJourChiffre = dateDuJourChiffre;
        return this;
    }

    public void setDateDuJourChiffre(ZonedDateTime dateDuJourChiffre) {
        this.dateDuJourChiffre = dateDuJourChiffre;
    }

    public String getRegistreSpecialRD() {
        return registreSpecialRD;
    }

    public DonneesActe registreSpecialRD(String registreSpecialRD) {
        this.registreSpecialRD = registreSpecialRD;
        return this;
    }

    public void setRegistreSpecialRD(String registreSpecialRD) {
        this.registreSpecialRD = registreSpecialRD;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public DonneesActe nomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
        return this;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getRegistre() {
        return registre;
    }

    public DonneesActe registre(String registre) {
        this.registre = registre;
        return this;
    }

    public void setRegistre(String registre) {
        this.registre = registre;
    }

    public Integer getAnnee() {
        return annee;
    }

    public DonneesActe annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getNumero() {
        return numero;
    }

    public DonneesActe numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ZonedDateTime getDateNaissanceChiffre() {
        return dateNaissanceChiffre;
    }

    public DonneesActe dateNaissanceChiffre(ZonedDateTime dateNaissanceChiffre) {
        this.dateNaissanceChiffre = dateNaissanceChiffre;
        return this;
    }

    public void setDateNaissanceChiffre(ZonedDateTime dateNaissanceChiffre) {
        this.dateNaissanceChiffre = dateNaissanceChiffre;
    }

    public String getNomPere() {
        return nomPere;
    }

    public DonneesActe nomPere(String nomPere) {
        this.nomPere = nomPere;
        return this;
    }

    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    public String getPrenomPere() {
        return prenomPere;
    }

    public DonneesActe prenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public DonneesActe nomMere(String nomMere) {
        this.nomMere = nomMere;
        return this;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getPrenomMere() {
        return prenomMere;
    }

    public DonneesActe prenomMere(String prenomMere) {
        this.prenomMere = prenomMere;
        return this;
    }

    public void setPrenomMere(String prenomMere) {
        this.prenomMere = prenomMere;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public DonneesActe sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Statut getStatut() {
        return statut;
    }

    public DonneesActe statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public DonneesActe villeNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
        return this;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getAdressePere() {
        return adressePere;
    }

    public DonneesActe adressePere(String adressePere) {
        this.adressePere = adressePere;
        return this;
    }

    public void setAdressePere(String adressePere) {
        this.adressePere = adressePere;
    }

    public String getAdresseMere() {
        return adresseMere;
    }

    public DonneesActe adresseMere(String adresseMere) {
        this.adresseMere = adresseMere;
        return this;
    }

    public void setAdresseMere(String adresseMere) {
        this.adresseMere = adresseMere;
    }

    public String getTemoins1() {
        return temoins1;
    }

    public DonneesActe temoins1(String temoins1) {
        this.temoins1 = temoins1;
        return this;
    }

    public void setTemoins1(String temoins1) {
        this.temoins1 = temoins1;
    }

    public String getTemoins2() {
        return temoins2;
    }

    public DonneesActe temoins2(String temoins2) {
        this.temoins2 = temoins2;
        return this;
    }

    public void setTemoins2(String temoins2) {
        this.temoins2 = temoins2;
    }

    public String getIdPere() {
        return idPere;
    }

    public DonneesActe idPere(String idPere) {
        this.idPere = idPere;
        return this;
    }

    public void setIdPere(String idPere) {
        this.idPere = idPere;
    }

    public String getIdMere() {
        return idMere;
    }

    public DonneesActe idMere(String idMere) {
        this.idMere = idMere;
        return this;
    }

    public void setIdMere(String idMere) {
        this.idMere = idMere;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public DonneesActe photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public DonneesActe photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getJuridiction() {
        return juridiction;
    }

    public DonneesActe juridiction(String juridiction) {
        this.juridiction = juridiction;
        return this;
    }

    public void setJuridiction(String juridiction) {
        this.juridiction = juridiction;
    }

    public String getLivre() {
        return livre;
    }

    public DonneesActe livre(String livre) {
        this.livre = livre;
        return this;
    }

    public void setLivre(String livre) {
        this.livre = livre;
    }

    public String getNotes() {
        return notes;
    }

    public DonneesActe notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFeuille() {
        return feuille;
    }

    public DonneesActe feuille(String feuille) {
        this.feuille = feuille;
        return this;
    }

    public void setFeuille(String feuille) {
        this.feuille = feuille;
    }

    public String getActe() {
        return acte;
    }

    public DonneesActe acte(String acte) {
        this.acte = acte;
        return this;
    }

    public void setActe(String acte) {
        this.acte = acte;
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
        DonneesActe donneesActe = (DonneesActe) o;
        if (donneesActe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donneesActe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonneesActe{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", dateDuJourChiffre='" + getDateDuJourChiffre() + "'" +
            ", registreSpecialRD='" + getRegistreSpecialRD() + "'" +
            ", nomEnfant='" + getNomEnfant() + "'" +
            ", registre='" + getRegistre() + "'" +
            ", annee=" + getAnnee() +
            ", numero='" + getNumero() + "'" +
            ", dateNaissanceChiffre='" + getDateNaissanceChiffre() + "'" +
            ", nomPere='" + getNomPere() + "'" +
            ", prenomPere='" + getPrenomPere() + "'" +
            ", nomMere='" + getNomMere() + "'" +
            ", prenomMere='" + getPrenomMere() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", statut='" + getStatut() + "'" +
            ", villeNaissance='" + getVilleNaissance() + "'" +
            ", adressePere='" + getAdressePere() + "'" +
            ", adresseMere='" + getAdresseMere() + "'" +
            ", temoins1='" + getTemoins1() + "'" +
            ", temoins2='" + getTemoins2() + "'" +
            ", idPere='" + getIdPere() + "'" +
            ", idMere='" + getIdMere() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", juridiction='" + getJuridiction() + "'" +
            ", livre='" + getLivre() + "'" +
            ", notes='" + getNotes() + "'" +
            ", feuille='" + getFeuille() + "'" +
            ", acte='" + getActe() + "'" +
            "}";
    }
}
