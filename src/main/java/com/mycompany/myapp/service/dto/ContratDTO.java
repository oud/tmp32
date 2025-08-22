package com.mycompany.myapp.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Contrat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContratDTO implements Serializable {

    private Long id;

    @NotNull
    private String numeroContratCollectif;

    @NotNull
    private String migre;

    @NotNull
    private String codeEntiteRattachement;

    @NotNull
    private String codeCentreGestion;

    @NotNull
    private String codeGroupeGestion;

    @NotNull
    private String codeReseauDistribution;

    @NotNull
    private String typeContratCollectif;

    @NotNull
    private String etatContrat;

    @NotNull
    private LocalDate dateEffetPremiereSouscription;

    private LocalDate dateEffetDerniereResiliation;

    private String motifResiliation;

    @NotNull
    private String codeNatureCouverture;

    @NotNull
    private String codeOffre;

    @NotNull
    private String numeroVersionOffre;

    @NotNull
    private String echeancePrincipale;

    @NotNull
    private String codeOrganismePorteurRisque;

    @NotNull
    private String indicateurPorteurRisque;

    @NotNull
    private String codeOrganismeProducteurFicheDsn;

    @NotNull
    private String codeOrganismeDelegataireCotisations;

    @NotNull
    private String codeOrganismeDelegatairePrestations;

    @NotNull
    private String datePremierMoisCotisationAutorise;

    @NotNull
    private Integer numeroOperationNiveau0;

    @NotNull
    private String statut;

    private PmEntrepriseDTO pmEntreprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroContratCollectif() {
        return numeroContratCollectif;
    }

    public void setNumeroContratCollectif(String numeroContratCollectif) {
        this.numeroContratCollectif = numeroContratCollectif;
    }

    public String getMigre() {
        return migre;
    }

    public void setMigre(String migre) {
        this.migre = migre;
    }

    public String getCodeEntiteRattachement() {
        return codeEntiteRattachement;
    }

    public void setCodeEntiteRattachement(String codeEntiteRattachement) {
        this.codeEntiteRattachement = codeEntiteRattachement;
    }

    public String getCodeCentreGestion() {
        return codeCentreGestion;
    }

    public void setCodeCentreGestion(String codeCentreGestion) {
        this.codeCentreGestion = codeCentreGestion;
    }

    public String getCodeGroupeGestion() {
        return codeGroupeGestion;
    }

    public void setCodeGroupeGestion(String codeGroupeGestion) {
        this.codeGroupeGestion = codeGroupeGestion;
    }

    public String getCodeReseauDistribution() {
        return codeReseauDistribution;
    }

    public void setCodeReseauDistribution(String codeReseauDistribution) {
        this.codeReseauDistribution = codeReseauDistribution;
    }

    public String getTypeContratCollectif() {
        return typeContratCollectif;
    }

    public void setTypeContratCollectif(String typeContratCollectif) {
        this.typeContratCollectif = typeContratCollectif;
    }

    public String getEtatContrat() {
        return etatContrat;
    }

    public void setEtatContrat(String etatContrat) {
        this.etatContrat = etatContrat;
    }

    public LocalDate getDateEffetPremiereSouscription() {
        return dateEffetPremiereSouscription;
    }

    public void setDateEffetPremiereSouscription(LocalDate dateEffetPremiereSouscription) {
        this.dateEffetPremiereSouscription = dateEffetPremiereSouscription;
    }

    public LocalDate getDateEffetDerniereResiliation() {
        return dateEffetDerniereResiliation;
    }

    public void setDateEffetDerniereResiliation(LocalDate dateEffetDerniereResiliation) {
        this.dateEffetDerniereResiliation = dateEffetDerniereResiliation;
    }

    public String getMotifResiliation() {
        return motifResiliation;
    }

    public void setMotifResiliation(String motifResiliation) {
        this.motifResiliation = motifResiliation;
    }

    public String getCodeNatureCouverture() {
        return codeNatureCouverture;
    }

    public void setCodeNatureCouverture(String codeNatureCouverture) {
        this.codeNatureCouverture = codeNatureCouverture;
    }

    public String getCodeOffre() {
        return codeOffre;
    }

    public void setCodeOffre(String codeOffre) {
        this.codeOffre = codeOffre;
    }

    public String getNumeroVersionOffre() {
        return numeroVersionOffre;
    }

    public void setNumeroVersionOffre(String numeroVersionOffre) {
        this.numeroVersionOffre = numeroVersionOffre;
    }

    public String getEcheancePrincipale() {
        return echeancePrincipale;
    }

    public void setEcheancePrincipale(String echeancePrincipale) {
        this.echeancePrincipale = echeancePrincipale;
    }

    public String getCodeOrganismePorteurRisque() {
        return codeOrganismePorteurRisque;
    }

    public void setCodeOrganismePorteurRisque(String codeOrganismePorteurRisque) {
        this.codeOrganismePorteurRisque = codeOrganismePorteurRisque;
    }

    public String getIndicateurPorteurRisque() {
        return indicateurPorteurRisque;
    }

    public void setIndicateurPorteurRisque(String indicateurPorteurRisque) {
        this.indicateurPorteurRisque = indicateurPorteurRisque;
    }

    public String getCodeOrganismeProducteurFicheDsn() {
        return codeOrganismeProducteurFicheDsn;
    }

    public void setCodeOrganismeProducteurFicheDsn(String codeOrganismeProducteurFicheDsn) {
        this.codeOrganismeProducteurFicheDsn = codeOrganismeProducteurFicheDsn;
    }

    public String getCodeOrganismeDelegataireCotisations() {
        return codeOrganismeDelegataireCotisations;
    }

    public void setCodeOrganismeDelegataireCotisations(String codeOrganismeDelegataireCotisations) {
        this.codeOrganismeDelegataireCotisations = codeOrganismeDelegataireCotisations;
    }

    public String getCodeOrganismeDelegatairePrestations() {
        return codeOrganismeDelegatairePrestations;
    }

    public void setCodeOrganismeDelegatairePrestations(String codeOrganismeDelegatairePrestations) {
        this.codeOrganismeDelegatairePrestations = codeOrganismeDelegatairePrestations;
    }

    public String getDatePremierMoisCotisationAutorise() {
        return datePremierMoisCotisationAutorise;
    }

    public void setDatePremierMoisCotisationAutorise(String datePremierMoisCotisationAutorise) {
        this.datePremierMoisCotisationAutorise = datePremierMoisCotisationAutorise;
    }

    public Integer getNumeroOperationNiveau0() {
        return numeroOperationNiveau0;
    }

    public void setNumeroOperationNiveau0(Integer numeroOperationNiveau0) {
        this.numeroOperationNiveau0 = numeroOperationNiveau0;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public PmEntrepriseDTO getPmEntreprise() {
        return pmEntreprise;
    }

    public void setPmEntreprise(PmEntrepriseDTO pmEntreprise) {
        this.pmEntreprise = pmEntreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContratDTO)) {
            return false;
        }

        ContratDTO contratDTO = (ContratDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contratDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContratDTO{" +
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
            ", pmEntreprise=" + getPmEntreprise() +
            "}";
    }
}
