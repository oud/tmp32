package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Produit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProduitDTO implements Serializable {

    private Long id;

    @NotNull
    private String codeProduit;

    @NotNull
    private LocalDate dateAdhesionProduit;

    private LocalDate dateRadiationProduit;

    @NotNull
    private String codeFormule;

    @NotNull
    private String codeFamilleRisqueFormule;

    @NotNull
    private String titreFormule;

    @NotNull
    private String typeFormule;

    @NotNull
    private String codeEtat;

    private GroupeDTO groupe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public LocalDate getDateAdhesionProduit() {
        return dateAdhesionProduit;
    }

    public void setDateAdhesionProduit(LocalDate dateAdhesionProduit) {
        this.dateAdhesionProduit = dateAdhesionProduit;
    }

    public LocalDate getDateRadiationProduit() {
        return dateRadiationProduit;
    }

    public void setDateRadiationProduit(LocalDate dateRadiationProduit) {
        this.dateRadiationProduit = dateRadiationProduit;
    }

    public String getCodeFormule() {
        return codeFormule;
    }

    public void setCodeFormule(String codeFormule) {
        this.codeFormule = codeFormule;
    }

    public String getCodeFamilleRisqueFormule() {
        return codeFamilleRisqueFormule;
    }

    public void setCodeFamilleRisqueFormule(String codeFamilleRisqueFormule) {
        this.codeFamilleRisqueFormule = codeFamilleRisqueFormule;
    }

    public String getTitreFormule() {
        return titreFormule;
    }

    public void setTitreFormule(String titreFormule) {
        this.titreFormule = titreFormule;
    }

    public String getTypeFormule() {
        return typeFormule;
    }

    public void setTypeFormule(String typeFormule) {
        this.typeFormule = typeFormule;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public GroupeDTO getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeDTO groupe) {
        this.groupe = groupe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, produitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", codeProduit='" + getCodeProduit() + "'" +
            ", dateAdhesionProduit='" + getDateAdhesionProduit() + "'" +
            ", dateRadiationProduit='" + getDateRadiationProduit() + "'" +
            ", codeFormule='" + getCodeFormule() + "'" +
            ", codeFamilleRisqueFormule='" + getCodeFamilleRisqueFormule() + "'" +
            ", titreFormule='" + getTitreFormule() + "'" +
            ", typeFormule='" + getTypeFormule() + "'" +
            ", codeEtat='" + getCodeEtat() + "'" +
            ", groupe=" + getGroupe() +
            "}";
    }
}
