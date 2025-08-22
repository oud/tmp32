package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Garantie.
 */
@Entity
@Table(name = "garantie")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Garantie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code_garantie_technique", nullable = false)
    private String codeGarantieTechnique;

    @NotNull
    @Column(name = "code_etat_garantie", nullable = false)
    private String codeEtatGarantie;

    @NotNull
    @Column(name = "date_adhesion_garantie", nullable = false)
    private LocalDate dateAdhesionGarantie;

    @NotNull
    @Column(name = "date_radiation_garantie", nullable = false)
    private LocalDate dateRadiationGarantie;

    @NotNull
    @Column(name = "code_assureur", nullable = false)
    private String codeAssureur;

    @NotNull
    @Column(name = "code_formule", nullable = false)
    private String codeFormule;

    @NotNull
    @Column(name = "code_pack", nullable = false)
    private String codePack;

    @NotNull
    @Column(name = "type_pack", nullable = false)
    private String typePack;

    @NotNull
    @Column(name = "titre_pack", nullable = false)
    private String titrePack;

    @NotNull
    @Column(name = "code_sous_pack", nullable = false)
    private String codeSousPack;

    @NotNull
    @Column(name = "type_sous_pack", nullable = false)
    private String typeSousPack;

    @NotNull
    @Column(name = "titre_sous_pack", nullable = false)
    private String titreSousPack;

    @NotNull
    @Column(name = "code_type_prestations", nullable = false)
    private String codeTypePrestations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
    @JsonIgnoreProperties(value = { "garantie" }, allowSetters = true)
    private Set<Taux> tauxes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "garanties", "groupe" }, allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Garantie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeGarantieTechnique() {
        return this.codeGarantieTechnique;
    }

    public Garantie codeGarantieTechnique(String codeGarantieTechnique) {
        this.setCodeGarantieTechnique(codeGarantieTechnique);
        return this;
    }

    public void setCodeGarantieTechnique(String codeGarantieTechnique) {
        this.codeGarantieTechnique = codeGarantieTechnique;
    }

    public String getCodeEtatGarantie() {
        return this.codeEtatGarantie;
    }

    public Garantie codeEtatGarantie(String codeEtatGarantie) {
        this.setCodeEtatGarantie(codeEtatGarantie);
        return this;
    }

    public void setCodeEtatGarantie(String codeEtatGarantie) {
        this.codeEtatGarantie = codeEtatGarantie;
    }

    public LocalDate getDateAdhesionGarantie() {
        return this.dateAdhesionGarantie;
    }

    public Garantie dateAdhesionGarantie(LocalDate dateAdhesionGarantie) {
        this.setDateAdhesionGarantie(dateAdhesionGarantie);
        return this;
    }

    public void setDateAdhesionGarantie(LocalDate dateAdhesionGarantie) {
        this.dateAdhesionGarantie = dateAdhesionGarantie;
    }

    public LocalDate getDateRadiationGarantie() {
        return this.dateRadiationGarantie;
    }

    public Garantie dateRadiationGarantie(LocalDate dateRadiationGarantie) {
        this.setDateRadiationGarantie(dateRadiationGarantie);
        return this;
    }

    public void setDateRadiationGarantie(LocalDate dateRadiationGarantie) {
        this.dateRadiationGarantie = dateRadiationGarantie;
    }

    public String getCodeAssureur() {
        return this.codeAssureur;
    }

    public Garantie codeAssureur(String codeAssureur) {
        this.setCodeAssureur(codeAssureur);
        return this;
    }

    public void setCodeAssureur(String codeAssureur) {
        this.codeAssureur = codeAssureur;
    }

    public String getCodeFormule() {
        return this.codeFormule;
    }

    public Garantie codeFormule(String codeFormule) {
        this.setCodeFormule(codeFormule);
        return this;
    }

    public void setCodeFormule(String codeFormule) {
        this.codeFormule = codeFormule;
    }

    public String getCodePack() {
        return this.codePack;
    }

    public Garantie codePack(String codePack) {
        this.setCodePack(codePack);
        return this;
    }

    public void setCodePack(String codePack) {
        this.codePack = codePack;
    }

    public String getTypePack() {
        return this.typePack;
    }

    public Garantie typePack(String typePack) {
        this.setTypePack(typePack);
        return this;
    }

    public void setTypePack(String typePack) {
        this.typePack = typePack;
    }

    public String getTitrePack() {
        return this.titrePack;
    }

    public Garantie titrePack(String titrePack) {
        this.setTitrePack(titrePack);
        return this;
    }

    public void setTitrePack(String titrePack) {
        this.titrePack = titrePack;
    }

    public String getCodeSousPack() {
        return this.codeSousPack;
    }

    public Garantie codeSousPack(String codeSousPack) {
        this.setCodeSousPack(codeSousPack);
        return this;
    }

    public void setCodeSousPack(String codeSousPack) {
        this.codeSousPack = codeSousPack;
    }

    public String getTypeSousPack() {
        return this.typeSousPack;
    }

    public Garantie typeSousPack(String typeSousPack) {
        this.setTypeSousPack(typeSousPack);
        return this;
    }

    public void setTypeSousPack(String typeSousPack) {
        this.typeSousPack = typeSousPack;
    }

    public String getTitreSousPack() {
        return this.titreSousPack;
    }

    public Garantie titreSousPack(String titreSousPack) {
        this.setTitreSousPack(titreSousPack);
        return this;
    }

    public void setTitreSousPack(String titreSousPack) {
        this.titreSousPack = titreSousPack;
    }

    public String getCodeTypePrestations() {
        return this.codeTypePrestations;
    }

    public Garantie codeTypePrestations(String codeTypePrestations) {
        this.setCodeTypePrestations(codeTypePrestations);
        return this;
    }

    public void setCodeTypePrestations(String codeTypePrestations) {
        this.codeTypePrestations = codeTypePrestations;
    }

    public Set<Taux> getTauxes() {
        return this.tauxes;
    }

    public void setTauxes(Set<Taux> tauxes) {
        if (this.tauxes != null) {
            this.tauxes.forEach(i -> i.setGarantie(null));
        }
        if (tauxes != null) {
            tauxes.forEach(i -> i.setGarantie(this));
        }
        this.tauxes = tauxes;
    }

    public Garantie tauxes(Set<Taux> tauxes) {
        this.setTauxes(tauxes);
        return this;
    }

    public Garantie addTaux(Taux taux) {
        this.tauxes.add(taux);
        taux.setGarantie(this);
        return this;
    }

    public Garantie removeTaux(Taux taux) {
        this.tauxes.remove(taux);
        taux.setGarantie(null);
        return this;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Garantie produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Garantie)) {
            return false;
        }
        return getId() != null && getId().equals(((Garantie) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Garantie{" +
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
            "}";
    }
}
