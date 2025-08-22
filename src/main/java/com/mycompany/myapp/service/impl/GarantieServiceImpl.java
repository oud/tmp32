package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Garantie;
import com.mycompany.myapp.repository.GarantieRepository;
import com.mycompany.myapp.service.GarantieService;
import com.mycompany.myapp.service.dto.GarantieDTO;
import com.mycompany.myapp.service.mapper.GarantieMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Garantie}.
 */
@Service
@Transactional
public class GarantieServiceImpl implements GarantieService {

    private static final Logger LOG = LoggerFactory.getLogger(GarantieServiceImpl.class);

    private final GarantieRepository garantieRepository;

    private final GarantieMapper garantieMapper;

    public GarantieServiceImpl(GarantieRepository garantieRepository, GarantieMapper garantieMapper) {
        this.garantieRepository = garantieRepository;
        this.garantieMapper = garantieMapper;
    }

    @Override
    public GarantieDTO save(GarantieDTO garantieDTO) {
        LOG.debug("Request to save Garantie : {}", garantieDTO);
        Garantie garantie = garantieMapper.toEntity(garantieDTO);
        garantie = garantieRepository.save(garantie);
        return garantieMapper.toDto(garantie);
    }

    @Override
    public GarantieDTO update(GarantieDTO garantieDTO) {
        LOG.debug("Request to update Garantie : {}", garantieDTO);
        Garantie garantie = garantieMapper.toEntity(garantieDTO);
        garantie = garantieRepository.save(garantie);
        return garantieMapper.toDto(garantie);
    }

    @Override
    public Optional<GarantieDTO> partialUpdate(GarantieDTO garantieDTO) {
        LOG.debug("Request to partially update Garantie : {}", garantieDTO);

        return garantieRepository
            .findById(garantieDTO.getId())
            .map(existingGarantie -> {
                garantieMapper.partialUpdate(existingGarantie, garantieDTO);

                return existingGarantie;
            })
            .map(garantieRepository::save)
            .map(garantieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GarantieDTO> findAll() {
        LOG.debug("Request to get all Garanties");
        return garantieRepository.findAll().stream().map(garantieMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GarantieDTO> findOne(Long id) {
        LOG.debug("Request to get Garantie : {}", id);
        return garantieRepository.findById(id).map(garantieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Garantie : {}", id);
        garantieRepository.deleteById(id);
    }
}
