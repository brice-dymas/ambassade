package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Livre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Livre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivreRepository extends JpaRepository<Livre, Long>, QueryDslPredicateExecutor<Livre> {

    @Query("SELECT L FROM Livre L WHERE L.codeISBN LIKE :codeISBN AND L.auteur LIKE :auteur AND L.titre LIKE :titre " +
        "AND L.edition LIKE :edition AND L.etagere LIKE :etagere AND L.categorie LIKE :categorie " +
        "AND L.resume LIKE :resume AND L.disponible LIKE :disponible AND L.page LIKE :page AND L.consultation LIKE :consultation " +
        "AND L.origine LIKE :origine AND L.sousTitre LIKE :sousTitre AND L.collection LIKE :collection AND L.impression LIKE :impression " +
        "AND L.format LIKE :format AND L.index LIKE :pIndex AND L.bibliographie LIKE :bibliographie AND L.lieuEdition LIKE :lieuEdition " +
        "AND L.lieuImpression LIKE :lieuImpression AND L.illustration LIKE :illustration AND L.observation LIKE :observation " +
        "AND L.prenom LIKE :prenom AND L.statistique LIKE :statistique AND L.glossaire LIKE :glossaire ")
    Page<Livre> search(@Param("codeISBN")String codeISBN,
                       @Param("auteur")String auteur, @Param("titre")String titre, @Param("edition")String edition,
                        @Param("etagere")String etagere, @Param("categorie")String categorie, @Param("resume")String resume,
                        @Param("disponible")String disponible, @Param("page")String page, @Param("consultation")String consultation,
                        @Param("origine")String origine, @Param("sousTitre")String sousTitre, @Param("collection")String collection,
                        @Param("impression")String impression, @Param("format")String format, @Param("pIndex")String index,
                        @Param("bibliographie")String bibliographie, @Param("lieuEdition")String lieuEdition,
                        @Param("lieuImpression")String lieuImpression, @Param("illustration")String illustration,
                        @Param("observation")String observation, @Param("prenom")String prenom,
                        @Param("statistique")String statistique, @Param("glossaire")String glossaire, Pageable pageable);

    @Query("SELECT L FROM Livre L WHERE L.codeISBN LIKE :codeISBN AND L.auteur LIKE :auteur AND L.titre LIKE :titre " +
        "AND L.edition LIKE :edition AND L.etagere LIKE :etagere AND L.categorie LIKE :categorie " +
        "AND L.resume LIKE :resume AND L.disponible LIKE :disponible AND L.page LIKE :page AND L.consultation LIKE :consultation " +
        "AND L.origine LIKE :origine AND L.sousTitre LIKE :sousTitre AND L.collection LIKE :collection AND L.impression LIKE :impression " +
        "AND L.format LIKE :format AND L.index LIKE :pIndex AND L.bibliographie LIKE :bibliographie AND L.lieuEdition LIKE :lieuEdition " +
        "AND L.lieuImpression LIKE :lieuImpression AND L.illustration LIKE :illustration AND L.observation LIKE :observation " +
        "AND L.prenom LIKE :prenom AND L.statistique LIKE :statistique AND L.glossaire LIKE :glossaire AND L.quantite >= :quantite ")
    Page<Livre> searchWithQuantite(@Param("quantite")Long quantite, @Param("codeISBN")String codeISBN,
                          @Param("auteur")String auteur, @Param("titre")String titre, @Param("edition")String edition,
                          @Param("etagere")String etagere, @Param("categorie")String categorie, @Param("resume")String resume,
                          @Param("disponible")String disponible, @Param("page")String page, @Param("consultation")String consultation,
                          @Param("origine")String origine, @Param("sousTitre")String sousTitre, @Param("collection")String collection,
                          @Param("impression")String impression, @Param("format")String format, @Param("pIndex")String index,
                          @Param("bibliographie")String bibliographie, @Param("lieuEdition")String lieuEdition,
                          @Param("lieuImpression")String lieuImpression, @Param("illustration")String illustration,
                          @Param("observation")String observation, @Param("prenom")String prenom,
                          @Param("statistique")String statistique, @Param("glossaire")String glossaire, Pageable pageable);

    @Query("SELECT L FROM Livre L WHERE L.codeISBN LIKE :codeISBN AND L.auteur LIKE :auteur AND L.titre LIKE :titre " +
        "AND L.edition LIKE :edition AND L.etagere LIKE :etagere AND L.annee >= :annee AND L.categorie LIKE :categorie " +
        "AND L.resume LIKE :resume AND L.disponible LIKE :disponible AND L.page LIKE :page AND L.consultation LIKE :consultation " +
        "AND L.origine LIKE :origine AND L.sousTitre LIKE :sousTitre AND L.collection LIKE :collection AND L.impression LIKE :impression " +
        "AND L.format LIKE :format AND L.index LIKE :pIndex AND L.bibliographie LIKE :bibliographie AND L.lieuEdition LIKE :lieuEdition " +
        "AND L.lieuImpression LIKE :lieuImpression AND L.illustration LIKE :illustration AND L.observation LIKE :observation " +
        "AND L.prenom LIKE :prenom AND L.statistique LIKE :statistique AND L.glossaire LIKE :glossaire ")
    Page<Livre> searchWithAnnee(@Param("annee")Integer annee, @Param("codeISBN")String codeISBN,
                          @Param("auteur")String auteur, @Param("titre")String titre, @Param("edition")String edition,
                          @Param("etagere")String etagere, @Param("categorie")String categorie, @Param("resume")String resume,
                          @Param("disponible")String disponible, @Param("page")String page, @Param("consultation")String consultation,
                          @Param("origine")String origine, @Param("sousTitre")String sousTitre, @Param("collection")String collection,
                          @Param("impression")String impression, @Param("format")String format, @Param("pIndex")String index,
                          @Param("bibliographie")String bibliographie, @Param("lieuEdition")String lieuEdition,
                          @Param("lieuImpression")String lieuImpression, @Param("illustration")String illustration,
                          @Param("observation")String observation, @Param("prenom")String prenom,
                          @Param("statistique")String statistique, @Param("glossaire")String glossaire, Pageable pageable);

    @Query("SELECT L FROM Livre L WHERE L.codeISBN LIKE :codeISBN AND L.auteur LIKE :auteur AND L.titre LIKE :titre " +
        "AND L.edition LIKE :edition AND L.etagere LIKE :etagere AND L.annee >= :annee AND L.categorie LIKE :categorie " +
        "AND L.resume LIKE :resume AND L.disponible LIKE :disponible AND L.page LIKE :page AND L.consultation LIKE :consultation " +
        "AND L.origine LIKE :origine AND L.sousTitre LIKE :sousTitre AND L.collection LIKE :collection AND L.impression LIKE :impression " +
        "AND L.format LIKE :format AND L.index LIKE :pIndex AND L.bibliographie LIKE :bibliographie AND L.lieuEdition LIKE :lieuEdition " +
        "AND L.lieuImpression LIKE :lieuImpression AND L.illustration LIKE :illustration AND L.observation LIKE :observation " +
        "AND L.prenom LIKE :prenom AND L.statistique LIKE :statistique AND L.glossaire LIKE :glossaire AND L.quantite >= :quantite ")
    Page<Livre> searchAll(@Param("quantite")Long quantite, @Param("annee")Integer annee, @Param("codeISBN")String codeISBN,
                       @Param("auteur")String auteur, @Param("titre")String titre, @Param("edition")String edition,
                       @Param("etagere")String etagere, @Param("categorie")String categorie, @Param("resume")String resume,
                       @Param("disponible")String disponible, @Param("page")String page, @Param("consultation")String consultation,
                       @Param("origine")String origine, @Param("sousTitre")String sousTitre, @Param("collection")String collection,
                       @Param("impression")String impression, @Param("format")String format, @Param("pIndex")String index,
                       @Param("bibliographie")String bibliographie, @Param("lieuEdition")String lieuEdition,
                       @Param("lieuImpression")String lieuImpression, @Param("illustration")String illustration,
                       @Param("observation")String observation, @Param("prenom")String prenom,
                       @Param("statistique")String statistique, @Param("glossaire")String glossaire, Pageable pageable);

}
