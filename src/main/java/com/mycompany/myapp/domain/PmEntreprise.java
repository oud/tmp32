package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A PmEntreprise.
 */
@Entity
@Table(name = "pm_entreprise")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PmEntreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_entreprise_rpg", nullable = false)
    private String idEntrepriseRPG;

    @NotNull
    @Column(name = "code_partenaire_distributeur", nullable = false)
    private String codePartenaireDistributeur;

    @NotNull
    @Column(name = "numero_siret_siege", nullable = false)
    private String numeroSiretSiege;

    @NotNull
    @Column(name = "code_etat", nullable = false)
    private String codeEtat;

    @Column(name = "libelle_etat")
    private String libelleEtat;

    @Column(name = "code_categorie_personne")
    private String codeCategoriePersonne;

    @Column(name = "libelle_categorie_personne")
    private String libelleCategoriePersonne;

    @Column(name = "code_type")
    private String codeType;

    @NotNull
    @Column(name = "date_creation_juridique", nullable = false)
    private LocalDate dateCreationJuridique;

    @Column(name = "date_etat")
    private LocalDate dateEtat;

    @Column(name = "date_fermeture_juridique")
    private LocalDate dateFermetureJuridique;

    @Column(name = "code_tranche_effectif")
    private String codeTrancheEffectif;

    @Column(name = "libelle_tranche_effectif")
    private String libelleTrancheEffectif;

    @Column(name = "date_cessation_activite")
    private LocalDate dateCessationActivite;

    @Column(name = "date_effectif_officiel")
    private LocalDate dateEffectifOfficiel;

    @Column(name = "effectif_officiel")
    private String effectifOfficiel;

    @Column(name = "code_motif_cessation_activite")
    private String codeMotifCessationActivite;

    @NotNull
    @Column(name = "siren", nullable = false)
    private String siren;

    @NotNull
    @Column(name = "code_forme_juridique", nullable = false)
    private String codeFormeJuridique;

    @NotNull
    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;

    @Column(name = "code_categorie_juridique")
    private String codeCategorieJuridique;

    @Column(name = "code_idcc")
    private String codeIDCC;

    @NotNull
    @Column(name = "code_ape", nullable = false)
    private String codeAPE;

    @Column(name = "identifiant_ai")
    private String identifiantAI;

    @Column(name = "checked")
    private Boolean checked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmEntreprise")
    @JsonIgnoreProperties(value = { "adresses", "emails", "telephones", "groupe", "pmEntreprise" }, allowSetters = true)
    private Set<PmEtablissement> pmEtablissements = new HashSet<>();

    @JsonIgnoreProperties(value = { "pmEntreprise", "groupes", "operations" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pmEntreprise")
    private Contrat contrat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PmEntreprise id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEntrepriseRPG() {
        return this.idEntrepriseRPG;
    }

    public PmEntreprise idEntrepriseRPG(String idEntrepriseRPG) {
        this.setIdEntrepriseRPG(idEntrepriseRPG);
        return this;
    }

    public void setIdEntrepriseRPG(String idEntrepriseRPG) {
        this.idEntrepriseRPG = idEntrepriseRPG;
    }

    public String getCodePartenaireDistributeur() {
        return this.codePartenaireDistributeur;
    }

    public PmEntreprise codePartenaireDistributeur(String codePartenaireDistributeur) {
        this.setCodePartenaireDistributeur(codePartenaireDistributeur);
        return this;
    }

    public void setCodePartenaireDistributeur(String codePartenaireDistributeur) {
        this.codePartenaireDistributeur = codePartenaireDistributeur;
    }

    public String getNumeroSiretSiege() {
        return this.numeroSiretSiege;
    }

    public PmEntreprise numeroSiretSiege(String numeroSiretSiege) {
        this.setNumeroSiretSiege(numeroSiretSiege);
        return this;
    }

    public void setNumeroSiretSiege(String numeroSiretSiege) {
        this.numeroSiretSiege = numeroSiretSiege;
    }

    public String getCodeEtat() {
        return this.codeEtat;
    }

    public PmEntreprise codeEtat(String codeEtat) {
        this.setCodeEtat(codeEtat);
        return this;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public String getLibelleEtat() {
        return this.libelleEtat;
    }

    public PmEntreprise libelleEtat(String libelleEtat) {
        this.setLibelleEtat(libelleEtat);
        return this;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    public String getCodeCategoriePersonne() {
        return this.codeCategoriePersonne;
    }

    public PmEntreprise codeCategoriePersonne(String codeCategoriePersonne) {
        this.setCodeCategoriePersonne(codeCategoriePersonne);
        return this;
    }

    public void setCodeCategoriePersonne(String codeCategoriePersonne) {
        this.codeCategoriePersonne = codeCategoriePersonne;
    }

    public String getLibelleCategoriePersonne() {
        return this.libelleCategoriePersonne;
    }

    public PmEntreprise libelleCategoriePersonne(String libelleCategoriePersonne) {
        this.setLibelleCategoriePersonne(libelleCategoriePersonne);
        return this;
    }

    public void setLibelleCategoriePersonne(String libelleCategoriePersonne) {
        this.libelleCategoriePersonne = libelleCategoriePersonne;
    }

    public String getCodeType() {
        return this.codeType;
    }

    public PmEntreprise codeType(String codeType) {
        this.setCodeType(codeType);
        return this;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public LocalDate getDateCreationJuridique() {
        return this.dateCreationJuridique;
    }

    public PmEntreprise dateCreationJuridique(LocalDate dateCreationJuridique) {
        this.setDateCreationJuridique(dateCreationJuridique);
        return this;
    }

    public void setDateCreationJuridique(LocalDate dateCreationJuridique) {
        this.dateCreationJuridique = dateCreationJuridique;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public PmEntreprise dateEtat(LocalDate dateEtat) {
        this.setDateEtat(dateEtat);
        return this;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public LocalDate getDateFermetureJuridique() {
        return this.dateFermetureJuridique;
    }

    public PmEntreprise dateFermetureJuridique(LocalDate dateFermetureJuridique) {
        this.setDateFermetureJuridique(dateFermetureJuridique);
        return this;
    }

    public void setDateFermetureJuridique(LocalDate dateFermetureJuridique) {
        this.dateFermetureJuridique = dateFermetureJuridique;
    }

    public String getCodeTrancheEffectif() {
        return this.codeTrancheEffectif;
    }

    public PmEntreprise codeTrancheEffectif(String codeTrancheEffectif) {
        this.setCodeTrancheEffectif(codeTrancheEffectif);
        return this;
    }

    public void setCodeTrancheEffectif(String codeTrancheEffectif) {
        this.codeTrancheEffectif = codeTrancheEffectif;
    }

    public String getLibelleTrancheEffectif() {
        return this.libelleTrancheEffectif;
    }

    public PmEntreprise libelleTrancheEffectif(String libelleTrancheEffectif) {
        this.setLibelleTrancheEffectif(libelleTrancheEffectif);
        return this;
    }

    public void setLibelleTrancheEffectif(String libelleTrancheEffectif) {
        this.libelleTrancheEffectif = libelleTrancheEffectif;
    }

    public LocalDate getDateCessationActivite() {
        return this.dateCessationActivite;
    }

    public PmEntreprise dateCessationActivite(LocalDate dateCessationActivite) {
        this.setDateCessationActivite(dateCessationActivite);
        return this;
    }

    public void setDateCessationActivite(LocalDate dateCessationActivite) {
        this.dateCessationActivite = dateCessationActivite;
    }

    public LocalDate getDateEffectifOfficiel() {
        return this.dateEffectifOfficiel;
    }

    public PmEntreprise dateEffectifOfficiel(LocalDate dateEffectifOfficiel) {
        this.setDateEffectifOfficiel(dateEffectifOfficiel);
        return this;
    }

    public void setDateEffectifOfficiel(LocalDate dateEffectifOfficiel) {
        this.dateEffectifOfficiel = dateEffectifOfficiel;
    }

    public String getEffectifOfficiel() {
        return this.effectifOfficiel;
    }

    public PmEntreprise effectifOfficiel(String effectifOfficiel) {
        this.setEffectifOfficiel(effectifOfficiel);
        return this;
    }

    public void setEffectifOfficiel(String effectifOfficiel) {
        this.effectifOfficiel = effectifOfficiel;
    }

    public String getCodeMotifCessationActivite() {
        return this.codeMotifCessationActivite;
    }

    public PmEntreprise codeMotifCessationActivite(String codeMotifCessationActivite) {
        this.setCodeMotifCessationActivite(codeMotifCessationActivite);
        return this;
    }

    public void setCodeMotifCessationActivite(String codeMotifCessationActivite) {
        this.codeMotifCessationActivite = codeMotifCessationActivite;
    }

    public String getSiren() {
        return this.siren;
    }

    public PmEntreprise siren(String siren) {
        this.setSiren(siren);
        return this;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCodeFormeJuridique() {
        return this.codeFormeJuridique;
    }

    public PmEntreprise codeFormeJuridique(String codeFormeJuridique) {
        this.setCodeFormeJuridique(codeFormeJuridique);
        return this;
    }

    public void setCodeFormeJuridique(String codeFormeJuridique) {
        this.codeFormeJuridique = codeFormeJuridique;
    }

    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    public PmEntreprise raisonSociale(String raisonSociale) {
        this.setRaisonSociale(raisonSociale);
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getCodeCategorieJuridique() {
        return this.codeCategorieJuridique;
    }

    public PmEntreprise codeCategorieJuridique(String codeCategorieJuridique) {
        this.setCodeCategorieJuridique(codeCategorieJuridique);
        return this;
    }

    public void setCodeCategorieJuridique(String codeCategorieJuridique) {
        this.codeCategorieJuridique = codeCategorieJuridique;
    }

    public String getCodeIDCC() {
        return this.codeIDCC;
    }

    public PmEntreprise codeIDCC(String codeIDCC) {
        this.setCodeIDCC(codeIDCC);
        return this;
    }

    public void setCodeIDCC(String codeIDCC) {
        this.codeIDCC = codeIDCC;
    }

    public String getCodeAPE() {
        return this.codeAPE;
    }

    public PmEntreprise codeAPE(String codeAPE) {
        this.setCodeAPE(codeAPE);
        return this;
    }

    public void setCodeAPE(String codeAPE) {
        this.codeAPE = codeAPE;
    }

    public String getIdentifiantAI() {
        return this.identifiantAI;
    }

    public PmEntreprise identifiantAI(String identifiantAI) {
        this.setIdentifiantAI(identifiantAI);
        return this;
    }

    public void setIdentifiantAI(String identifiantAI) {
        this.identifiantAI = identifiantAI;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public PmEntreprise checked(Boolean checked) {
        this.setChecked(checked);
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Set<PmEtablissement> getPmEtablissements() {
        return this.pmEtablissements;
    }

    public void setPmEtablissements(Set<PmEtablissement> pmEtablissements) {
        if (this.pmEtablissements != null) {
            this.pmEtablissements.forEach(i -> i.setPmEntreprise(null));
        }
        if (pmEtablissements != null) {
            pmEtablissements.forEach(i -> i.setPmEntreprise(this));
        }
        this.pmEtablissements = pmEtablissements;
    }

    public PmEntreprise pmEtablissements(Set<PmEtablissement> pmEtablissements) {
        this.setPmEtablissements(pmEtablissements);
        return this;
    }

    public PmEntreprise addPmEtablissement(PmEtablissement pmEtablissement) {
        this.pmEtablissements.add(pmEtablissement);
        pmEtablissement.setPmEntreprise(this);
        return this;
    }

    public PmEntreprise removePmEtablissement(PmEtablissement pmEtablissement) {
        this.pmEtablissements.remove(pmEtablissement);
        pmEtablissement.setPmEntreprise(null);
        return this;
    }

    public Contrat getContrat() {
        return this.contrat;
    }

    public void setContrat(Contrat contrat) {
        if (this.contrat != null) {
            this.contrat.setPmEntreprise(null);
        }
        if (contrat != null) {
            contrat.setPmEntreprise(this);
        }
        this.contrat = contrat;
    }

    public PmEntreprise contrat(Contrat contrat) {
        this.setContrat(contrat);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PmEntreprise)) {
            return false;
        }
        return getId() != null && getId().equals(((PmEntreprise) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PmEntreprise{" +
            "id=" + getId() +
            ", idEntrepriseRPG='" + getIdEntrepriseRPG() + "'" +
            ", codePartenaireDistributeur='" + getCodePartenaireDistributeur() + "'" +
            ", numeroSiretSiege='" + getNumeroSiretSiege() + "'" +
            ", codeEtat='" + getCodeEtat() + "'" +
            ", libelleEtat='" + getLibelleEtat() + "'" +
            ", codeCategoriePersonne='" + getCodeCategoriePersonne() + "'" +
            ", libelleCategoriePersonne='" + getLibelleCategoriePersonne() + "'" +
            ", codeType='" + getCodeType() + "'" +
            ", dateCreationJuridique='" + getDateCreationJuridique() + "'" +
            ", dateEtat='" + getDateEtat() + "'" +
            ", dateFermetureJuridique='" + getDateFermetureJuridique() + "'" +
            ", codeTrancheEffectif='" + getCodeTrancheEffectif() + "'" +
            ", libelleTrancheEffectif='" + getLibelleTrancheEffectif() + "'" +
            ", dateCessationActivite='" + getDateCessationActivite() + "'" +
            ", dateEffectifOfficiel='" + getDateEffectifOfficiel() + "'" +
            ", effectifOfficiel='" + getEffectifOfficiel() + "'" +
            ", codeMotifCessationActivite='" + getCodeMotifCessationActivite() + "'" +
            ", siren='" + getSiren() + "'" +
            ", codeFormeJuridique='" + getCodeFormeJuridique() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", codeCategorieJuridique='" + getCodeCategorieJuridique() + "'" +
            ", codeIDCC='" + getCodeIDCC() + "'" +
            ", codeAPE='" + getCodeAPE() + "'" +
            ", identifiantAI='" + getIdentifiantAI() + "'" +
            ", checked='" + getChecked() + "'" +
            "}";
    }
}
