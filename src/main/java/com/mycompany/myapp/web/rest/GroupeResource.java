package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.GroupeRepository;
import com.mycompany.myapp.service.GroupeService;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Groupe}.
 */
@RestController
@RequestMapping("/api/groupes")
public class GroupeResource {

    private static final Logger LOG = LoggerFactory.getLogger(GroupeResource.class);

    private static final String ENTITY_NAME = "groupe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupeService groupeService;

    private final GroupeRepository groupeRepository;

    public GroupeResource(GroupeService groupeService, GroupeRepository groupeRepository) {
        this.groupeService = groupeService;
        this.groupeRepository = groupeRepository;
    }

    /**
     * {@code POST  /groupes} : Create a new groupe.
     *
     * @param groupeDTO the groupeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupeDTO, or with status {@code 400 (Bad Request)} if the groupe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<GroupeDTO> createGroupe(@Valid @RequestBody GroupeDTO groupeDTO) throws URISyntaxException {
        LOG.debug("REST request to save Groupe : {}", groupeDTO);
        if (groupeDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        groupeDTO = groupeService.save(groupeDTO);
        return ResponseEntity.created(new URI("/api/groupes/" + groupeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, groupeDTO.getId().toString()))
            .body(groupeDTO);
    }

    /**
     * {@code PUT  /groupes/:id} : Updates an existing groupe.
     *
     * @param id the id of the groupeDTO to save.
     * @param groupeDTO the groupeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupeDTO,
     * or with status {@code 400 (Bad Request)} if the groupeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GroupeDTO> updateGroupe(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GroupeDTO groupeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Groupe : {}, {}", id, groupeDTO);
        if (groupeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, groupeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!groupeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        groupeDTO = groupeService.update(groupeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupeDTO.getId().toString()))
            .body(groupeDTO);
    }

    /**
     * {@code PATCH  /groupes/:id} : Partial updates given fields of an existing groupe, field will ignore if it is null
     *
     * @param id the id of the groupeDTO to save.
     * @param groupeDTO the groupeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupeDTO,
     * or with status {@code 400 (Bad Request)} if the groupeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the groupeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the groupeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GroupeDTO> partialUpdateGroupe(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GroupeDTO groupeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Groupe partially : {}, {}", id, groupeDTO);
        if (groupeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, groupeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!groupeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GroupeDTO> result = groupeService.partialUpdate(groupeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /groupes} : get all the groupes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupes in body.
     */
    @GetMapping("")
    public List<GroupeDTO> getAllGroupes() {
        LOG.debug("REST request to get all Groupes");
        return groupeService.findAll();
    }

    /**
     * {@code GET  /groupes/:id} : get the "id" groupe.
     *
     * @param id the id of the groupeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupeDTO> getGroupe(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Groupe : {}", id);
        Optional<GroupeDTO> groupeDTO = groupeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupeDTO);
    }

    /**
     * {@code DELETE  /groupes/:id} : delete the "id" groupe.
     *
     * @param id the id of the groupeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Groupe : {}", id);
        groupeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
