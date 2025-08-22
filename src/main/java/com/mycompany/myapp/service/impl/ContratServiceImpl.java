package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.repository.ContratRepository;
import com.mycompany.myapp.service.ContratService;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.mapper.ContratMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Contrat}.
 */
@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    private static final Logger LOG = LoggerFactory.getLogger(ContratServiceImpl.class);

    private final ContratRepository contratRepository;

    private final ContratMapper contratMapper;

    public ContratServiceImpl(ContratRepository contratRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
    }

    @Override
    public ContratDTO save(ContratDTO contratDTO) {
        LOG.debug("Request to save Contrat : {}", contratDTO);
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat = contratRepository.save(contrat);
        return contratMapper.toDto(contrat);
    }

    @Override
    public ContratDTO update(ContratDTO contratDTO) {
        LOG.debug("Request to update Contrat : {}", contratDTO);
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat = contratRepository.save(contrat);
        return contratMapper.toDto(contrat);
    }

    @Override
    public Optional<ContratDTO> partialUpdate(ContratDTO contratDTO) {
        LOG.debug("Request to partially update Contrat : {}", contratDTO);

        return contratRepository
            .findById(contratDTO.getId())
            .map(existingContrat -> {
                contratMapper.partialUpdate(existingContrat, contratDTO);

                return existingContrat;
            })
            .map(contratRepository::save)
            .map(contratMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findAll() {
        LOG.debug("Request to get all Contrats");
        return contratRepository.findAll().stream().map(contratMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContratDTO> findOne(Long id) {
        LOG.debug("Request to get Contrat : {}", id);
        return contratRepository.findById(id).map(contratMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Contrat : {}", id);
        contratRepository.deleteById(id);
    }
}
