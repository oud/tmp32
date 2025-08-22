package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.dto.GroupeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Groupe} and its DTO {@link GroupeDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupeMapper extends EntityMapper<GroupeDTO, Groupe> {
    @Mapping(target = "contrat", source = "contrat", qualifiedByName = "contratId")
    GroupeDTO toDto(Groupe s);

    @Named("contratId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContratDTO toDtoContratId(Contrat contrat);
}
