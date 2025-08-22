package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Taux;
import com.mycompany.myapp.repository.TauxRepository;
import com.mycompany.myapp.service.TauxService;
import com.mycompany.myapp.service.dto.TauxDTO;
import com.mycompany.myapp.service.mapper.TauxMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Taux}.
 */
@Service
@Transactional
public class TauxServiceImpl implements TauxService {

    private static final Logger LOG = LoggerFactory.getLogger(TauxServiceImpl.class);

    private final TauxRepository tauxRepository;

    private final TauxMapper tauxMapper;

    public TauxServiceImpl(TauxRepository tauxRepository, TauxMapper tauxMapper) {
        this.tauxRepository = tauxRepository;
        this.tauxMapper = tauxMapper;
    }

    @Override
    public TauxDTO save(TauxDTO tauxDTO) {
        LOG.debug("Request to save Taux : {}", tauxDTO);
        Taux taux = tauxMapper.toEntity(tauxDTO);
        taux = tauxRepository.save(taux);
        return tauxMapper.toDto(taux);
    }

    @Override
    public TauxDTO update(TauxDTO tauxDTO) {
        LOG.debug("Request to update Taux : {}", tauxDTO);
        Taux taux = tauxMapper.toEntity(tauxDTO);
        taux = tauxRepository.save(taux);
        return tauxMapper.toDto(taux);
    }

    @Override
    public Optional<TauxDTO> partialUpdate(TauxDTO tauxDTO) {
        LOG.debug("Request to partially update Taux : {}", tauxDTO);

        return tauxRepository
            .findById(tauxDTO.getId())
            .map(existingTaux -> {
                tauxMapper.partialUpdate(existingTaux, tauxDTO);

                return existingTaux;
            })
            .map(tauxRepository::save)
            .map(tauxMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TauxDTO> findAll() {
        LOG.debug("Request to get all Tauxes");
        return tauxRepository.findAll().stream().map(tauxMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TauxDTO> findOne(Long id) {
        LOG.debug("Request to get Taux : {}", id);
        return tauxRepository.findById(id).map(tauxMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Taux : {}", id);
        tauxRepository.deleteById(id);
    }
}
