package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code_produit", nullable = false)
    private String codeProduit;

    @NotNull
    @Column(name = "date_adhesion_produit", nullable = false)
    private LocalDate dateAdhesionProduit;

    @Column(name = "date_radiation_produit")
    private LocalDate dateRadiationProduit;

    @NotNull
    @Column(name = "code_formule", nullable = false)
    private String codeFormule;

    @NotNull
    @Column(name = "code_famille_risque_formule", nullable = false)
    private String codeFamilleRisqueFormule;

    @NotNull
    @Column(name = "titre_formule", nullable = false)
    private String titreFormule;

    @NotNull
    @Column(name = "type_formule", nullable = false)
    private String typeFormule;

    @NotNull
    @Column(name = "code_etat", nullable = false)
    private String codeEtat;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
    @JsonIgnoreProperties(value = { "tauxes", "produit" }, allowSetters = true)
    private Set<Garantie> garanties = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "pmEtablissements", "produits", "contrat" }, allowSetters = true)
    private Groupe groupe;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Produit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProduit() {
        return this.codeProduit;
    }

    public Produit codeProduit(String codeProduit) {
        this.setCodeProduit(codeProduit);
        return this;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public LocalDate getDateAdhesionProduit() {
        return this.dateAdhesionProduit;
    }

    public Produit dateAdhesionProduit(LocalDate dateAdhesionProduit) {
        this.setDateAdhesionProduit(dateAdhesionProduit);
        return this;
    }

    public void setDateAdhesionProduit(LocalDate dateAdhesionProduit) {
        this.dateAdhesionProduit = dateAdhesionProduit;
    }

    public LocalDate getDateRadiationProduit() {
        return this.dateRadiationProduit;
    }

    public Produit dateRadiationProduit(LocalDate dateRadiationProduit) {
        this.setDateRadiationProduit(dateRadiationProduit);
        return this;
    }

    public void setDateRadiationProduit(LocalDate dateRadiationProduit) {
        this.dateRadiationProduit = dateRadiationProduit;
    }

    public String getCodeFormule() {
        return this.codeFormule;
    }

    public Produit codeFormule(String codeFormule) {
        this.setCodeFormule(codeFormule);
        return this;
    }

    public void setCodeFormule(String codeFormule) {
        this.codeFormule = codeFormule;
    }

    public String getCodeFamilleRisqueFormule() {
        return this.codeFamilleRisqueFormule;
    }

    public Produit codeFamilleRisqueFormule(String codeFamilleRisqueFormule) {
        this.setCodeFamilleRisqueFormule(codeFamilleRisqueFormule);
        return this;
    }

    public void setCodeFamilleRisqueFormule(String codeFamilleRisqueFormule) {
        this.codeFamilleRisqueFormule = codeFamilleRisqueFormule;
    }

    public String getTitreFormule() {
        return this.titreFormule;
    }

    public Produit titreFormule(String titreFormule) {
        this.setTitreFormule(titreFormule);
        return this;
    }

    public void setTitreFormule(String titreFormule) {
        this.titreFormule = titreFormule;
    }

    public String getTypeFormule() {
        return this.typeFormule;
    }

    public Produit typeFormule(String typeFormule) {
        this.setTypeFormule(typeFormule);
        return this;
    }

    public void setTypeFormule(String typeFormule) {
        this.typeFormule = typeFormule;
    }

    public String getCodeEtat() {
        return this.codeEtat;
    }

    public Produit codeEtat(String codeEtat) {
        this.setCodeEtat(codeEtat);
        return this;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public Set<Garantie> getGaranties() {
        return this.garanties;
    }

    public void setGaranties(Set<Garantie> garanties) {
        if (this.garanties != null) {
            this.garanties.forEach(i -> i.setProduit(null));
        }
        if (garanties != null) {
            garanties.forEach(i -> i.setProduit(this));
        }
        this.garanties = garanties;
    }

    public Produit garanties(Set<Garantie> garanties) {
        this.setGaranties(garanties);
        return this;
    }

    public Produit addGarantie(Garantie garantie) {
        this.garanties.add(garantie);
        garantie.setProduit(this);
        return this;
    }

    public Produit removeGarantie(Garantie garantie) {
        this.garanties.remove(garantie);
        garantie.setProduit(null);
        return this;
    }

    public Groupe getGroupe() {
        return this.groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Produit groupe(Groupe groupe) {
        this.setGroupe(groupe);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return getId() != null && getId().equals(((Produit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", codeProduit='" + getCodeProduit() + "'" +
            ", dateAdhesionProduit='" + getDateAdhesionProduit() + "'" +
            ", dateRadiationProduit='" + getDateRadiationProduit() + "'" +
            ", codeFormule='" + getCodeFormule() + "'" +
            ", codeFamilleRisqueFormule='" + getCodeFamilleRisqueFormule() + "'" +
            ", titreFormule='" + getTitreFormule() + "'" +
            ", typeFormule='" + getTypeFormule() + "'" +
            ", codeEtat='" + getCodeEtat() + "'" +
            "}";
    }
}
