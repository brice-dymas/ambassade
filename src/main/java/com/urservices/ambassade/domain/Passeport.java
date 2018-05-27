package com.urservices.ambassade.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.urservices.ambassade.domain.enumeration.Statut;

import com.urservices.ambassade.domain.enumeration.State;

/**
 * A Passeport.
 */
@Entity
@Table(name = "passeport")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Passeport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0L)
    @Column(name = "numero_formulaire", nullable = false)
    private Long numeroFormulaire;

    @NotNull
    @Size(max = 30)
    @Column(name = "nom", length = 30, nullable = false)
    private String nom;

    @NotNull
    @Size(max = 40)
    @Column(name = "prenom", length = 40, nullable = false)
    private String prenom;

    @Size(max = 15)
    @Column(name = "numero_passeport", length = 15)
    private String numeroPasseport;

    @NotNull
    @Column(name = "ne_le", nullable = false)
    private LocalDate neLe;

    @NotNull
    @Size(max = 30)
    @Column(name = "lieu_naissance", length = 30, nullable = false)
    private String lieuNaissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_civil")
    private Statut etatCivil;

    @Column(name = "adresse")
    private String adresse;

    @Size(max = 15)
    @Column(name = "telephone", length = 15)
    private String telephone;

    @Size(max = 20)
    @Column(name = "nif", length = 20)
    private String nif;

    @Size(max = 25)
    @Column(name = "pays_emetteur", length = 25)
    private String paysEmetteur;

    @NotNull
    @Column(name = "soumis_le", nullable = false)
    private LocalDate soumisLe;

    @Column(name = "delivre_le")
    private LocalDate delivreLe;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant", precision=10, scale=2, nullable = false)
    private BigDecimal montant;

    @Column(name = "remarques")
    private String remarques;

    @Column(name = "date_emission")
    private LocalDate dateEmission;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Column(name = "remarques_r")
    private String remarquesR;

    @Size(max = 15)
    @Column(name = "sms", length = 15)
    private String sms;

    @Size(max = 60)
    @Column(name = "sms_2", length = 60)
    private String sms2;

    @Size(max = 50)
    @Column(name = "documents", length = 50)
    private String documents;

    @Column(name = "taille")
    private Integer taille;

    @Column(name = "recu")
    private String recu;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

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

    public Long getNumeroFormulaire() {
        return numeroFormulaire;
    }

    public Passeport numeroFormulaire(Long numeroFormulaire) {
        this.numeroFormulaire = numeroFormulaire;
        return this;
    }

    public void setNumeroFormulaire(Long numeroFormulaire) {
        this.numeroFormulaire = numeroFormulaire;
    }

    public String getNom() {
        return nom;
    }

    public Passeport nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Passeport prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroPasseport() {
        return numeroPasseport;
    }

    public Passeport numeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
        return this;
    }

    public void setNumeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
    }

    public LocalDate getNeLe() {
        return neLe;
    }

    public Passeport neLe(LocalDate neLe) {
        this.neLe = neLe;
        return this;
    }

    public void setNeLe(LocalDate neLe) {
        this.neLe = neLe;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public Passeport lieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Statut getEtatCivil() {
        return etatCivil;
    }

    public Passeport etatCivil(Statut etatCivil) {
        this.etatCivil = etatCivil;
        return this;
    }

    public void setEtatCivil(Statut etatCivil) {
        this.etatCivil = etatCivil;
    }

    public String getAdresse() {
        return adresse;
    }

    public Passeport adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Passeport telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNif() {
        return nif;
    }

    public Passeport nif(String nif) {
        this.nif = nif;
        return this;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPaysEmetteur() {
        return paysEmetteur;
    }

    public Passeport paysEmetteur(String paysEmetteur) {
        this.paysEmetteur = paysEmetteur;
        return this;
    }

    public void setPaysEmetteur(String paysEmetteur) {
        this.paysEmetteur = paysEmetteur;
    }

    public LocalDate getSoumisLe() {
        return soumisLe;
    }

    public Passeport soumisLe(LocalDate soumisLe) {
        this.soumisLe = soumisLe;
        return this;
    }

    public void setSoumisLe(LocalDate soumisLe) {
        this.soumisLe = soumisLe;
    }

    public LocalDate getDelivreLe() {
        return delivreLe;
    }

    public Passeport delivreLe(LocalDate delivreLe) {
        this.delivreLe = delivreLe;
        return this;
    }

    public void setDelivreLe(LocalDate delivreLe) {
        this.delivreLe = delivreLe;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Passeport montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getRemarques() {
        return remarques;
    }

    public Passeport remarques(String remarques) {
        this.remarques = remarques;
        return this;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public Passeport dateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
        return this;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public Passeport dateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
        return this;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getRemarquesR() {
        return remarquesR;
    }

    public Passeport remarquesR(String remarquesR) {
        this.remarquesR = remarquesR;
        return this;
    }

    public void setRemarquesR(String remarquesR) {
        this.remarquesR = remarquesR;
    }

    public String getSms() {
        return sms;
    }

    public Passeport sms(String sms) {
        this.sms = sms;
        return this;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getSms2() {
        return sms2;
    }

    public Passeport sms2(String sms2) {
        this.sms2 = sms2;
        return this;
    }

    public void setSms2(String sms2) {
        this.sms2 = sms2;
    }

    public String getDocuments() {
        return documents;
    }

    public Passeport documents(String documents) {
        this.documents = documents;
        return this;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public Integer getTaille() {
        return taille;
    }

    public Passeport taille(Integer taille) {
        this.taille = taille;
        return this;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public String getRecu() {
        return recu;
    }

    public Passeport recu(String recu) {
        this.recu = recu;
        return this;
    }

    public void setRecu(String recu) {
        this.recu = recu;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Passeport photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Passeport photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public State getState() {
        return state;
    }

    public Passeport state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public Passeport typeService(TypeService typeService) {
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
        Passeport passeport = (Passeport) o;
        if (passeport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passeport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Passeport{" +
            "id=" + getId() +
            ", numeroFormulaire=" + getNumeroFormulaire() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", numeroPasseport='" + getNumeroPasseport() + "'" +
            ", neLe='" + getNeLe() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", nif='" + getNif() + "'" +
            ", paysEmetteur='" + getPaysEmetteur() + "'" +
            ", soumisLe='" + getSoumisLe() + "'" +
            ", delivreLe='" + getDelivreLe() + "'" +
            ", montant=" + getMontant() +
            ", remarques='" + getRemarques() + "'" +
            ", dateEmission='" + getDateEmission() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            ", remarquesR='" + getRemarquesR() + "'" +
            ", sms='" + getSms() + "'" +
            ", sms2='" + getSms2() + "'" +
            ", documents='" + getDocuments() + "'" +
            ", taille=" + getTaille() +
            ", recu='" + getRecu() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
