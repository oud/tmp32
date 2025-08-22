package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TauxRepository;
import com.mycompany.myapp.service.TauxService;
import com.mycompany.myapp.service.dto.TauxDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Taux}.
 */
@RestController
@RequestMapping("/api/tauxes")
public class TauxResource {

    private static final Logger LOG = LoggerFactory.getLogger(TauxResource.class);

    private static final String ENTITY_NAME = "taux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TauxService tauxService;

    private final TauxRepository tauxRepository;

    public TauxResource(TauxService tauxService, TauxRepository tauxRepository) {
        this.tauxService = tauxService;
        this.tauxRepository = tauxRepository;
    }

    /**
     * {@code POST  /tauxes} : Create a new taux.
     *
     * @param tauxDTO the tauxDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tauxDTO, or with status {@code 400 (Bad Request)} if the taux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TauxDTO> createTaux(@Valid @RequestBody TauxDTO tauxDTO) throws URISyntaxException {
        LOG.debug("REST request to save Taux : {}", tauxDTO);
        if (tauxDTO.getId() != null) {
            throw new BadRequestAlertException("A new taux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tauxDTO = tauxService.save(tauxDTO);
        return ResponseEntity.created(new URI("/api/tauxes/" + tauxDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tauxDTO.getId().toString()))
            .body(tauxDTO);
    }

    /**
     * {@code PUT  /tauxes/:id} : Updates an existing taux.
     *
     * @param id the id of the tauxDTO to save.
     * @param tauxDTO the tauxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tauxDTO,
     * or with status {@code 400 (Bad Request)} if the tauxDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tauxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TauxDTO> updateTaux(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TauxDTO tauxDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Taux : {}, {}", id, tauxDTO);
        if (tauxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tauxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tauxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tauxDTO = tauxService.update(tauxDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tauxDTO.getId().toString()))
            .body(tauxDTO);
    }

    /**
     * {@code PATCH  /tauxes/:id} : Partial updates given fields of an existing taux, field will ignore if it is null
     *
     * @param id the id of the tauxDTO to save.
     * @param tauxDTO the tauxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tauxDTO,
     * or with status {@code 400 (Bad Request)} if the tauxDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tauxDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tauxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TauxDTO> partialUpdateTaux(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TauxDTO tauxDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Taux partially : {}, {}", id, tauxDTO);
        if (tauxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tauxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tauxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TauxDTO> result = tauxService.partialUpdate(tauxDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tauxDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tauxes} : get all the tauxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tauxes in body.
     */
    @GetMapping("")
    public List<TauxDTO> getAllTauxes() {
        LOG.debug("REST request to get all Tauxes");
        return tauxService.findAll();
    }

    /**
     * {@code GET  /tauxes/:id} : get the "id" taux.
     *
     * @param id the id of the tauxDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tauxDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TauxDTO> getTaux(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Taux : {}", id);
        Optional<TauxDTO> tauxDTO = tauxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tauxDTO);
    }

    /**
     * {@code DELETE  /tauxes/:id} : delete the "id" taux.
     *
     * @param id the id of the tauxDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaux(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Taux : {}", id);
        tauxService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
