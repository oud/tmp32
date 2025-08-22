package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DemandeXRM;
import com.mycompany.myapp.service.dto.DemandeXRMDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DemandeXRM} and its DTO {@link DemandeXRMDTO}.
 */
@Mapper(componentModel = "spring")
public interface DemandeXRMMapper extends EntityMapper<DemandeXRMDTO, DemandeXRM> {}
