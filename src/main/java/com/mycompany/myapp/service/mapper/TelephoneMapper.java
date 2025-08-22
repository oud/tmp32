package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.PmEtablissement;
import com.mycompany.myapp.domain.Telephone;
import com.mycompany.myapp.service.dto.PmEtablissementDTO;
import com.mycompany.myapp.service.dto.TelephoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Telephone} and its DTO {@link TelephoneDTO}.
 */
@Mapper(componentModel = "spring")
public interface TelephoneMapper extends EntityMapper<TelephoneDTO, Telephone> {
    @Mapping(target = "pmEtablissement", source = "pmEtablissement", qualifiedByName = "pmEtablissementId")
    TelephoneDTO toDto(Telephone s);

    @Named("pmEtablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PmEtablissementDTO toDtoPmEtablissementId(PmEtablissement pmEtablissement);
}
