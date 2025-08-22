package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DemandeXRMTestSamples.*;
import static com.mycompany.myapp.domain.MiseEnGestionTestSamples.*;
import static com.mycompany.myapp.domain.PmEtablissementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MiseEnGestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MiseEnGestion.class);
        MiseEnGestion miseEnGestion1 = getMiseEnGestionSample1();
        MiseEnGestion miseEnGestion2 = new MiseEnGestion();
        assertThat(miseEnGestion1).isNotEqualTo(miseEnGestion2);

        miseEnGestion2.setId(miseEnGestion1.getId());
        assertThat(miseEnGestion1).isEqualTo(miseEnGestion2);

        miseEnGestion2 = getMiseEnGestionSample2();
        assertThat(miseEnGestion1).isNotEqualTo(miseEnGestion2);
    }

    @Test
    void demandeXRMTest() {
        MiseEnGestion miseEnGestion = getMiseEnGestionRandomSampleGenerator();
        DemandeXRM demandeXRMBack = getDemandeXRMRandomSampleGenerator();

        miseEnGestion.addDemandeXRM(demandeXRMBack);
        assertThat(miseEnGestion.getDemandeXRMS()).containsOnly(demandeXRMBack);

        miseEnGestion.removeDemandeXRM(demandeXRMBack);
        assertThat(miseEnGestion.getDemandeXRMS()).doesNotContain(demandeXRMBack);

        miseEnGestion.demandeXRMS(new HashSet<>(Set.of(demandeXRMBack)));
        assertThat(miseEnGestion.getDemandeXRMS()).containsOnly(demandeXRMBack);

        miseEnGestion.setDemandeXRMS(new HashSet<>());
        assertThat(miseEnGestion.getDemandeXRMS()).doesNotContain(demandeXRMBack);
    }

    @Test
    void pmEtablissementTest() {
        MiseEnGestion miseEnGestion = getMiseEnGestionRandomSampleGenerator();
        PmEtablissement pmEtablissementBack = getPmEtablissementRandomSampleGenerator();

        miseEnGestion.addPmEtablissement(pmEtablissementBack);
        assertThat(miseEnGestion.getPmEtablissements()).containsOnly(pmEtablissementBack);

        miseEnGestion.removePmEtablissement(pmEtablissementBack);
        assertThat(miseEnGestion.getPmEtablissements()).doesNotContain(pmEtablissementBack);

        miseEnGestion.pmEtablissements(new HashSet<>(Set.of(pmEtablissementBack)));
        assertThat(miseEnGestion.getPmEtablissements()).containsOnly(pmEtablissementBack);

        miseEnGestion.setPmEtablissements(new HashSet<>());
        assertThat(miseEnGestion.getPmEtablissements()).doesNotContain(pmEtablissementBack);
    }
}
