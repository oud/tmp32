package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A MiseEnGestion.
 */
@Entity
@Table(name = "mise_en_gestion")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MiseEnGestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code_type_mise_en_gestion", nullable = false)
    private String codeTypeMiseEnGestion;

    @NotNull
    @Column(name = "code_offre", nullable = false)
    private String codeOffre;

    @NotNull
    @Column(name = "date_effet", nullable = false)
    private LocalDate dateEffet;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_mise_en_gestion__demandexrm",
        joinColumns = @JoinColumn(name = "mise_en_gestion_id"),
        inverseJoinColumns = @JoinColumn(name = "demandexrm_id")
    )
    @JsonIgnoreProperties(value = { "miseEnGestions" }, allowSetters = true)
    private Set<DemandeXRM> demandeXRMS = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_mise_en_gestion__pm_etablissement",
        joinColumns = @JoinColumn(name = "mise_en_gestion_id"),
        inverseJoinColumns = @JoinColumn(name = "pm_etablissement_id")
    )
    @JsonIgnoreProperties(value = { "adresses", "emails", "telephones", "miseEnGestions", "groupe", "pmEntreprise" }, allowSetters = true)
    private Set<PmEtablissement> pmEtablissements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MiseEnGestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeMiseEnGestion() {
        return this.codeTypeMiseEnGestion;
    }

    public MiseEnGestion codeTypeMiseEnGestion(String codeTypeMiseEnGestion) {
        this.setCodeTypeMiseEnGestion(codeTypeMiseEnGestion);
        return this;
    }

    public void setCodeTypeMiseEnGestion(String codeTypeMiseEnGestion) {
        this.codeTypeMiseEnGestion = codeTypeMiseEnGestion;
    }

    public String getCodeOffre() {
        return this.codeOffre;
    }

    public MiseEnGestion codeOffre(String codeOffre) {
        this.setCodeOffre(codeOffre);
        return this;
    }

    public void setCodeOffre(String codeOffre) {
        this.codeOffre = codeOffre;
    }

    public LocalDate getDateEffet() {
        return this.dateEffet;
    }

    public MiseEnGestion dateEffet(LocalDate dateEffet) {
        this.setDateEffet(dateEffet);
        return this;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public Set<DemandeXRM> getDemandeXRMS() {
        return this.demandeXRMS;
    }

    public void setDemandeXRMS(Set<DemandeXRM> demandeXRMS) {
        this.demandeXRMS = demandeXRMS;
    }

    public MiseEnGestion demandeXRMS(Set<DemandeXRM> demandeXRMS) {
        this.setDemandeXRMS(demandeXRMS);
        return this;
    }

    public MiseEnGestion addDemandeXRM(DemandeXRM demandeXRM) {
        this.demandeXRMS.add(demandeXRM);
        return this;
    }

    public MiseEnGestion removeDemandeXRM(DemandeXRM demandeXRM) {
        this.demandeXRMS.remove(demandeXRM);
        return this;
    }

    public Set<PmEtablissement> getPmEtablissements() {
        return this.pmEtablissements;
    }

    public void setPmEtablissements(Set<PmEtablissement> pmEtablissements) {
        this.pmEtablissements = pmEtablissements;
    }

    public MiseEnGestion pmEtablissements(Set<PmEtablissement> pmEtablissements) {
        this.setPmEtablissements(pmEtablissements);
        return this;
    }

    public MiseEnGestion addPmEtablissement(PmEtablissement pmEtablissement) {
        this.pmEtablissements.add(pmEtablissement);
        return this;
    }

    public MiseEnGestion removePmEtablissement(PmEtablissement pmEtablissement) {
        this.pmEtablissements.remove(pmEtablissement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MiseEnGestion)) {
            return false;
        }
        return getId() != null && getId().equals(((MiseEnGestion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MiseEnGestion{" +
            "id=" + getId() +
            ", codeTypeMiseEnGestion='" + getCodeTypeMiseEnGestion() + "'" +
            ", codeOffre='" + getCodeOffre() + "'" +
            ", dateEffet='" + getDateEffet() + "'" +
            "}";
    }
}
