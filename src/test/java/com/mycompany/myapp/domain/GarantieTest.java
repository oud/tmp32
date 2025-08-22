package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.GarantieTestSamples.*;
import static com.mycompany.myapp.domain.ProduitTestSamples.*;
import static com.mycompany.myapp.domain.TauxTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class GarantieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Garantie.class);
        Garantie garantie1 = getGarantieSample1();
        Garantie garantie2 = new Garantie();
        assertThat(garantie1).isNotEqualTo(garantie2);

        garantie2.setId(garantie1.getId());
        assertThat(garantie1).isEqualTo(garantie2);

        garantie2 = getGarantieSample2();
        assertThat(garantie1).isNotEqualTo(garantie2);
    }

    @Test
    void tauxTest() {
        Garantie garantie = getGarantieRandomSampleGenerator();
        Taux tauxBack = getTauxRandomSampleGenerator();

        garantie.addTaux(tauxBack);
        assertThat(garantie.getTauxes()).containsOnly(tauxBack);
        assertThat(tauxBack.getGarantie()).isEqualTo(garantie);

        garantie.removeTaux(tauxBack);
        assertThat(garantie.getTauxes()).doesNotContain(tauxBack);
        assertThat(tauxBack.getGarantie()).isNull();

        garantie.tauxes(new HashSet<>(Set.of(tauxBack)));
        assertThat(garantie.getTauxes()).containsOnly(tauxBack);
        assertThat(tauxBack.getGarantie()).isEqualTo(garantie);

        garantie.setTauxes(new HashSet<>());
        assertThat(garantie.getTauxes()).doesNotContain(tauxBack);
        assertThat(tauxBack.getGarantie()).isNull();
    }

    @Test
    void produitTest() {
        Garantie garantie = getGarantieRandomSampleGenerator();
        Produit produitBack = getProduitRandomSampleGenerator();

        garantie.setProduit(produitBack);
        assertThat(garantie.getProduit()).isEqualTo(produitBack);

        garantie.produit(null);
        assertThat(garantie.getProduit()).isNull();
    }
}
