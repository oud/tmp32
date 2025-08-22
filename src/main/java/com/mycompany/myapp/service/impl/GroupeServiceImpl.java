package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.repository.GroupeRepository;
import com.mycompany.myapp.service.GroupeService;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.service.mapper.GroupeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Groupe}.
 */
@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupeServiceImpl.class);

    private final GroupeRepository groupeRepository;

    private final GroupeMapper groupeMapper;

    public GroupeServiceImpl(GroupeRepository groupeRepository, GroupeMapper groupeMapper) {
        this.groupeRepository = groupeRepository;
        this.groupeMapper = groupeMapper;
    }

    @Override
    public GroupeDTO save(GroupeDTO groupeDTO) {
        LOG.debug("Request to save Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    @Override
    public GroupeDTO update(GroupeDTO groupeDTO) {
        LOG.debug("Request to update Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    @Override
    public Optional<GroupeDTO> partialUpdate(GroupeDTO groupeDTO) {
        LOG.debug("Request to partially update Groupe : {}", groupeDTO);

        return groupeRepository
            .findById(groupeDTO.getId())
            .map(existingGroupe -> {
                groupeMapper.partialUpdate(existingGroupe, groupeDTO);

                return existingGroupe;
            })
            .map(groupeRepository::save)
            .map(groupeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupeDTO> findAll() {
        LOG.debug("Request to get all Groupes");
        return groupeRepository.findAll().stream().map(groupeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GroupeDTO> findOne(Long id) {
        LOG.debug("Request to get Groupe : {}", id);
        return groupeRepository.findById(id).map(groupeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Groupe : {}", id);
        groupeRepository.deleteById(id);
    }
}
