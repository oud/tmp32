package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MiseEnGestion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MiseEnGestionRepositoryWithBagRelationships {
    Optional<MiseEnGestion> fetchBagRelationships(Optional<MiseEnGestion> miseEnGestion);

    List<MiseEnGestion> fetchBagRelationships(List<MiseEnGestion> miseEnGestions);

    Page<MiseEnGestion> fetchBagRelationships(Page<MiseEnGestion> miseEnGestions);
}
