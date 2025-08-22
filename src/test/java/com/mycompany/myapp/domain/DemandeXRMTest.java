package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DemandeXRMTestSamples.*;
import static com.mycompany.myapp.domain.MiseEnGestionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DemandeXRMTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeXRM.class);
        DemandeXRM demandeXRM1 = getDemandeXRMSample1();
        DemandeXRM demandeXRM2 = new DemandeXRM();
        assertThat(demandeXRM1).isNotEqualTo(demandeXRM2);

        demandeXRM2.setId(demandeXRM1.getId());
        assertThat(demandeXRM1).isEqualTo(demandeXRM2);

        demandeXRM2 = getDemandeXRMSample2();
        assertThat(demandeXRM1).isNotEqualTo(demandeXRM2);
    }

    @Test
    void miseEnGestionTest() {
        DemandeXRM demandeXRM = getDemandeXRMRandomSampleGenerator();
        MiseEnGestion miseEnGestionBack = getMiseEnGestionRandomSampleGenerator();

        demandeXRM.addMiseEnGestion(miseEnGestionBack);
        assertThat(demandeXRM.getMiseEnGestions()).containsOnly(miseEnGestionBack);
        assertThat(miseEnGestionBack.getDemandeXRM()).isEqualTo(demandeXRM);

        demandeXRM.removeMiseEnGestion(miseEnGestionBack);
        assertThat(demandeXRM.getMiseEnGestions()).doesNotContain(miseEnGestionBack);
        assertThat(miseEnGestionBack.getDemandeXRM()).isNull();

        demandeXRM.miseEnGestions(new HashSet<>(Set.of(miseEnGestionBack)));
        assertThat(demandeXRM.getMiseEnGestions()).containsOnly(miseEnGestionBack);
        assertThat(miseEnGestionBack.getDemandeXRM()).isEqualTo(demandeXRM);

        demandeXRM.setMiseEnGestions(new HashSet<>());
        assertThat(demandeXRM.getMiseEnGestions()).doesNotContain(miseEnGestionBack);
        assertThat(miseEnGestionBack.getDemandeXRM()).isNull();
    }
}
