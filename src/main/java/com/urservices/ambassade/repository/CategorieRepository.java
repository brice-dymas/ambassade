package com.urservices.ambassade.repository;

import com.urservices.ambassade.domain.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


/**
 * Spring Data JPA repository for the Categorie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>, QueryDslPredicateExecutor<Categorie> {

    @Query("SELECT C FROM Categorie C WHERE C.nomCategorie LIKE :nomCategorie")
    Page<Categorie> search(@Param("nomCategorie")String nomCategorie, Pageable pageable);
}
