package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContratTestSamples.*;
import static com.mycompany.myapp.domain.GroupeTestSamples.*;
import static com.mycompany.myapp.domain.PmEtablissementTestSamples.*;
import static com.mycompany.myapp.domain.ProduitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class GroupeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Groupe.class);
        Groupe groupe1 = getGroupeSample1();
        Groupe groupe2 = new Groupe();
        assertThat(groupe1).isNotEqualTo(groupe2);

        groupe2.setId(groupe1.getId());
        assertThat(groupe1).isEqualTo(groupe2);

        groupe2 = getGroupeSample2();
        assertThat(groupe1).isNotEqualTo(groupe2);
    }

    @Test
    void pmEtablissementTest() {
        Groupe groupe = getGroupeRandomSampleGenerator();
        PmEtablissement pmEtablissementBack = getPmEtablissementRandomSampleGenerator();

        groupe.addPmEtablissement(pmEtablissementBack);
        assertThat(groupe.getPmEtablissements()).containsOnly(pmEtablissementBack);
        assertThat(pmEtablissementBack.getGroupe()).isEqualTo(groupe);

        groupe.removePmEtablissement(pmEtablissementBack);
        assertThat(groupe.getPmEtablissements()).doesNotContain(pmEtablissementBack);
        assertThat(pmEtablissementBack.getGroupe()).isNull();

        groupe.pmEtablissements(new HashSet<>(Set.of(pmEtablissementBack)));
        assertThat(groupe.getPmEtablissements()).containsOnly(pmEtablissementBack);
        assertThat(pmEtablissementBack.getGroupe()).isEqualTo(groupe);

        groupe.setPmEtablissements(new HashSet<>());
        assertThat(groupe.getPmEtablissements()).doesNotContain(pmEtablissementBack);
        assertThat(pmEtablissementBack.getGroupe()).isNull();
    }

    @Test
    void produitTest() {
        Groupe groupe = getGroupeRandomSampleGenerator();
        Produit produitBack = getProduitRandomSampleGenerator();

        groupe.addProduit(produitBack);
        assertThat(groupe.getProduits()).containsOnly(produitBack);
        assertThat(produitBack.getGroupe()).isEqualTo(groupe);

        groupe.removeProduit(produitBack);
        assertThat(groupe.getProduits()).doesNotContain(produitBack);
        assertThat(produitBack.getGroupe()).isNull();

        groupe.produits(new HashSet<>(Set.of(produitBack)));
        assertThat(groupe.getProduits()).containsOnly(produitBack);
        assertThat(produitBack.getGroupe()).isEqualTo(groupe);

        groupe.setProduits(new HashSet<>());
        assertThat(groupe.getProduits()).doesNotContain(produitBack);
        assertThat(produitBack.getGroupe()).isNull();
    }

    @Test
    void contratTest() {
        Groupe groupe = getGroupeRandomSampleGenerator();
        Contrat contratBack = getContratRandomSampleGenerator();

        groupe.setContrat(contratBack);
        assertThat(groupe.getContrat()).isEqualTo(contratBack);

        groupe.contrat(null);
        assertThat(groupe.getContrat()).isNull();
    }
}
