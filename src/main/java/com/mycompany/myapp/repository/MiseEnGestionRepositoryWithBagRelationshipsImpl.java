package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MiseEnGestion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class MiseEnGestionRepositoryWithBagRelationshipsImpl implements MiseEnGestionRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String MISEENGESTIONS_PARAMETER = "miseEnGestions";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<MiseEnGestion> fetchBagRelationships(Optional<MiseEnGestion> miseEnGestion) {
        return miseEnGestion.map(this::fetchDemandeXRMS).map(this::fetchPmEtablissements);
    }

    @Override
    public Page<MiseEnGestion> fetchBagRelationships(Page<MiseEnGestion> miseEnGestions) {
        return new PageImpl<>(
            fetchBagRelationships(miseEnGestions.getContent()),
            miseEnGestions.getPageable(),
            miseEnGestions.getTotalElements()
        );
    }

    @Override
    public List<MiseEnGestion> fetchBagRelationships(List<MiseEnGestion> miseEnGestions) {
        return Optional.of(miseEnGestions).map(this::fetchDemandeXRMS).map(this::fetchPmEtablissements).orElse(Collections.emptyList());
    }

    MiseEnGestion fetchDemandeXRMS(MiseEnGestion result) {
        return entityManager
            .createQuery(
                "select miseEnGestion from MiseEnGestion miseEnGestion left join fetch miseEnGestion.demandeXRMS where miseEnGestion.id = :id",
                MiseEnGestion.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<MiseEnGestion> fetchDemandeXRMS(List<MiseEnGestion> miseEnGestions) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, miseEnGestions.size()).forEach(index -> order.put(miseEnGestions.get(index).getId(), index));
        List<MiseEnGestion> result = entityManager
            .createQuery(
                "select miseEnGestion from MiseEnGestion miseEnGestion left join fetch miseEnGestion.demandeXRMS where miseEnGestion in :miseEnGestions",
                MiseEnGestion.class
            )
            .setParameter(MISEENGESTIONS_PARAMETER, miseEnGestions)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    MiseEnGestion fetchPmEtablissements(MiseEnGestion result) {
        return entityManager
            .createQuery(
                "select miseEnGestion from MiseEnGestion miseEnGestion left join fetch miseEnGestion.pmEtablissements where miseEnGestion.id = :id",
                MiseEnGestion.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<MiseEnGestion> fetchPmEtablissements(List<MiseEnGestion> miseEnGestions) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, miseEnGestions.size()).forEach(index -> order.put(miseEnGestions.get(index).getId(), index));
        List<MiseEnGestion> result = entityManager
            .createQuery(
                "select miseEnGestion from MiseEnGestion miseEnGestion left join fetch miseEnGestion.pmEtablissements where miseEnGestion in :miseEnGestions",
                MiseEnGestion.class
            )
            .setParameter(MISEENGESTIONS_PARAMETER, miseEnGestions)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
