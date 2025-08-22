package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.domain.Operation;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.dto.OperationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Operation} and its DTO {@link OperationDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperationMapper extends EntityMapper<OperationDTO, Operation> {
    @Mapping(target = "contrat", source = "contrat", qualifiedByName = "contratId")
    OperationDTO toDto(Operation s);

    @Named("contratId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContratDTO toDtoContratId(Contrat contrat);
}
