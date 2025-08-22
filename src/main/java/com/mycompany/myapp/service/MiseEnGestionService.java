package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MiseEnGestionDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.MiseEnGestion}.
 */
public interface MiseEnGestionService {
    /**
     * Save a miseEnGestion.
     *
     * @param miseEnGestionDTO the entity to save.
     * @return the persisted entity.
     */
    MiseEnGestionDTO save(MiseEnGestionDTO miseEnGestionDTO);

    /**
     * Updates a miseEnGestion.
     *
     * @param miseEnGestionDTO the entity to update.
     * @return the persisted entity.
     */
    MiseEnGestionDTO update(MiseEnGestionDTO miseEnGestionDTO);

    /**
     * Partially updates a miseEnGestion.
     *
     * @param miseEnGestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MiseEnGestionDTO> partialUpdate(MiseEnGestionDTO miseEnGestionDTO);

    /**
     * Get all the miseEnGestions.
     *
     * @return the list of entities.
     */
    List<MiseEnGestionDTO> findAll();

    /**
     * Get all the miseEnGestions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MiseEnGestionDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" miseEnGestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MiseEnGestionDTO> findOne(Long id);

    /**
     * Delete the "id" miseEnGestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
