package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.domain.MiseEnGestion;
import com.mycompany.myapp.domain.PmEntreprise;
import com.mycompany.myapp.domain.PmEtablissement;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.service.dto.MiseEnGestionDTO;
import com.mycompany.myapp.service.dto.PmEntrepriseDTO;
import com.mycompany.myapp.service.dto.PmEtablissementDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PmEtablissement} and its DTO {@link PmEtablissementDTO}.
 */
@Mapper(componentModel = "spring")
public interface PmEtablissementMapper extends EntityMapper<PmEtablissementDTO, PmEtablissement> {
    @Mapping(target = "miseEnGestions", source = "miseEnGestions", qualifiedByName = "miseEnGestionIdSet")
    @Mapping(target = "groupe", source = "groupe", qualifiedByName = "groupeId")
    @Mapping(target = "pmEntreprise", source = "pmEntreprise", qualifiedByName = "pmEntrepriseId")
    PmEtablissementDTO toDto(PmEtablissement s);

    @Mapping(target = "miseEnGestions", ignore = true)
    @Mapping(target = "removeMiseEnGestion", ignore = true)
    PmEtablissement toEntity(PmEtablissementDTO pmEtablissementDTO);

    @Named("miseEnGestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MiseEnGestionDTO toDtoMiseEnGestionId(MiseEnGestion miseEnGestion);

    @Named("miseEnGestionIdSet")
    default Set<MiseEnGestionDTO> toDtoMiseEnGestionIdSet(Set<MiseEnGestion> miseEnGestion) {
        return miseEnGestion.stream().map(this::toDtoMiseEnGestionId).collect(Collectors.toSet());
    }

    @Named("groupeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GroupeDTO toDtoGroupeId(Groupe groupe);

    @Named("pmEntrepriseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEntrepriseDTO toDtoPmEntrepriseId(PmEntreprise pmEntreprise);
}
