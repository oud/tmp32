package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.GarantieTestSamples.*;
import static com.mycompany.myapp.domain.TauxTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TauxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taux.class);
        Taux taux1 = getTauxSample1();
        Taux taux2 = new Taux();
        assertThat(taux1).isNotEqualTo(taux2);

        taux2.setId(taux1.getId());
        assertThat(taux1).isEqualTo(taux2);

        taux2 = getTauxSample2();
        assertThat(taux1).isNotEqualTo(taux2);
    }

    @Test
    void garantieTest() {
        Taux taux = getTauxRandomSampleGenerator();
        Garantie garantieBack = getGarantieRandomSampleGenerator();

        taux.setGarantie(garantieBack);
        assertThat(taux.getGarantie()).isEqualTo(garantieBack);

        taux.garantie(null);
        assertThat(taux.getGarantie()).isNull();
    }
}
