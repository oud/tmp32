package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Operation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationDTO implements Serializable {

    private Long id;

    @NotNull
    private String numeroOperationNiveau0;

    @NotNull
    private String etatOperation;

    @NotNull
    private LocalDate dateEffetOperation;

    private LocalDate dateDemandeOperation;

    @NotNull
    private LocalDate dateCreation;

    @NotNull
    private String codeActeGestion;

    private String numeroOperationAnnulee;

    private ContratDTO contrat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOperationNiveau0() {
        return numeroOperationNiveau0;
    }

    public void setNumeroOperationNiveau0(String numeroOperationNiveau0) {
        this.numeroOperationNiveau0 = numeroOperationNiveau0;
    }

    public String getEtatOperation() {
        return etatOperation;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public LocalDate getDateEffetOperation() {
        return dateEffetOperation;
    }

    public void setDateEffetOperation(LocalDate dateEffetOperation) {
        this.dateEffetOperation = dateEffetOperation;
    }

    public LocalDate getDateDemandeOperation() {
        return dateDemandeOperation;
    }

    public void setDateDemandeOperation(LocalDate dateDemandeOperation) {
        this.dateDemandeOperation = dateDemandeOperation;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCodeActeGestion() {
        return codeActeGestion;
    }

    public void setCodeActeGestion(String codeActeGestion) {
        this.codeActeGestion = codeActeGestion;
    }

    public String getNumeroOperationAnnulee() {
        return numeroOperationAnnulee;
    }

    public void setNumeroOperationAnnulee(String numeroOperationAnnulee) {
        this.numeroOperationAnnulee = numeroOperationAnnulee;
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
        if (!(o instanceof OperationDTO)) {
            return false;
        }

        OperationDTO operationDTO = (OperationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, operationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationDTO{" +
            "id=" + getId() +
            ", numeroOperationNiveau0='" + getNumeroOperationNiveau0() + "'" +
            ", etatOperation='" + getEtatOperation() + "'" +
            ", dateEffetOperation='" + getDateEffetOperation() + "'" +
            ", dateDemandeOperation='" + getDateDemandeOperation() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", codeActeGestion='" + getCodeActeGestion() + "'" +
            ", numeroOperationAnnulee='" + getNumeroOperationAnnulee() + "'" +
            ", contrat=" + getContrat() +
            "}";
    }
}
