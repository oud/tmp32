package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Taux} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TauxDTO implements Serializable {

    private Long id;

    @NotNull
    private String codeVariableDeclarative;

    @NotNull
    private String uniteVariableDeclarative;

    private String valeurFacteurMontant;

    @NotNull
    private String valeurFacteurTaux;

    private GarantieDTO garantie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeVariableDeclarative() {
        return codeVariableDeclarative;
    }

    public void setCodeVariableDeclarative(String codeVariableDeclarative) {
        this.codeVariableDeclarative = codeVariableDeclarative;
    }

    public String getUniteVariableDeclarative() {
        return uniteVariableDeclarative;
    }

    public void setUniteVariableDeclarative(String uniteVariableDeclarative) {
        this.uniteVariableDeclarative = uniteVariableDeclarative;
    }

    public String getValeurFacteurMontant() {
        return valeurFacteurMontant;
    }

    public void setValeurFacteurMontant(String valeurFacteurMontant) {
        this.valeurFacteurMontant = valeurFacteurMontant;
    }

    public String getValeurFacteurTaux() {
        return valeurFacteurTaux;
    }

    public void setValeurFacteurTaux(String valeurFacteurTaux) {
        this.valeurFacteurTaux = valeurFacteurTaux;
    }

    public GarantieDTO getGarantie() {
        return garantie;
    }

    public void setGarantie(GarantieDTO garantie) {
        this.garantie = garantie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TauxDTO)) {
            return false;
        }

        TauxDTO tauxDTO = (TauxDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tauxDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TauxDTO{" +
            "id=" + getId() +
            ", codeVariableDeclarative='" + getCodeVariableDeclarative() + "'" +
            ", uniteVariableDeclarative='" + getUniteVariableDeclarative() + "'" +
            ", valeurFacteurMontant='" + getValeurFacteurMontant() + "'" +
            ", valeurFacteurTaux='" + getValeurFacteurTaux() + "'" +
            ", garantie=" + getGarantie() +
            "}";
    }
}
