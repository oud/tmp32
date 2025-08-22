package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DemandeXRMTestSamples.*;
import static com.mycompany.myapp.domain.MiseEnGestionTestSamples.*;
import static com.mycompany.myapp.domain.PmEtablissementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
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
    void pmEtablissementTest() {
        MiseEnGestion miseEnGestion = getMiseEnGestionRandomSampleGenerator();
        PmEtablissement pmEtablissementBack = getPmEtablissementRandomSampleGenerator();

        miseEnGestion.setPmEtablissement(pmEtablissementBack);
        assertThat(miseEnGestion.getPmEtablissement()).isEqualTo(pmEtablissementBack);

        miseEnGestion.pmEtablissement(null);
        assertThat(miseEnGestion.getPmEtablissement()).isNull();
    }

    @Test
    void demandeXRMTest() {
        MiseEnGestion miseEnGestion = getMiseEnGestionRandomSampleGenerator();
        DemandeXRM demandeXRMBack = getDemandeXRMRandomSampleGenerator();

        miseEnGestion.setDemandeXRM(demandeXRMBack);
        assertThat(miseEnGestion.getDemandeXRM()).isEqualTo(demandeXRMBack);

        miseEnGestion.demandeXRM(null);
        assertThat(miseEnGestion.getDemandeXRM()).isNull();
    }
}
