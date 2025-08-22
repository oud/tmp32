package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Groupe;
import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.service.dto.GroupeDTO;
import com.mycompany.myapp.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Mapping(target = "groupe", source = "groupe", qualifiedByName = "groupeId")
    ProduitDTO toDto(Produit s);

    @Named("groupeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GroupeDTO toDtoGroupeId(Groupe groupe);
}
