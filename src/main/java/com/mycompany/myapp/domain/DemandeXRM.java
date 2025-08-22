package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.Status;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A DemandeXRM.
 */
@Entity
@Table(name = "demande_xrm")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DemandeXRM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_demande")
    private LocalTime dateDemande;

    @Column(name = "date_envoi_ai")
    private LocalTime dateEnvoiAI;

    @Column(name = "date_envoi_ivs")
    private LocalTime dateEnvoiIVS;

    @Enumerated(EnumType.STRING)
    @Column(name = "a_i")
    private Status aI;

    @Enumerated(EnumType.STRING)
    @Column(name = "i_vs")
    private Status iVS;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "demandeXRMS")
    @JsonIgnoreProperties(value = { "demandeXRMS", "pmEtablissements" }, allowSetters = true)
    private Set<MiseEnGestion> miseEnGestions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DemandeXRM id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getDateDemande() {
        return this.dateDemande;
    }

    public DemandeXRM dateDemande(LocalTime dateDemande) {
        this.setDateDemande(dateDemande);
        return this;
    }

    public void setDateDemande(LocalTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalTime getDateEnvoiAI() {
        return this.dateEnvoiAI;
    }

    public DemandeXRM dateEnvoiAI(LocalTime dateEnvoiAI) {
        this.setDateEnvoiAI(dateEnvoiAI);
        return this;
    }

    public void setDateEnvoiAI(LocalTime dateEnvoiAI) {
        this.dateEnvoiAI = dateEnvoiAI;
    }

    public LocalTime getDateEnvoiIVS() {
        return this.dateEnvoiIVS;
    }

    public DemandeXRM dateEnvoiIVS(LocalTime dateEnvoiIVS) {
        this.setDateEnvoiIVS(dateEnvoiIVS);
        return this;
    }

    public void setDateEnvoiIVS(LocalTime dateEnvoiIVS) {
        this.dateEnvoiIVS = dateEnvoiIVS;
    }

    public Status getaI() {
        return this.aI;
    }

    public DemandeXRM aI(Status aI) {
        this.setaI(aI);
        return this;
    }

    public void setaI(Status aI) {
        this.aI = aI;
    }

    public Status getiVS() {
        return this.iVS;
    }

    public DemandeXRM iVS(Status iVS) {
        this.setiVS(iVS);
        return this;
    }

    public void setiVS(Status iVS) {
        this.iVS = iVS;
    }

    public Set<MiseEnGestion> getMiseEnGestions() {
        return this.miseEnGestions;
    }

    public void setMiseEnGestions(Set<MiseEnGestion> miseEnGestions) {
        if (this.miseEnGestions != null) {
            this.miseEnGestions.forEach(i -> i.removeDemandeXRM(this));
        }
        if (miseEnGestions != null) {
            miseEnGestions.forEach(i -> i.addDemandeXRM(this));
        }
        this.miseEnGestions = miseEnGestions;
    }

    public DemandeXRM miseEnGestions(Set<MiseEnGestion> miseEnGestions) {
        this.setMiseEnGestions(miseEnGestions);
        return this;
    }

    public DemandeXRM addMiseEnGestion(MiseEnGestion miseEnGestion) {
        this.miseEnGestions.add(miseEnGestion);
        miseEnGestion.getDemandeXRMS().add(this);
        return this;
    }

    public DemandeXRM removeMiseEnGestion(MiseEnGestion miseEnGestion) {
        this.miseEnGestions.remove(miseEnGestion);
        miseEnGestion.getDemandeXRMS().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeXRM)) {
            return false;
        }
        return getId() != null && getId().equals(((DemandeXRM) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeXRM{" +
            "id=" + getId() +
            ", dateDemande='" + getDateDemande() + "'" +
            ", dateEnvoiAI='" + getDateEnvoiAI() + "'" +
            ", dateEnvoiIVS='" + getDateEnvoiIVS() + "'" +
            ", aI='" + getaI() + "'" +
            ", iVS='" + getiVS() + "'" +
            "}";
    }
}
