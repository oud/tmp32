package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ContratRepository;
import com.mycompany.myapp.service.ContratService;
import com.mycompany.myapp.service.dto.ContratDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Contrat}.
 */
@RestController
@RequestMapping("/api/contrats")
public class ContratResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContratResource.class);

    private static final String ENTITY_NAME = "contrat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContratService contratService;

    private final ContratRepository contratRepository;

    public ContratResource(ContratService contratService, ContratRepository contratRepository) {
        this.contratService = contratService;
        this.contratRepository = contratRepository;
    }

    /**
     * {@code POST  /contrats} : Create a new contrat.
     *
     * @param contratDTO the contratDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contratDTO, or with status {@code 400 (Bad Request)} if the contrat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContratDTO> createContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        LOG.debug("REST request to save Contrat : {}", contratDTO);
        if (contratDTO.getId() != null) {
            throw new BadRequestAlertException("A new contrat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contratDTO = contratService.save(contratDTO);
        return ResponseEntity.created(new URI("/api/contrats/" + contratDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, contratDTO.getId().toString()))
            .body(contratDTO);
    }

    /**
     * {@code PUT  /contrats/:id} : Updates an existing contrat.
     *
     * @param id the id of the contratDTO to save.
     * @param contratDTO the contratDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contratDTO,
     * or with status {@code 400 (Bad Request)} if the contratDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contratDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContratDTO> updateContrat(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContratDTO contratDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Contrat : {}, {}", id, contratDTO);
        if (contratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contratDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contratRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contratDTO = contratService.update(contratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contratDTO.getId().toString()))
            .body(contratDTO);
    }

    /**
     * {@code PATCH  /contrats/:id} : Partial updates given fields of an existing contrat, field will ignore if it is null
     *
     * @param id the id of the contratDTO to save.
     * @param contratDTO the contratDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contratDTO,
     * or with status {@code 400 (Bad Request)} if the contratDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contratDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contratDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContratDTO> partialUpdateContrat(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContratDTO contratDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Contrat partially : {}, {}", id, contratDTO);
        if (contratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contratDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contratRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContratDTO> result = contratService.partialUpdate(contratDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contratDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contrats} : get all the contrats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contrats in body.
     */
    @GetMapping("")
    public List<ContratDTO> getAllContrats() {
        LOG.debug("REST request to get all Contrats");
        return contratService.findAll();
    }

    /**
     * {@code GET  /contrats/:id} : get the "id" contrat.
     *
     * @param id the id of the contratDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contratDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContratDTO> getContrat(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Contrat : {}", id);
        Optional<ContratDTO> contratDTO = contratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contratDTO);
    }

    /**
     * {@code DELETE  /contrats/:id} : delete the "id" contrat.
     *
     * @param id the id of the contratDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Contrat : {}", id);
        contratService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
