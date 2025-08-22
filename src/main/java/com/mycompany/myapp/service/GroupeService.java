package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GroupeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Groupe}.
 */
public interface GroupeService {
    /**
     * Save a groupe.
     *
     * @param groupeDTO the entity to save.
     * @return the persisted entity.
     */
    GroupeDTO save(GroupeDTO groupeDTO);

    /**
     * Updates a groupe.
     *
     * @param groupeDTO the entity to update.
     * @return the persisted entity.
     */
    GroupeDTO update(GroupeDTO groupeDTO);

    /**
     * Partially updates a groupe.
     *
     * @param groupeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GroupeDTO> partialUpdate(GroupeDTO groupeDTO);

    /**
     * Get all the groupes.
     *
     * @return the list of entities.
     */
    List<GroupeDTO> findAll();

    /**
     * Get the "id" groupe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GroupeDTO> findOne(Long id);

    /**
     * Delete the "id" groupe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
