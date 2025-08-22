package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DemandeXRM;
import com.mycompany.myapp.domain.MiseEnGestion;
import com.mycompany.myapp.service.dto.DemandeXRMDTO;
import com.mycompany.myapp.service.dto.MiseEnGestionDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DemandeXRM} and its DTO {@link DemandeXRMDTO}.
 */
@Mapper(componentModel = "spring")
public interface DemandeXRMMapper extends EntityMapper<DemandeXRMDTO, DemandeXRM> {
    @Mapping(target = "miseEnGestions", source = "miseEnGestions", qualifiedByName = "miseEnGestionIdSet")
    DemandeXRMDTO toDto(DemandeXRM s);

    @Mapping(target = "miseEnGestions", ignore = true)
    @Mapping(target = "removeMiseEnGestion", ignore = true)
    DemandeXRM toEntity(DemandeXRMDTO demandeXRMDTO);

    @Named("miseEnGestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MiseEnGestionDTO toDtoMiseEnGestionId(MiseEnGestion miseEnGestion);

    @Named("miseEnGestionIdSet")
    default Set<MiseEnGestionDTO> toDtoMiseEnGestionIdSet(Set<MiseEnGestion> miseEnGestion) {
        return miseEnGestion.stream().map(this::toDtoMiseEnGestionId).collect(Collectors.toSet());
    }
}
