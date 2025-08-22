package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * A Taux.
 */
@Entity
@Table(name = "taux")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Taux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code_variable_declarative", nullable = false)
    private String codeVariableDeclarative;

    @NotNull
    @Column(name = "unite_variable_declarative", nullable = false)
    private String uniteVariableDeclarative;

    @Column(name = "valeur_facteur_montant")
    private String valeurFacteurMontant;

    @NotNull
    @Column(name = "valeur_facteur_taux", nullable = false)
    private String valeurFacteurTaux;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tauxes", "produit" }, allowSetters = true)
    private Garantie garantie;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Taux id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeVariableDeclarative() {
        return this.codeVariableDeclarative;
    }

    public Taux codeVariableDeclarative(String codeVariableDeclarative) {
        this.setCodeVariableDeclarative(codeVariableDeclarative);
        return this;
    }

    public void setCodeVariableDeclarative(String codeVariableDeclarative) {
        this.codeVariableDeclarative = codeVariableDeclarative;
    }

    public String getUniteVariableDeclarative() {
        return this.uniteVariableDeclarative;
    }

    public Taux uniteVariableDeclarative(String uniteVariableDeclarative) {
        this.setUniteVariableDeclarative(uniteVariableDeclarative);
        return this;
    }

    public void setUniteVariableDeclarative(String uniteVariableDeclarative) {
        this.uniteVariableDeclarative = uniteVariableDeclarative;
    }

    public String getValeurFacteurMontant() {
        return this.valeurFacteurMontant;
    }

    public Taux valeurFacteurMontant(String valeurFacteurMontant) {
        this.setValeurFacteurMontant(valeurFacteurMontant);
        return this;
    }

    public void setValeurFacteurMontant(String valeurFacteurMontant) {
        this.valeurFacteurMontant = valeurFacteurMontant;
    }

    public String getValeurFacteurTaux() {
        return this.valeurFacteurTaux;
    }

    public Taux valeurFacteurTaux(String valeurFacteurTaux) {
        this.setValeurFacteurTaux(valeurFacteurTaux);
        return this;
    }

    public void setValeurFacteurTaux(String valeurFacteurTaux) {
        this.valeurFacteurTaux = valeurFacteurTaux;
    }

    public Garantie getGarantie() {
        return this.garantie;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

    public Taux garantie(Garantie garantie) {
        this.setGarantie(garantie);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Taux)) {
            return false;
        }
        return getId() != null && getId().equals(((Taux) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taux{" +
            "id=" + getId() +
            ", codeVariableDeclarative='" + getCodeVariableDeclarative() + "'" +
            ", uniteVariableDeclarative='" + getUniteVariableDeclarative() + "'" +
            ", valeurFacteurMontant='" + getValeurFacteurMontant() + "'" +
            ", valeurFacteurTaux='" + getValeurFacteurTaux() + "'" +
            "}";
    }
}
