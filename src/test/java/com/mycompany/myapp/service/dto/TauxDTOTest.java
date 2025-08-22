package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TauxDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TauxDTO.class);
        TauxDTO tauxDTO1 = new TauxDTO();
        tauxDTO1.setId(1L);
        TauxDTO tauxDTO2 = new TauxDTO();
        assertThat(tauxDTO1).isNotEqualTo(tauxDTO2);
        tauxDTO2.setId(tauxDTO1.getId());
        assertThat(tauxDTO1).isEqualTo(tauxDTO2);
        tauxDTO2.setId(2L);
        assertThat(tauxDTO1).isNotEqualTo(tauxDTO2);
        tauxDTO1.setId(null);
        assertThat(tauxDTO1).isNotEqualTo(tauxDTO2);
    }
}
