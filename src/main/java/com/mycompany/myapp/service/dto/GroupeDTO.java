package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Groupe} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GroupeDTO implements Serializable {

    private Long id;

    @NotNull
    private String codeGroupeAssures;

    @NotNull
    private String codeGroupePopulation;

    @NotNull
    private String typeGroupeAssures;

    @NotNull
    private LocalDate dateDebutPeriodeGroupeAssures;

    @NotNull
    private String libelleGroupeAssuresTypeAutre;

    @NotNull
    private String codeEtatGroupeAssures;

    private ContratDTO contrat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeGroupeAssures() {
        return codeGroupeAssures;
    }

    public void setCodeGroupeAssures(String codeGroupeAssures) {
        this.codeGroupeAssures = codeGroupeAssures;
    }

    public String getCodeGroupePopulation() {
        return codeGroupePopulation;
    }

    public void setCodeGroupePopulation(String codeGroupePopulation) {
        this.codeGroupePopulation = codeGroupePopulation;
    }

    public String getTypeGroupeAssures() {
        return typeGroupeAssures;
    }

    public void setTypeGroupeAssures(String typeGroupeAssures) {
        this.typeGroupeAssures = typeGroupeAssures;
    }

    public LocalDate getDateDebutPeriodeGroupeAssures() {
        return dateDebutPeriodeGroupeAssures;
    }

    public void setDateDebutPeriodeGroupeAssures(LocalDate dateDebutPeriodeGroupeAssures) {
        this.dateDebutPeriodeGroupeAssures = dateDebutPeriodeGroupeAssures;
    }

    public String getLibelleGroupeAssuresTypeAutre() {
        return libelleGroupeAssuresTypeAutre;
    }

    public void setLibelleGroupeAssuresTypeAutre(String libelleGroupeAssuresTypeAutre) {
        this.libelleGroupeAssuresTypeAutre = libelleGroupeAssuresTypeAutre;
    }

    public String getCodeEtatGroupeAssures() {
        return codeEtatGroupeAssures;
    }

    public void setCodeEtatGroupeAssures(String codeEtatGroupeAssures) {
        this.codeEtatGroupeAssures = codeEtatGroupeAssures;
    }

    public ContratDTO getContrat() {
        return contrat;
    }

    public void setContrat(ContratDTO contrat) {
        this.contrat = contrat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupeDTO)) {
            return false;
        }

        GroupeDTO groupeDTO = (GroupeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, groupeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupeDTO{" +
            "id=" + getId() +
            ", codeGroupeAssures='" + getCodeGroupeAssures() + "'" +
            ", codeGroupePopulation='" + getCodeGroupePopulation() + "'" +
            ", typeGroupeAssures='" + getTypeGroupeAssures() + "'" +
            ", dateDebutPeriodeGroupeAssures='" + getDateDebutPeriodeGroupeAssures() + "'" +
            ", libelleGroupeAssuresTypeAutre='" + getLibelleGroupeAssuresTypeAutre() + "'" +
            ", codeEtatGroupeAssures='" + getCodeEtatGroupeAssures() + "'" +
            ", contrat=" + getContrat() +
            "}";
    }
}
