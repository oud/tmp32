package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Taux;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Taux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TauxRepository extends JpaRepository<Taux, Long> {}
