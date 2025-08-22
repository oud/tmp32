package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Operation.
 */
@Entity
@Table(name = "operation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_operation_niveau_0", nullable = false)
    private String numeroOperationNiveau0;

    @NotNull
    @Column(name = "etat_operation", nullable = false)
    private String etatOperation;

    @NotNull
    @Column(name = "date_effet_operation", nullable = false)
    private LocalDate dateEffetOperation;

    @Column(name = "date_demande_operation")
    private LocalDate dateDemandeOperation;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @NotNull
    @Column(name = "code_acte_gestion", nullable = false)
    private String codeActeGestion;

    @Column(name = "numero_operation_annulee")
    private String numeroOperationAnnulee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "pmEntreprise", "groupes", "operations" }, allowSetters = true)
    private Contrat contrat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Operation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOperationNiveau0() {
        return this.numeroOperationNiveau0;
    }

    public Operation numeroOperationNiveau0(String numeroOperationNiveau0) {
        this.setNumeroOperationNiveau0(numeroOperationNiveau0);
        return this;
    }

    public void setNumeroOperationNiveau0(String numeroOperationNiveau0) {
        this.numeroOperationNiveau0 = numeroOperationNiveau0;
    }

    public String getEtatOperation() {
        return this.etatOperation;
    }

    public Operation etatOperation(String etatOperation) {
        this.setEtatOperation(etatOperation);
        return this;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public LocalDate getDateEffetOperation() {
        return this.dateEffetOperation;
    }

    public Operation dateEffetOperation(LocalDate dateEffetOperation) {
        this.setDateEffetOperation(dateEffetOperation);
        return this;
    }

    public void setDateEffetOperation(LocalDate dateEffetOperation) {
        this.dateEffetOperation = dateEffetOperation;
    }

    public LocalDate getDateDemandeOperation() {
        return this.dateDemandeOperation;
    }

    public Operation dateDemandeOperation(LocalDate dateDemandeOperation) {
        this.setDateDemandeOperation(dateDemandeOperation);
        return this;
    }

    public void setDateDemandeOperation(LocalDate dateDemandeOperation) {
        this.dateDemandeOperation = dateDemandeOperation;
    }

    public LocalDate getDateCreation() {
        return this.dateCreation;
    }

    public Operation dateCreation(LocalDate dateCreation) {
        this.setDateCreation(dateCreation);
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCodeActeGestion() {
        return this.codeActeGestion;
    }

    public Operation codeActeGestion(String codeActeGestion) {
        this.setCodeActeGestion(codeActeGestion);
        return this;
    }

    public void setCodeActeGestion(String codeActeGestion) {
        this.codeActeGestion = codeActeGestion;
    }

    public String getNumeroOperationAnnulee() {
        return this.numeroOperationAnnulee;
    }

    public Operation numeroOperationAnnulee(String numeroOperationAnnulee) {
        this.setNumeroOperationAnnulee(numeroOperationAnnulee);
        return this;
    }

    public void setNumeroOperationAnnulee(String numeroOperationAnnulee) {
        this.numeroOperationAnnulee = numeroOperationAnnulee;
    }

    public Contrat getContrat() {
        return this.contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Operation contrat(Contrat contrat) {
        this.setContrat(contrat);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operation)) {
            return false;
        }
        return getId() != null && getId().equals(((Operation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Operation{" +
            "id=" + getId() +
            ", numeroOperationNiveau0='" + getNumeroOperationNiveau0() + "'" +
            ", etatOperation='" + getEtatOperation() + "'" +
            ", dateEffetOperation='" + getDateEffetOperation() + "'" +
            ", dateDemandeOperation='" + getDateDemandeOperation() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", codeActeGestion='" + getCodeActeGestion() + "'" +
            ", numeroOperationAnnulee='" + getNumeroOperationAnnulee() + "'" +
            "}";
    }
}
