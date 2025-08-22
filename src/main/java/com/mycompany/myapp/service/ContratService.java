package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ContratDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Contrat}.
 */
public interface ContratService {
    /**
     * Save a contrat.
     *
     * @param contratDTO the entity to save.
     * @return the persisted entity.
     */
    ContratDTO save(ContratDTO contratDTO);

    /**
     * Updates a contrat.
     *
     * @param contratDTO the entity to update.
     * @return the persisted entity.
     */
    ContratDTO update(ContratDTO contratDTO);

    /**
     * Partially updates a contrat.
     *
     * @param contratDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContratDTO> partialUpdate(ContratDTO contratDTO);

    /**
     * Get all the contrats.
     *
     * @return the list of entities.
     */
    List<ContratDTO> findAll();

    /**
     * Get the "id" contrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContratDTO> findOne(Long id);

    /**
     * Delete the "id" contrat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
