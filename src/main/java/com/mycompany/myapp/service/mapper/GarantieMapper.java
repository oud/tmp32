package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Garantie;
import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.service.dto.GarantieDTO;
import com.mycompany.myapp.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Garantie} and its DTO {@link GarantieDTO}.
 */
@Mapper(componentModel = "spring")
public interface GarantieMapper extends EntityMapper<GarantieDTO, Garantie> {
    @Mapping(target = "produit", source = "produit", qualifiedByName = "produitId")
    GarantieDTO toDto(Garantie s);

    @Named("produitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProduitDTO toDtoProduitId(Produit produit);
}
