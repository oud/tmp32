package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TauxDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Taux}.
 */
public interface TauxService {
    /**
     * Save a taux.
     *
     * @param tauxDTO the entity to save.
     * @return the persisted entity.
     */
    TauxDTO save(TauxDTO tauxDTO);

    /**
     * Updates a taux.
     *
     * @param tauxDTO the entity to update.
     * @return the persisted entity.
     */
    TauxDTO update(TauxDTO tauxDTO);

    /**
     * Partially updates a taux.
     *
     * @param tauxDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TauxDTO> partialUpdate(TauxDTO tauxDTO);

    /**
     * Get all the tauxes.
     *
     * @return the list of entities.
     */
    List<TauxDTO> findAll();

    /**
     * Get the "id" taux.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TauxDTO> findOne(Long id);

    /**
     * Delete the "id" taux.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
