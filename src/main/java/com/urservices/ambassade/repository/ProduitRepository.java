package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Produit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("SELECT P FROM Produit P WHERE P.monnaie LIKE :monnaie AND P.nomProduit LIKE :nomProduit AND P.montant >= :montant ")
    Page<Produit> findByMonnaieAndProduitAndMontant(@Param("monnaie") String monnaie, @Param("nomProduit") String nomProduit,
                                                    @Param("montant") Long montant, Pageable pageable);
}
