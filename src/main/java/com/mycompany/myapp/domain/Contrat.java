package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Contrat.
 */
@Entity
@Table(name = "contrat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numero_contrat_collectif", nullable = false)
    private String numeroContratCollectif;

    @NotNull
    @Column(name = "migre", nullable = false)
    private String migre;

    @NotNull
    @Column(name = "code_entite_rattachement", nullable = false)
    private String codeEntiteRattachement;

    @NotNull
    @Column(name = "code_centre_gestion", nullable = false)
    private String codeCentreGestion;

    @NotNull
    @Column(name = "code_groupe_gestion", nullable = false)
    private String codeGroupeGestion;

    @NotNull
    @Column(name = "code_reseau_distribution", nullable = false)
    private String codeReseauDistribution;

    @NotNull
    @Column(name = "type_contrat_collectif", nullable = false)
    private String typeContratCollectif;

    @NotNull
    @Column(name = "etat_contrat", nullable = false)
    private String etatContrat;

    @NotNull
    @Column(name = "date_effet_premiere_souscription", nullable = false)
    private LocalDate dateEffetPremiereSouscription;

    @Column(name = "date_effet_derniere_resiliation")
    private LocalDate dateEffetDerniereResiliation;

    @Column(name = "motif_resiliation")
    private String motifResiliation;

    @NotNull
    @Column(name = "code_nature_couverture", nullable = false)
    private String codeNatureCouverture;

    @NotNull
    @Column(name = "code_offre", nullable = false)
    private String codeOffre;

    @NotNull
    @Column(name = "numero_version_offre", nullable = false)
    private String numeroVersionOffre;

    @NotNull
    @Column(name = "echeance_principale", nullable = false)
    private String echeancePrincipale;

    @NotNull
    @Column(name = "code_organisme_porteur_risque", nullable = false)
    private String codeOrganismePorteurRisque;

    @NotNull
    @Column(name = "indicateur_porteur_risque", nullable = false)
    private String indicateurPorteurRisque;

    @NotNull
    @Column(name = "code_organisme_producteur_fiche_dsn", nullable = false)
    private String codeOrganismeProducteurFicheDsn;

    @NotNull
    @Column(name = "code_organisme_delegataire_cotisations", nullable = false)
    private String codeOrganismeDelegataireCotisations;

    @NotNull
    @Column(name = "code_organisme_delegataire_prestations", nullable = false)
    private String codeOrganismeDelegatairePrestations;

    @NotNull
    @Column(name = "date_premier_mois_cotisation_autorise", nullable = false)
    private String datePremierMoisCotisationAutorise;

    @NotNull
    @Column(name = "numero_operation_niveau_0", nullable = false)
    private Integer numeroOperationNiveau0;

    @NotNull
    @Column(name = "statut", nullable = false)
    private String statut;

    @JsonIgnoreProperties(value = { "pmEtablissements", "contrat" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private PmEntreprise pmEntreprise;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
    @JsonIgnoreProperties(value = { "pmEtablissements", "produits", "contrat" }, allowSetters = true)
    private Set<Groupe> groupes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
    @JsonIgnoreProperties(value = { "contrat" }, allowSetters = true)
    private Set<Operation> operations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Contrat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroContratCollectif() {
        return this.numeroContratCollectif;
    }

    public Contrat numeroContratCollectif(String numeroContratCollectif) {
        this.setNumeroContratCollectif(numeroContratCollectif);
        return this;
    }

    public void setNumeroContratCollectif(String numeroContratCollectif) {
        this.numeroContratCollectif = numeroContratCollectif;
    }

    public String getMigre() {
        return this.migre;
    }

    public Contrat migre(String migre) {
        this.setMigre(migre);
        return this;
    }

    public void setMigre(String migre) {
        this.migre = migre;
    }

    public String getCodeEntiteRattachement() {
        return this.codeEntiteRattachement;
    }

    public Contrat codeEntiteRattachement(String codeEntiteRattachement) {
        this.setCodeEntiteRattachement(codeEntiteRattachement);
        return this;
    }

    public void setCodeEntiteRattachement(String codeEntiteRattachement) {
        this.codeEntiteRattachement = codeEntiteRattachement;
    }

    public String getCodeCentreGestion() {
        return this.codeCentreGestion;
    }

    public Contrat codeCentreGestion(String codeCentreGestion) {
        this.setCodeCentreGestion(codeCentreGestion);
        return this;
    }

    public void setCodeCentreGestion(String codeCentreGestion) {
        this.codeCentreGestion = codeCentreGestion;
    }

    public String getCodeGroupeGestion() {
        return this.codeGroupeGestion;
    }

    public Contrat codeGroupeGestion(String codeGroupeGestion) {
        this.setCodeGroupeGestion(codeGroupeGestion);
        return this;
    }

    public void setCodeGroupeGestion(String codeGroupeGestion) {
        this.codeGroupeGestion = codeGroupeGestion;
    }

    public String getCodeReseauDistribution() {
        return this.codeReseauDistribution;
    }

    public Contrat codeReseauDistribution(String codeReseauDistribution) {
        this.setCodeReseauDistribution(codeReseauDistribution);
        return this;
    }

    public void setCodeReseauDistribution(String codeReseauDistribution) {
        this.codeReseauDistribution = codeReseauDistribution;
    }

    public String getTypeContratCollectif() {
        return this.typeContratCollectif;
    }

    public Contrat typeContratCollectif(String typeContratCollectif) {
        this.setTypeContratCollectif(typeContratCollectif);
        return this;
    }

    public void setTypeContratCollectif(String typeContratCollectif) {
        this.typeContratCollectif = typeContratCollectif;
    }

    public String getEtatContrat() {
        return this.etatContrat;
    }

    public Contrat etatContrat(String etatContrat) {
        this.setEtatContrat(etatContrat);
        return this;
    }

    public void setEtatContrat(String etatContrat) {
        this.etatContrat = etatContrat;
    }

    public LocalDate getDateEffetPremiereSouscription() {
        return this.dateEffetPremiereSouscription;
    }

    public Contrat dateEffetPremiereSouscription(LocalDate dateEffetPremiereSouscription) {
        this.setDateEffetPremiereSouscription(dateEffetPremiereSouscription);
        return this;
    }

    public void setDateEffetPremiereSouscription(LocalDate dateEffetPremiereSouscription) {
        this.dateEffetPremiereSouscription = dateEffetPremiereSouscription;
    }

    public LocalDate getDateEffetDerniereResiliation() {
        return this.dateEffetDerniereResiliation;
    }

    public Contrat dateEffetDerniereResiliation(LocalDate dateEffetDerniereResiliation) {
        this.setDateEffetDerniereResiliation(dateEffetDerniereResiliation);
        return this;
    }

    public void setDateEffetDerniereResiliation(LocalDate dateEffetDerniereResiliation) {
        this.dateEffetDerniereResiliation = dateEffetDerniereResiliation;
    }

    public String getMotifResiliation() {
        return this.motifResiliation;
    }

    public Contrat motifResiliation(String motifResiliation) {
        this.setMotifResiliation(motifResiliation);
        return this;
    }

    public void setMotifResiliation(String motifResiliation) {
        this.motifResiliation = motifResiliation;
    }

    public String getCodeNatureCouverture() {
        return this.codeNatureCouverture;
    }

    public Contrat codeNatureCouverture(String codeNatureCouverture) {
        this.setCodeNatureCouverture(codeNatureCouverture);
        return this;
    }

    public void setCodeNatureCouverture(String codeNatureCouverture) {
        this.codeNatureCouverture = codeNatureCouverture;
    }

    public String getCodeOffre() {
        return this.codeOffre;
    }

    public Contrat codeOffre(String codeOffre) {
        this.setCodeOffre(codeOffre);
        return this;
    }

    public void setCodeOffre(String codeOffre) {
        this.codeOffre = codeOffre;
    }

    public String getNumeroVersionOffre() {
        return this.numeroVersionOffre;
    }

    public Contrat numeroVersionOffre(String numeroVersionOffre) {
        this.setNumeroVersionOffre(numeroVersionOffre);
        return this;
    }

    public void setNumeroVersionOffre(String numeroVersionOffre) {
        this.numeroVersionOffre = numeroVersionOffre;
    }

    public String getEcheancePrincipale() {
        return this.echeancePrincipale;
    }

    public Contrat echeancePrincipale(String echeancePrincipale) {
        this.setEcheancePrincipale(echeancePrincipale);
        return this;
    }

    public void setEcheancePrincipale(String echeancePrincipale) {
        this.echeancePrincipale = echeancePrincipale;
    }

    public String getCodeOrganismePorteurRisque() {
        return this.codeOrganismePorteurRisque;
    }

    public Contrat codeOrganismePorteurRisque(String codeOrganismePorteurRisque) {
        this.setCodeOrganismePorteurRisque(codeOrganismePorteurRisque);
        return this;
    }

    public void setCodeOrganismePorteurRisque(String codeOrganismePorteurRisque) {
        this.codeOrganismePorteurRisque = codeOrganismePorteurRisque;
    }

    public String getIndicateurPorteurRisque() {
        return this.indicateurPorteurRisque;
    }

    public Contrat indicateurPorteurRisque(String indicateurPorteurRisque) {
        this.setIndicateurPorteurRisque(indicateurPorteurRisque);
        return this;
    }

    public void setIndicateurPorteurRisque(String indicateurPorteurRisque) {
        this.indicateurPorteurRisque = indicateurPorteurRisque;
    }

    public String getCodeOrganismeProducteurFicheDsn() {
        return this.codeOrganismeProducteurFicheDsn;
    }

    public Contrat codeOrganismeProducteurFicheDsn(String codeOrganismeProducteurFicheDsn) {
        this.setCodeOrganismeProducteurFicheDsn(codeOrganismeProducteurFicheDsn);
        return this;
    }

    public void setCodeOrganismeProducteurFicheDsn(String codeOrganismeProducteurFicheDsn) {
        this.codeOrganismeProducteurFicheDsn = codeOrganismeProducteurFicheDsn;
    }

    public String getCodeOrganismeDelegataireCotisations() {
        return this.codeOrganismeDelegataireCotisations;
    }

    public Contrat codeOrganismeDelegataireCotisations(String codeOrganismeDelegataireCotisations) {
        this.setCodeOrganismeDelegataireCotisations(codeOrganismeDelegataireCotisations);
        return this;
    }

    public void setCodeOrganismeDelegataireCotisations(String codeOrganismeDelegataireCotisations) {
        this.codeOrganismeDelegataireCotisations = codeOrganismeDelegataireCotisations;
    }

    public String getCodeOrganismeDelegatairePrestations() {
        return this.codeOrganismeDelegatairePrestations;
    }

    public Contrat codeOrganismeDelegatairePrestations(String codeOrganismeDelegatairePrestations) {
        this.setCodeOrganismeDelegatairePrestations(codeOrganismeDelegatairePrestations);
        return this;
    }

    public void setCodeOrganismeDelegatairePrestations(String codeOrganismeDelegatairePrestations) {
        this.codeOrganismeDelegatairePrestations = codeOrganismeDelegatairePrestations;
    }

    public String getDatePremierMoisCotisationAutorise() {
        return this.datePremierMoisCotisationAutorise;
    }

    public Contrat datePremierMoisCotisationAutorise(String datePremierMoisCotisationAutorise) {
        this.setDatePremierMoisCotisationAutorise(datePremierMoisCotisationAutorise);
        return this;
    }

    public void setDatePremierMoisCotisationAutorise(String datePremierMoisCotisationAutorise) {
        this.datePremierMoisCotisationAutorise = datePremierMoisCotisationAutorise;
    }

    public Integer getNumeroOperationNiveau0() {
        return this.numeroOperationNiveau0;
    }

    public Contrat numeroOperationNiveau0(Integer numeroOperationNiveau0) {
        this.setNumeroOperationNiveau0(numeroOperationNiveau0);
        return this;
    }

    public void setNumeroOperationNiveau0(Integer numeroOperationNiveau0) {
        this.numeroOperationNiveau0 = numeroOperationNiveau0;
    }

    public String getStatut() {
        return this.statut;
    }

    public Contrat statut(String statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public PmEntreprise getPmEntreprise() {
        return this.pmEntreprise;
    }

    public void setPmEntreprise(PmEntreprise pmEntreprise) {
        this.pmEntreprise = pmEntreprise;
    }

    public Contrat pmEntreprise(PmEntreprise pmEntreprise) {
        this.setPmEntreprise(pmEntreprise);
        return this;
    }

    public Set<Groupe> getGroupes() {
        return this.groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        if (this.groupes != null) {
            this.groupes.forEach(i -> i.setContrat(null));
        }
        if (groupes != null) {
            groupes.forEach(i -> i.setContrat(this));
        }
        this.groupes = groupes;
    }

    public Contrat groupes(Set<Groupe> groupes) {
        this.setGroupes(groupes);
        return this;
    }

    public Contrat addGroupe(Groupe groupe) {
        this.groupes.add(groupe);
        groupe.setContrat(this);
        return this;
    }

    public Contrat removeGroupe(Groupe groupe) {
        this.groupes.remove(groupe);
        groupe.setContrat(null);
        return this;
    }

    public Set<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(Set<Operation> operations) {
        if (this.operations != null) {
            this.operations.forEach(i -> i.setContrat(null));
        }
        if (operations != null) {
            operations.forEach(i -> i.setContrat(this));
        }
        this.operations = operations;
    }

    public Contrat operations(Set<Operation> operations) {
        this.setOperations(operations);
        return this;
    }

    public Contrat addOperation(Operation operation) {
        this.operations.add(operation);
        operation.setContrat(this);
        return this;
    }

    public Contrat removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.setContrat(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contrat)) {
            return false;
        }
        return getId() != null && getId().equals(((Contrat) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", numeroContratCollectif='" + getNumeroContratCollectif() + "'" +
            ", migre='" + getMigre() + "'" +
            ", codeEntiteRattachement='" + getCodeEntiteRattachement() + "'" +
            ", codeCentreGestion='" + getCodeCentreGestion() + "'" +
            ", codeGroupeGestion='" + getCodeGroupeGestion() + "'" +
            ", codeReseauDistribution='" + getCodeReseauDistribution() + "'" +
            ", typeContratCollectif='" + getTypeContratCollectif() + "'" +
            ", etatContrat='" + getEtatContrat() + "'" +
            ", dateEffetPremiereSouscription='" + getDateEffetPremiereSouscription() + "'" +
            ", dateEffetDerniereResiliation='" + getDateEffetDerniereResiliation() + "'" +
            ", motifResiliation='" + getMotifResiliation() + "'" +
            ", codeNatureCouverture='" + getCodeNatureCouverture() + "'" +
            ", codeOffre='" + getCodeOffre() + "'" +
            ", numeroVersionOffre='" + getNumeroVersionOffre() + "'" +
            ", echeancePrincipale='" + getEcheancePrincipale() + "'" +
            ", codeOrganismePorteurRisque='" + getCodeOrganismePorteurRisque() + "'" +
            ", indicateurPorteurRisque='" + getIndicateurPorteurRisque() + "'" +
            ", codeOrganismeProducteurFicheDsn='" + getCodeOrganismeProducteurFicheDsn() + "'" +
            ", codeOrganismeDelegataireCotisations='" + getCodeOrganismeDelegataireCotisations() + "'" +
            ", codeOrganismeDelegatairePrestations='" + getCodeOrganismeDelegatairePrestations() + "'" +
            ", datePremierMoisCotisationAutorise='" + getDatePremierMoisCotisationAutorise() + "'" +
            ", numeroOperationNiveau0=" + getNumeroOperationNiveau0() +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
