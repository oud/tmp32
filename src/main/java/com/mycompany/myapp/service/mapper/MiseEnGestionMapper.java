package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DemandeXRM;
import com.mycompany.myapp.domain.MiseEnGestion;
import com.mycompany.myapp.domain.PmEtablissement;
import com.mycompany.myapp.service.dto.DemandeXRMDTO;
import com.mycompany.myapp.service.dto.MiseEnGestionDTO;
import com.mycompany.myapp.service.dto.PmEtablissementDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MiseEnGestion} and its DTO {@link MiseEnGestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface MiseEnGestionMapper extends EntityMapper<MiseEnGestionDTO, MiseEnGestion> {
    @Mapping(target = "demandeXRMS", source = "demandeXRMS", qualifiedByName = "demandeXRMIdSet")
    @Mapping(target = "pmEtablissements", source = "pmEtablissements", qualifiedByName = "pmEtablissementIdSet")
    MiseEnGestionDTO toDto(MiseEnGestion s);

    @Mapping(target = "removeDemandeXRM", ignore = true)
    @Mapping(target = "removePmEtablissement", ignore = true)
    MiseEnGestion toEntity(MiseEnGestionDTO miseEnGestionDTO);

    @Named("demandeXRMId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DemandeXRMDTO toDtoDemandeXRMId(DemandeXRM demandeXRM);

    @Named("demandeXRMIdSet")
    default Set<DemandeXRMDTO> toDtoDemandeXRMIdSet(Set<DemandeXRM> demandeXRM) {
        return demandeXRM.stream().map(this::toDtoDemandeXRMId).collect(Collectors.toSet());
    }

    @Named("pmEtablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEtablissementDTO toDtoPmEtablissementId(PmEtablissement pmEtablissement);

    @Named("pmEtablissementIdSet")
    default Set<PmEtablissementDTO> toDtoPmEtablissementIdSet(Set<PmEtablissement> pmEtablissement) {
        return pmEtablissement.stream().map(this::toDtoPmEtablissementId).collect(Collectors.toSet());
    }
}
