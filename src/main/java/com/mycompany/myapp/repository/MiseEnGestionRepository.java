package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MiseEnGestion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MiseEnGestion entity.
 *
 * When extending this class, extend MiseEnGestionRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface MiseEnGestionRepository extends MiseEnGestionRepositoryWithBagRelationships, JpaRepository<MiseEnGestion, Long> {
    default Optional<MiseEnGestion> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<MiseEnGestion> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<MiseEnGestion> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
