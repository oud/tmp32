package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GarantieDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Garantie}.
 */
public interface GarantieService {
    /**
     * Save a garantie.
     *
     * @param garantieDTO the entity to save.
     * @return the persisted entity.
     */
    GarantieDTO save(GarantieDTO garantieDTO);

    /**
     * Updates a garantie.
     *
     * @param garantieDTO the entity to update.
     * @return the persisted entity.
     */
    GarantieDTO update(GarantieDTO garantieDTO);

    /**
     * Partially updates a garantie.
     *
     * @param garantieDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GarantieDTO> partialUpdate(GarantieDTO garantieDTO);

    /**
     * Get all the garanties.
     *
     * @return the list of entities.
     */
    List<GarantieDTO> findAll();

    /**
     * Get the "id" garantie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GarantieDTO> findOne(Long id);

    /**
     * Delete the "id" garantie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
