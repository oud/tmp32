package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContratTestSamples.*;
import static com.mycompany.myapp.domain.GroupeTestSamples.*;
import static com.mycompany.myapp.domain.OperationTestSamples.*;
import static com.mycompany.myapp.domain.PmEntrepriseTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ContratTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contrat.class);
        Contrat contrat1 = getContratSample1();
        Contrat contrat2 = new Contrat();
        assertThat(contrat1).isNotEqualTo(contrat2);

        contrat2.setId(contrat1.getId());
        assertThat(contrat1).isEqualTo(contrat2);

        contrat2 = getContratSample2();
        assertThat(contrat1).isNotEqualTo(contrat2);
    }

    @Test
    void pmEntrepriseTest() {
        Contrat contrat = getContratRandomSampleGenerator();
        PmEntreprise pmEntrepriseBack = getPmEntrepriseRandomSampleGenerator();

        contrat.setPmEntreprise(pmEntrepriseBack);
        assertThat(contrat.getPmEntreprise()).isEqualTo(pmEntrepriseBack);

        contrat.pmEntreprise(null);
        assertThat(contrat.getPmEntreprise()).isNull();
    }

    @Test
    void groupeTest() {
        Contrat contrat = getContratRandomSampleGenerator();
        Groupe groupeBack = getGroupeRandomSampleGenerator();

        contrat.addGroupe(groupeBack);
        assertThat(contrat.getGroupes()).containsOnly(groupeBack);
        assertThat(groupeBack.getContrat()).isEqualTo(contrat);

        contrat.removeGroupe(groupeBack);
        assertThat(contrat.getGroupes()).doesNotContain(groupeBack);
        assertThat(groupeBack.getContrat()).isNull();

        contrat.groupes(new HashSet<>(Set.of(groupeBack)));
        assertThat(contrat.getGroupes()).containsOnly(groupeBack);
        assertThat(groupeBack.getContrat()).isEqualTo(contrat);

        contrat.setGroupes(new HashSet<>());
        assertThat(contrat.getGroupes()).doesNotContain(groupeBack);
        assertThat(groupeBack.getContrat()).isNull();
    }

    @Test
    void operationTest() {
        Contrat contrat = getContratRandomSampleGenerator();
        Operation operationBack = getOperationRandomSampleGenerator();

        contrat.addOperation(operationBack);
        assertThat(contrat.getOperations()).containsOnly(operationBack);
        assertThat(operationBack.getContrat()).isEqualTo(contrat);

        contrat.removeOperation(operationBack);
        assertThat(contrat.getOperations()).doesNotContain(operationBack);
        assertThat(operationBack.getContrat()).isNull();

        contrat.operations(new HashSet<>(Set.of(operationBack)));
        assertThat(contrat.getOperations()).containsOnly(operationBack);
        assertThat(operationBack.getContrat()).isEqualTo(contrat);

        contrat.setOperations(new HashSet<>());
        assertThat(contrat.getOperations()).doesNotContain(operationBack);
        assertThat(operationBack.getContrat()).isNull();
    }
}
