package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A PmEtablissement.
 */
@Entity
@Table(name = "pm_etablissement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PmEtablissement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_etablissement_rpg", nullable = false)
    private String idEtablissementRPG;

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

    @NotNull
    @Column(name = "code_categorie_personne", nullable = false)
    private String codeCategoriePersonne;

    @NotNull
    @Column(name = "libelle_categorie_personne", nullable = false)
    private String libelleCategoriePersonne;

    @NotNull
    @Column(name = "code_type", nullable = false)
    private String codeType;

    @NotNull
    @Column(name = "date_creation_juridique", nullable = false)
    private LocalDate dateCreationJuridique;

    @NotNull
    @Column(name = "date_etat", nullable = false)
    private LocalDate dateEtat;

    @Column(name = "date_fermeture_juridique")
    private LocalDate dateFermetureJuridique;

    @NotNull
    @Column(name = "code_ape", nullable = false)
    private String codeAPE;

    @Column(name = "code_idcc")
    private String codeIDCC;

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
    @Column(name = "siret", nullable = false)
    private String siret;

    @NotNull
    @Column(name = "code_type_etablissement", nullable = false)
    private String codeTypeEtablissement;

    @NotNull
    @Column(name = "libelle_enseigne", nullable = false)
    private String libelleEnseigne;

    @NotNull
    @Column(name = "code_nic", nullable = false)
    private String codeNIC;

    @Column(name = "identifiant_ai")
    private String identifiantAI;

    @Column(name = "checked")
    private Boolean checked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmEtablissement")
    @JsonIgnoreProperties(value = { "pmEtablissement" }, allowSetters = true)
    private Set<Adresse> adresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmEtablissement")
    @JsonIgnoreProperties(value = { "pmEtablissement" }, allowSetters = true)
    private Set<Email> emails = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmEtablissement")
    @JsonIgnoreProperties(value = { "pmEtablissement" }, allowSetters = true)
    private Set<Telephone> telephones = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pmEtablissement")
    @JsonIgnoreProperties(value = { "pmEtablissement", "demandeXRM" }, allowSetters = true)
    private Set<MiseEnGestion> miseEnGestions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "pmEtablissements", "produits", "contrat" }, allowSetters = true)
    private Groupe groupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "pmEtablissements", "contrat" }, allowSetters = true)
    private PmEntreprise pmEntreprise;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PmEtablissement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEtablissementRPG() {
        return this.idEtablissementRPG;
    }

    public PmEtablissement idEtablissementRPG(String idEtablissementRPG) {
        this.setIdEtablissementRPG(idEtablissementRPG);
        return this;
    }

    public void setIdEtablissementRPG(String idEtablissementRPG) {
        this.idEtablissementRPG = idEtablissementRPG;
    }

    public String getCodePartenaireDistributeur() {
        return this.codePartenaireDistributeur;
    }

    public PmEtablissement codePartenaireDistributeur(String codePartenaireDistributeur) {
        this.setCodePartenaireDistributeur(codePartenaireDistributeur);
        return this;
    }

    public void setCodePartenaireDistributeur(String codePartenaireDistributeur) {
        this.codePartenaireDistributeur = codePartenaireDistributeur;
    }

    public String getNumeroSiretSiege() {
        return this.numeroSiretSiege;
    }

    public PmEtablissement numeroSiretSiege(String numeroSiretSiege) {
        this.setNumeroSiretSiege(numeroSiretSiege);
        return this;
    }

    public void setNumeroSiretSiege(String numeroSiretSiege) {
        this.numeroSiretSiege = numeroSiretSiege;
    }

    public String getCodeEtat() {
        return this.codeEtat;
    }

    public PmEtablissement codeEtat(String codeEtat) {
        this.setCodeEtat(codeEtat);
        return this;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public String getLibelleEtat() {
        return this.libelleEtat;
    }

    public PmEtablissement libelleEtat(String libelleEtat) {
        this.setLibelleEtat(libelleEtat);
        return this;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    public String getCodeCategoriePersonne() {
        return this.codeCategoriePersonne;
    }

    public PmEtablissement codeCategoriePersonne(String codeCategoriePersonne) {
        this.setCodeCategoriePersonne(codeCategoriePersonne);
        return this;
    }

    public void setCodeCategoriePersonne(String codeCategoriePersonne) {
        this.codeCategoriePersonne = codeCategoriePersonne;
    }

    public String getLibelleCategoriePersonne() {
        return this.libelleCategoriePersonne;
    }

    public PmEtablissement libelleCategoriePersonne(String libelleCategoriePersonne) {
        this.setLibelleCategoriePersonne(libelleCategoriePersonne);
        return this;
    }

    public void setLibelleCategoriePersonne(String libelleCategoriePersonne) {
        this.libelleCategoriePersonne = libelleCategoriePersonne;
    }

    public String getCodeType() {
        return this.codeType;
    }

    public PmEtablissement codeType(String codeType) {
        this.setCodeType(codeType);
        return this;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public LocalDate getDateCreationJuridique() {
        return this.dateCreationJuridique;
    }

    public PmEtablissement dateCreationJuridique(LocalDate dateCreationJuridique) {
        this.setDateCreationJuridique(dateCreationJuridique);
        return this;
    }

    public void setDateCreationJuridique(LocalDate dateCreationJuridique) {
        this.dateCreationJuridique = dateCreationJuridique;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public PmEtablissement dateEtat(LocalDate dateEtat) {
        this.setDateEtat(dateEtat);
        return this;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public LocalDate getDateFermetureJuridique() {
        return this.dateFermetureJuridique;
    }

    public PmEtablissement dateFermetureJuridique(LocalDate dateFermetureJuridique) {
        this.setDateFermetureJuridique(dateFermetureJuridique);
        return this;
    }

    public void setDateFermetureJuridique(LocalDate dateFermetureJuridique) {
        this.dateFermetureJuridique = dateFermetureJuridique;
    }

    public String getCodeAPE() {
        return this.codeAPE;
    }

    public PmEtablissement codeAPE(String codeAPE) {
        this.setCodeAPE(codeAPE);
        return this;
    }

    public void setCodeAPE(String codeAPE) {
        this.codeAPE = codeAPE;
    }

    public String getCodeIDCC() {
        return this.codeIDCC;
    }

    public PmEtablissement codeIDCC(String codeIDCC) {
        this.setCodeIDCC(codeIDCC);
        return this;
    }

    public void setCodeIDCC(String codeIDCC) {
        this.codeIDCC = codeIDCC;
    }

    public String getCodeTrancheEffectif() {
        return this.codeTrancheEffectif;
    }

    public PmEtablissement codeTrancheEffectif(String codeTrancheEffectif) {
        this.setCodeTrancheEffectif(codeTrancheEffectif);
        return this;
    }

    public void setCodeTrancheEffectif(String codeTrancheEffectif) {
        this.codeTrancheEffectif = codeTrancheEffectif;
    }

    public String getLibelleTrancheEffectif() {
        return this.libelleTrancheEffectif;
    }

    public PmEtablissement libelleTrancheEffectif(String libelleTrancheEffectif) {
        this.setLibelleTrancheEffectif(libelleTrancheEffectif);
        return this;
    }

    public void setLibelleTrancheEffectif(String libelleTrancheEffectif) {
        this.libelleTrancheEffectif = libelleTrancheEffectif;
    }

    public LocalDate getDateCessationActivite() {
        return this.dateCessationActivite;
    }

    public PmEtablissement dateCessationActivite(LocalDate dateCessationActivite) {
        this.setDateCessationActivite(dateCessationActivite);
        return this;
    }

    public void setDateCessationActivite(LocalDate dateCessationActivite) {
        this.dateCessationActivite = dateCessationActivite;
    }

    public LocalDate getDateEffectifOfficiel() {
        return this.dateEffectifOfficiel;
    }

    public PmEtablissement dateEffectifOfficiel(LocalDate dateEffectifOfficiel) {
        this.setDateEffectifOfficiel(dateEffectifOfficiel);
        return this;
    }

    public void setDateEffectifOfficiel(LocalDate dateEffectifOfficiel) {
        this.dateEffectifOfficiel = dateEffectifOfficiel;
    }

    public String getEffectifOfficiel() {
        return this.effectifOfficiel;
    }

    public PmEtablissement effectifOfficiel(String effectifOfficiel) {
        this.setEffectifOfficiel(effectifOfficiel);
        return this;
    }

    public void setEffectifOfficiel(String effectifOfficiel) {
        this.effectifOfficiel = effectifOfficiel;
    }

    public String getCodeMotifCessationActivite() {
        return this.codeMotifCessationActivite;
    }

    public PmEtablissement codeMotifCessationActivite(String codeMotifCessationActivite) {
        this.setCodeMotifCessationActivite(codeMotifCessationActivite);
        return this;
    }

    public void setCodeMotifCessationActivite(String codeMotifCessationActivite) {
        this.codeMotifCessationActivite = codeMotifCessationActivite;
    }

    public String getSiret() {
        return this.siret;
    }

    public PmEtablissement siret(String siret) {
        this.setSiret(siret);
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getCodeTypeEtablissement() {
        return this.codeTypeEtablissement;
    }

    public PmEtablissement codeTypeEtablissement(String codeTypeEtablissement) {
        this.setCodeTypeEtablissement(codeTypeEtablissement);
        return this;
    }

    public void setCodeTypeEtablissement(String codeTypeEtablissement) {
        this.codeTypeEtablissement = codeTypeEtablissement;
    }

    public String getLibelleEnseigne() {
        return this.libelleEnseigne;
    }

    public PmEtablissement libelleEnseigne(String libelleEnseigne) {
        this.setLibelleEnseigne(libelleEnseigne);
        return this;
    }

    public void setLibelleEnseigne(String libelleEnseigne) {
        this.libelleEnseigne = libelleEnseigne;
    }

    public String getCodeNIC() {
        return this.codeNIC;
    }

    public PmEtablissement codeNIC(String codeNIC) {
        this.setCodeNIC(codeNIC);
        return this;
    }

    public void setCodeNIC(String codeNIC) {
        this.codeNIC = codeNIC;
    }

    public String getIdentifiantAI() {
        return this.identifiantAI;
    }

    public PmEtablissement identifiantAI(String identifiantAI) {
        this.setIdentifiantAI(identifiantAI);
        return this;
    }

    public void setIdentifiantAI(String identifiantAI) {
        this.identifiantAI = identifiantAI;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public PmEtablissement checked(Boolean checked) {
        this.setChecked(checked);
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Set<Adresse> getAdresses() {
        return this.adresses;
    }

    public void setAdresses(Set<Adresse> adresses) {
        if (this.adresses != null) {
            this.adresses.forEach(i -> i.setPmEtablissement(null));
        }
        if (adresses != null) {
            adresses.forEach(i -> i.setPmEtablissement(this));
        }
        this.adresses = adresses;
    }

    public PmEtablissement adresses(Set<Adresse> adresses) {
        this.setAdresses(adresses);
        return this;
    }

    public PmEtablissement addAdresse(Adresse adresse) {
        this.adresses.add(adresse);
        adresse.setPmEtablissement(this);
        return this;
    }

    public PmEtablissement removeAdresse(Adresse adresse) {
        this.adresses.remove(adresse);
        adresse.setPmEtablissement(null);
        return this;
    }

    public Set<Email> getEmails() {
        return this.emails;
    }

    public void setEmails(Set<Email> emails) {
        if (this.emails != null) {
            this.emails.forEach(i -> i.setPmEtablissement(null));
        }
        if (emails != null) {
            emails.forEach(i -> i.setPmEtablissement(this));
        }
        this.emails = emails;
    }

    public PmEtablissement emails(Set<Email> emails) {
        this.setEmails(emails);
        return this;
    }

    public PmEtablissement addEmail(Email email) {
        this.emails.add(email);
        email.setPmEtablissement(this);
        return this;
    }

    public PmEtablissement removeEmail(Email email) {
        this.emails.remove(email);
        email.setPmEtablissement(null);
        return this;
    }

    public Set<Telephone> getTelephones() {
        return this.telephones;
    }

    public void setTelephones(Set<Telephone> telephones) {
        if (this.telephones != null) {
            this.telephones.forEach(i -> i.setPmEtablissement(null));
        }
        if (telephones != null) {
            telephones.forEach(i -> i.setPmEtablissement(this));
        }
        this.telephones = telephones;
    }

    public PmEtablissement telephones(Set<Telephone> telephones) {
        this.setTelephones(telephones);
        return this;
    }

    public PmEtablissement addTelephone(Telephone telephone) {
        this.telephones.add(telephone);
        telephone.setPmEtablissement(this);
        return this;
    }

    public PmEtablissement removeTelephone(Telephone telephone) {
        this.telephones.remove(telephone);
        telephone.setPmEtablissement(null);
        return this;
    }

    public Set<MiseEnGestion> getMiseEnGestions() {
        return this.miseEnGestions;
    }

    public void setMiseEnGestions(Set<MiseEnGestion> miseEnGestions) {
        if (this.miseEnGestions != null) {
            this.miseEnGestions.forEach(i -> i.setPmEtablissement(null));
        }
        if (miseEnGestions != null) {
            miseEnGestions.forEach(i -> i.setPmEtablissement(this));
        }
        this.miseEnGestions = miseEnGestions;
    }

    public PmEtablissement miseEnGestions(Set<MiseEnGestion> miseEnGestions) {
        this.setMiseEnGestions(miseEnGestions);
        return this;
    }

    public PmEtablissement addMiseEnGestion(MiseEnGestion miseEnGestion) {
        this.miseEnGestions.add(miseEnGestion);
        miseEnGestion.setPmEtablissement(this);
        return this;
    }

    public PmEtablissement removeMiseEnGestion(MiseEnGestion miseEnGestion) {
        this.miseEnGestions.remove(miseEnGestion);
        miseEnGestion.setPmEtablissement(null);
        return this;
    }

    public Groupe getGroupe() {
        return this.groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public PmEtablissement groupe(Groupe groupe) {
        this.setGroupe(groupe);
        return this;
    }

    public PmEntreprise getPmEntreprise() {
        return this.pmEntreprise;
    }

    public void setPmEntreprise(PmEntreprise pmEntreprise) {
        this.pmEntreprise = pmEntreprise;
    }

    public PmEtablissement pmEntreprise(PmEntreprise pmEntreprise) {
        this.setPmEntreprise(pmEntreprise);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PmEtablissement)) {
            return false;
        }
        return getId() != null && getId().equals(((PmEtablissement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PmEtablissement{" +
            "id=" + getId() +
            ", idEtablissementRPG='" + getIdEtablissementRPG() + "'" +
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
            ", codeAPE='" + getCodeAPE() + "'" +
            ", codeIDCC='" + getCodeIDCC() + "'" +
            ", codeTrancheEffectif='" + getCodeTrancheEffectif() + "'" +
            ", libelleTrancheEffectif='" + getLibelleTrancheEffectif() + "'" +
            ", dateCessationActivite='" + getDateCessationActivite() + "'" +
            ", dateEffectifOfficiel='" + getDateEffectifOfficiel() + "'" +
            ", effectifOfficiel='" + getEffectifOfficiel() + "'" +
            ", codeMotifCessationActivite='" + getCodeMotifCessationActivite() + "'" +
            ", siret='" + getSiret() + "'" +
            ", codeTypeEtablissement='" + getCodeTypeEtablissement() + "'" +
            ", libelleEnseigne='" + getLibelleEnseigne() + "'" +
            ", codeNIC='" + getCodeNIC() + "'" +
            ", identifiantAI='" + getIdentifiantAI() + "'" +
            ", checked='" + getChecked() + "'" +
            "}";
    }
}
