package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.GarantieTestSamples.*;
import static com.mycompany.myapp.domain.GroupeTestSamples.*;
import static com.mycompany.myapp.domain.ProduitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProduitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produit.class);
        Produit produit1 = getProduitSample1();
        Produit produit2 = new Produit();
        assertThat(produit1).isNotEqualTo(produit2);

        produit2.setId(produit1.getId());
        assertThat(produit1).isEqualTo(produit2);

        produit2 = getProduitSample2();
        assertThat(produit1).isNotEqualTo(produit2);
    }

    @Test
    void garantieTest() {
        Produit produit = getProduitRandomSampleGenerator();
        Garantie garantieBack = getGarantieRandomSampleGenerator();

        produit.addGarantie(garantieBack);
        assertThat(produit.getGaranties()).containsOnly(garantieBack);
        assertThat(garantieBack.getProduit()).isEqualTo(produit);

        produit.removeGarantie(garantieBack);
        assertThat(produit.getGaranties()).doesNotContain(garantieBack);
        assertThat(garantieBack.getProduit()).isNull();

        produit.garanties(new HashSet<>(Set.of(garantieBack)));
        assertThat(produit.getGaranties()).containsOnly(garantieBack);
        assertThat(garantieBack.getProduit()).isEqualTo(produit);

        produit.setGaranties(new HashSet<>());
        assertThat(produit.getGaranties()).doesNotContain(garantieBack);
        assertThat(garantieBack.getProduit()).isNull();
    }

    @Test
    void groupeTest() {
        Produit produit = getProduitRandomSampleGenerator();
        Groupe groupeBack = getGroupeRandomSampleGenerator();

        produit.setGroupe(groupeBack);
        assertThat(produit.getGroupe()).isEqualTo(groupeBack);

        produit.groupe(null);
        assertThat(produit.getGroupe()).isNull();
    }
}
