package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DemandeXRM;
import com.mycompany.myapp.domain.MiseEnGestion;
import com.mycompany.myapp.domain.PmEtablissement;
import com.mycompany.myapp.service.dto.DemandeXRMDTO;
import com.mycompany.myapp.service.dto.MiseEnGestionDTO;
import com.mycompany.myapp.service.dto.PmEtablissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MiseEnGestion} and its DTO {@link MiseEnGestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface MiseEnGestionMapper extends EntityMapper<MiseEnGestionDTO, MiseEnGestion> {
    @Mapping(target = "pmEtablissement", source = "pmEtablissement", qualifiedByName = "pmEtablissementId")
    @Mapping(target = "demandeXRM", source = "demandeXRM", qualifiedByName = "demandeXRMId")
    MiseEnGestionDTO toDto(MiseEnGestion s);

    @Named("pmEtablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEtablissementDTO toDtoPmEtablissementId(PmEtablissement pmEtablissement);

    @Named("demandeXRMId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DemandeXRMDTO toDtoDemandeXRMId(DemandeXRM demandeXRM);
}
