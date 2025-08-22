package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.domain.PmEntreprise;
import com.mycompany.myapp.domain.PmEtablissement;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.service.dto.PmEntrepriseDTO;
import com.mycompany.myapp.service.dto.PmEtablissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PmEtablissement} and its DTO {@link PmEtablissementDTO}.
 */
@Mapper(componentModel = "spring")
public interface PmEtablissementMapper extends EntityMapper<PmEtablissementDTO, PmEtablissement> {
    @Mapping(target = "groupe", source = "groupe", qualifiedByName = "groupeId")
    @Mapping(target = "pmEntreprise", source = "pmEntreprise", qualifiedByName = "pmEntrepriseId")
    PmEtablissementDTO toDto(PmEtablissement s);

    @Named("groupeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GroupeDTO toDtoGroupeId(Groupe groupe);

    @Named("pmEntrepriseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEntrepriseDTO toDtoPmEntrepriseId(PmEntreprise pmEntreprise);
}
