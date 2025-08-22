package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContratTestSamples.*;
import static com.mycompany.myapp.domain.PmEntrepriseTestSamples.*;
import static com.mycompany.myapp.domain.PmEtablissementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PmEntrepriseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PmEntreprise.class);
        PmEntreprise pmEntreprise1 = getPmEntrepriseSample1();
        PmEntreprise pmEntreprise2 = new PmEntreprise();
        assertThat(pmEntreprise1).isNotEqualTo(pmEntreprise2);

        pmEntreprise2.setId(pmEntreprise1.getId());
        assertThat(pmEntreprise1).isEqualTo(pmEntreprise2);

        pmEntreprise2 = getPmEntrepriseSample2();
        assertThat(pmEntreprise1).isNotEqualTo(pmEntreprise2);
    }

    @Test
    void pmEtablissementTest() {
        PmEntreprise pmEntreprise = getPmEntrepriseRandomSampleGenerator();
        PmEtablissement pmEtablissementBack = getPmEtablissementRandomSampleGenerator();

        pmEntreprise.addPmEtablissement(pmEtablissementBack);
        assertThat(pmEntreprise.getPmEtablissements()).containsOnly(pmEtablissementBack);
        assertThat(pmEtablissementBack.getPmEntreprise()).isEqualTo(pmEntreprise);

        pmEntreprise.removePmEtablissement(pmEtablissementBack);
        assertThat(pmEntreprise.getPmEtablissements()).doesNotContain(pmEtablissementBack);
        assertThat(pmEtablissementBack.getPmEntreprise()).isNull();

        pmEntreprise.pmEtablissements(new HashSet<>(Set.of(pmEtablissementBack)));
        assertThat(pmEntreprise.getPmEtablissements()).containsOnly(pmEtablissementBack);
        assertThat(pmEtablissementBack.getPmEntreprise()).isEqualTo(pmEntreprise);

        pmEntreprise.setPmEtablissements(new HashSet<>());
        assertThat(pmEntreprise.getPmEtablissements()).doesNotContain(pmEtablissementBack);
        assertThat(pmEtablissementBack.getPmEntreprise()).isNull();
    }

    @Test
    void contratTest() {
        PmEntreprise pmEntreprise = getPmEntrepriseRandomSampleGenerator();
        Contrat contratBack = getContratRandomSampleGenerator();

        pmEntreprise.setContrat(contratBack);
        assertThat(pmEntreprise.getContrat()).isEqualTo(contratBack);
        assertThat(contratBack.getPmEntreprise()).isEqualTo(pmEntreprise);

        pmEntreprise.contrat(null);
        assertThat(pmEntreprise.getContrat()).isNull();
        assertThat(contratBack.getPmEntreprise()).isNull();
    }
}
