package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Livre.
 */
@Entity
@Table(name = "livre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "code_isbn", length = 50, nullable = false)
    private String codeISBN;

    @Size(max = 50)
    @Column(name = "auteur", length = 50)
    private String auteur;

    @Size(max = 100)
    @Column(name = "titre", length = 100)
    private String titre;

    @Size(max = 50)
    @Column(name = "edition", length = 50)
    private String edition;

    @Size(max = 50)
    @Column(name = "etagere", length = 50)
    private String etagere;

    @Size(max = 4)
    @Column(name = "annee", length = 4)
    private String annee;

    @Size(max = 50)
    @Column(name = "categorie", length = 50)
    private String categorie;

    @Column(name = "resume")
    private String resume;

    @Size(max = 2)
    @Column(name = "quantite", length = 2)
    private String quantite;

    @Size(max = 50)
    @Column(name = "disponible", length = 50)
    private String disponible;

    @Column(name = "created_by_php_runner")
    private Integer createdByPHPRunner;

    @Size(max = 4)
    @Column(name = "page", length = 4)
    private String page;

    @Column(name = "consultation")
    private String consultation;

    @Size(max = 50)
    @Column(name = "origine", length = 50)
    private String origine;

    @Size(max = 100)
    @Column(name = "sous_titre", length = 100)
    private String sousTitre;

    @Size(max = 50)
    @Column(name = "collection", length = 50)
    private String collection;

    @Size(max = 50)
    @Column(name = "impression", length = 50)
    private String impression;

    @Size(max = 50)
    @Column(name = "format", length = 50)
    private String format;

    @Size(max = 50)
    @Column(name = "jhi_index", length = 50)
    private String index;

    @Size(max = 50)
    @Column(name = "bibliographie", length = 50)
    private String bibliographie;

    @Size(max = 50)
    @Column(name = "lieu_edition", length = 50)
    private String lieuEdition;

    @Size(max = 50)
    @Column(name = "lieu_impression", length = 50)
    private String lieuImpression;

    @Size(max = 50)
    @Column(name = "illustration", length = 50)
    private String illustration;

    @Size(max = 50)
    @Column(name = "observation", length = 50)
    private String observation;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Size(max = 50)
    @Column(name = "statistique", length = 50)
    private String statistique;

    @Size(max = 50)
    @Column(name = "glossaire", length = 50)
    private String glossaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeISBN() {
        return codeISBN;
    }

    public Livre codeISBN(String codeISBN) {
        this.codeISBN = codeISBN;
        return this;
    }

    public void setCodeISBN(String codeISBN) {
        this.codeISBN = codeISBN;
    }

    public String getAuteur() {
        return auteur;
    }

    public Livre auteur(String auteur) {
        this.auteur = auteur;
        return this;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public Livre titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEdition() {
        return edition;
    }

    public Livre edition(String edition) {
        this.edition = edition;
        return this;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEtagere() {
        return etagere;
    }

    public Livre etagere(String etagere) {
        this.etagere = etagere;
        return this;
    }

    public void setEtagere(String etagere) {
        this.etagere = etagere;
    }

    public String getAnnee() {
        return annee;
    }

    public Livre annee(String annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getCategorie() {
        return categorie;
    }

    public Livre categorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getResume() {
        return resume;
    }

    public Livre resume(String resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getQuantite() {
        return quantite;
    }

    public Livre quantite(String quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDisponible() {
        return disponible;
    }

    public Livre disponible(String disponible) {
        this.disponible = disponible;
        return this;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public Integer getCreatedByPHPRunner() {
        return createdByPHPRunner;
    }

    public Livre createdByPHPRunner(Integer createdByPHPRunner) {
        this.createdByPHPRunner = createdByPHPRunner;
        return this;
    }

    public void setCreatedByPHPRunner(Integer createdByPHPRunner) {
        this.createdByPHPRunner = createdByPHPRunner;
    }

    public String getPage() {
        return page;
    }

    public Livre page(String page) {
        this.page = page;
        return this;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getConsultation() {
        return consultation;
    }

    public Livre consultation(String consultation) {
        this.consultation = consultation;
        return this;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public String getOrigine() {
        return origine;
    }

    public Livre origine(String origine) {
        this.origine = origine;
        return this;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public Livre sousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
        return this;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getCollection() {
        return collection;
    }

    public Livre collection(String collection) {
        this.collection = collection;
        return this;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getImpression() {
        return impression;
    }

    public Livre impression(String impression) {
        this.impression = impression;
        return this;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getFormat() {
        return format;
    }

    public Livre format(String format) {
        this.format = format;
        return this;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getIndex() {
        return index;
    }

    public Livre index(String index) {
        this.index = index;
        return this;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getBibliographie() {
        return bibliographie;
    }

    public Livre bibliographie(String bibliographie) {
        this.bibliographie = bibliographie;
        return this;
    }

    public void setBibliographie(String bibliographie) {
        this.bibliographie = bibliographie;
    }

    public String getLieuEdition() {
        return lieuEdition;
    }

    public Livre lieuEdition(String lieuEdition) {
        this.lieuEdition = lieuEdition;
        return this;
    }

    public void setLieuEdition(String lieuEdition) {
        this.lieuEdition = lieuEdition;
    }

    public String getLieuImpression() {
        return lieuImpression;
    }

    public Livre lieuImpression(String lieuImpression) {
        this.lieuImpression = lieuImpression;
        return this;
    }

    public void setLieuImpression(String lieuImpression) {
        this.lieuImpression = lieuImpression;
    }

    public String getIllustration() {
        return illustration;
    }

    public Livre illustration(String illustration) {
        this.illustration = illustration;
        return this;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public String getObservation() {
        return observation;
    }

    public Livre observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPrenom() {
        return prenom;
    }

    public Livre prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatistique() {
        return statistique;
    }

    public Livre statistique(String statistique) {
        this.statistique = statistique;
        return this;
    }

    public void setStatistique(String statistique) {
        this.statistique = statistique;
    }

    public String getGlossaire() {
        return glossaire;
    }

    public Livre glossaire(String glossaire) {
        this.glossaire = glossaire;
        return this;
    }

    public void setGlossaire(String glossaire) {
        this.glossaire = glossaire;
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
        Livre livre = (Livre) o;
        if (livre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), livre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Livre{" +
            "id=" + getId() +
            ", codeISBN='" + getCodeISBN() + "'" +
            ", auteur='" + getAuteur() + "'" +
            ", titre='" + getTitre() + "'" +
            ", edition='" + getEdition() + "'" +
            ", etagere='" + getEtagere() + "'" +
            ", annee='" + getAnnee() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", resume='" + getResume() + "'" +
            ", quantite='" + getQuantite() + "'" +
            ", disponible='" + getDisponible() + "'" +
            ", createdByPHPRunner=" + getCreatedByPHPRunner() +
            ", page='" + getPage() + "'" +
            ", consultation='" + getConsultation() + "'" +
            ", origine='" + getOrigine() + "'" +
            ", sousTitre='" + getSousTitre() + "'" +
            ", collection='" + getCollection() + "'" +
            ", impression='" + getImpression() + "'" +
            ", format='" + getFormat() + "'" +
            ", index='" + getIndex() + "'" +
            ", bibliographie='" + getBibliographie() + "'" +
            ", lieuEdition='" + getLieuEdition() + "'" +
            ", lieuImpression='" + getLieuImpression() + "'" +
            ", illustration='" + getIllustration() + "'" +
            ", observation='" + getObservation() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", statistique='" + getStatistique() + "'" +
            ", glossaire='" + getGlossaire() + "'" +
            "}";
    }
}
