package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Montant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Montant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MontantRepository extends JpaRepository<Montant, Long> {

    @Query("SELECT M FROM Montant M WHERE M.monnaie LIKE :monnaie AND M.produit LIKE :produit ")
    Page<Montant> findByMonnaieAndProduit(@Param("monnaie") String monnaie, @Param("produit") String produit, Pageable pageable);

    @Query("SELECT M FROM Montant M WHERE M.monnaie LIKE :monnaie AND M.produit LIKE :produit AND M.montant= :montant ")
    Page<Montant> findByMonnaieAndProduitAndMontant(@Param("monnaie") String monnaie, @Param("produit") String produit,
                                                    @Param("montant") Long montant, Pageable pageable);


}
