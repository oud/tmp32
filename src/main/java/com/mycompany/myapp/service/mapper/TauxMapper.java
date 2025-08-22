package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Garantie;
import com.mycompany.myapp.domain.Taux;
import com.mycompany.myapp.service.dto.GarantieDTO;
import com.mycompany.myapp.service.dto.TauxDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Taux} and its DTO {@link TauxDTO}.
 */
@Mapper(componentModel = "spring")
public interface TauxMapper extends EntityMapper<TauxDTO, Taux> {
    @Mapping(target = "garantie", source = "garantie", qualifiedByName = "garantieId")
    TauxDTO toDto(Taux s);

    @Named("garantieId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GarantieDTO toDtoGarantieId(Garantie garantie);
}
