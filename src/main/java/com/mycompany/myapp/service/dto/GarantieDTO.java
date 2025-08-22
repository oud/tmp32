package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Garantie} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GarantieDTO implements Serializable {

    private Long id;

    @NotNull
    private String codeGarantieTechnique;

    @NotNull
    private String codeEtatGarantie;

    @NotNull
    private LocalDate dateAdhesionGarantie;

    @NotNull
    private LocalDate dateRadiationGarantie;

    @NotNull
    private String codeAssureur;

    @NotNull
    private String codeFormule;

    @NotNull
    private String codePack;

    @NotNull
    private String typePack;

    @NotNull
    private String titrePack;

    @NotNull
    private String codeSousPack;

    @NotNull
    private String typeSousPack;

    @NotNull
    private String titreSousPack;

    @NotNull
    private String codeTypePrestations;

    private ProduitDTO produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeGarantieTechnique() {
        return codeGarantieTechnique;
    }

    public void setCodeGarantieTechnique(String codeGarantieTechnique) {
        this.codeGarantieTechnique = codeGarantieTechnique;
    }

    public String getCodeEtatGarantie() {
        return codeEtatGarantie;
    }

    public void setCodeEtatGarantie(String codeEtatGarantie) {
        this.codeEtatGarantie = codeEtatGarantie;
    }

    public LocalDate getDateAdhesionGarantie() {
        return dateAdhesionGarantie;
    }

    public void setDateAdhesionGarantie(LocalDate dateAdhesionGarantie) {
        this.dateAdhesionGarantie = dateAdhesionGarantie;
    }

    public LocalDate getDateRadiationGarantie() {
        return dateRadiationGarantie;
    }

    public void setDateRadiationGarantie(LocalDate dateRadiationGarantie) {
        this.dateRadiationGarantie = dateRadiationGarantie;
    }

    public String getCodeAssureur() {
        return codeAssureur;
    }

    public void setCodeAssureur(String codeAssureur) {
        this.codeAssureur = codeAssureur;
    }

    public String getCodeFormule() {
        return codeFormule;
    }

    public void setCodeFormule(String codeFormule) {
        this.codeFormule = codeFormule;
    }

    public String getCodePack() {
        return codePack;
    }

    public void setCodePack(String codePack) {
        this.codePack = codePack;
    }

    public String getTypePack() {
        return typePack;
    }

    public void setTypePack(String typePack) {
        this.typePack = typePack;
    }

    public String getTitrePack() {
        return titrePack;
    }

    public void setTitrePack(String titrePack) {
        this.titrePack = titrePack;
    }

    public String getCodeSousPack() {
        return codeSousPack;
    }

    public void setCodeSousPack(String codeSousPack) {
        this.codeSousPack = codeSousPack;
    }

    public String getTypeSousPack() {
        return typeSousPack;
    }

    public void setTypeSousPack(String typeSousPack) {
        this.typeSousPack = typeSousPack;
    }

    public String getTitreSousPack() {
        return titreSousPack;
    }

    public void setTitreSousPack(String titreSousPack) {
        this.titreSousPack = titreSousPack;
    }

    public String getCodeTypePrestations() {
        return codeTypePrestations;
    }

    public void setCodeTypePrestations(String codeTypePrestations) {
        this.codeTypePrestations = codeTypePrestations;
    }

    public ProduitDTO getProduit() {
        return produit;
    }

    public void setProduit(ProduitDTO produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GarantieDTO)) {
            return false;
        }

        GarantieDTO garantieDTO = (GarantieDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, garantieDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GarantieDTO{" +
            "id=" + getId() +
            ", codeGarantieTechnique='" + getCodeGarantieTechnique() + "'" +
            ", codeEtatGarantie='" + getCodeEtatGarantie() + "'" +
            ", dateAdhesionGarantie='" + getDateAdhesionGarantie() + "'" +
            ", dateRadiationGarantie='" + getDateRadiationGarantie() + "'" +
            ", codeAssureur='" + getCodeAssureur() + "'" +
            ", codeFormule='" + getCodeFormule() + "'" +
            ", codePack='" + getCodePack() + "'" +
            ", typePack='" + getTypePack() + "'" +
            ", titrePack='" + getTitrePack() + "'" +
            ", codeSousPack='" + getCodeSousPack() + "'" +
            ", typeSousPack='" + getTypeSousPack() + "'" +
            ", titreSousPack='" + getTitreSousPack() + "'" +
            ", codeTypePrestations='" + getCodeTypePrestations() + "'" +
            ", produit=" + getProduit() +
            "}";
    }
}
