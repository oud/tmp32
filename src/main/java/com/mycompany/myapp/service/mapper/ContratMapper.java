package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.domain.PmEntreprise;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.dto.PmEntrepriseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contrat} and its DTO {@link ContratDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContratMapper extends EntityMapper<ContratDTO, Contrat> {
    @Mapping(target = "pmEntreprise", source = "pmEntreprise", qualifiedByName = "pmEntrepriseId")
    ContratDTO toDto(Contrat s);

    @Named("pmEntrepriseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEntrepriseDTO toDtoPmEntrepriseId(PmEntreprise pmEntreprise);
}
