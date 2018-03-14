package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Monnaie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Monnaie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonnaieRepository extends JpaRepository<Monnaie, Long> {
    @Query("SELECT M FROM Monnaie M WHERE M.type LIKE :pType AND M.produit LIKE :produit ")
    Page<Monnaie> findByTypeAndProduit(@Param("pType") String type, @Param("produit") String produit, Pageable pageable);

    @Query("SELECT M FROM Monnaie M WHERE M.type LIKE :pType AND M.produit LIKE :produit AND M.montant= :montant ")
    Page<Monnaie> searchAll(@Param("pType") String type, @Param("produit") String produit,
                                                    @Param("montant") Long montant, Pageable pageable);
}
