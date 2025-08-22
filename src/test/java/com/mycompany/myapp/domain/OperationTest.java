package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContratTestSamples.*;
import static com.mycompany.myapp.domain.OperationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Operation.class);
        Operation operation1 = getOperationSample1();
        Operation operation2 = new Operation();
        assertThat(operation1).isNotEqualTo(operation2);

        operation2.setId(operation1.getId());
        assertThat(operation1).isEqualTo(operation2);

        operation2 = getOperationSample2();
        assertThat(operation1).isNotEqualTo(operation2);
    }

    @Test
    void contratTest() {
        Operation operation = getOperationRandomSampleGenerator();
        Contrat contratBack = getContratRandomSampleGenerator();

        operation.setContrat(contratBack);
        assertThat(operation.getContrat()).isEqualTo(contratBack);

        operation.contrat(null);
        assertThat(operation.getContrat()).isNull();
    }
}
