package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.Status;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DemandeXRM} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DemandeXRMDTO implements Serializable {

    private Long id;

    private LocalTime dateDemande;

    private LocalTime dateEnvoiAI;

    private LocalTime dateEnvoiIVS;

    private Status aI;

    private Status iVS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalTime dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalTime getDateEnvoiAI() {
        return dateEnvoiAI;
    }

    public void setDateEnvoiAI(LocalTime dateEnvoiAI) {
        this.dateEnvoiAI = dateEnvoiAI;
    }

    public LocalTime getDateEnvoiIVS() {
        return dateEnvoiIVS;
    }

    public void setDateEnvoiIVS(LocalTime dateEnvoiIVS) {
        this.dateEnvoiIVS = dateEnvoiIVS;
    }

    public Status getaI() {
        return aI;
    }

    public void setaI(Status aI) {
        this.aI = aI;
    }

    public Status getiVS() {
        return iVS;
    }

    public void setiVS(Status iVS) {
        this.iVS = iVS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeXRMDTO)) {
            return false;
        }

        DemandeXRMDTO demandeXRMDTO = (DemandeXRMDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, demandeXRMDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeXRMDTO{" +
            "id=" + getId() +
            ", dateDemande='" + getDateDemande() + "'" +
            ", dateEnvoiAI='" + getDateEnvoiAI() + "'" +
            ", dateEnvoiIVS='" + getDateEnvoiIVS() + "'" +
            ", aI='" + getaI() + "'" +
            ", iVS='" + getiVS() + "'" +
            "}";
    }
}
