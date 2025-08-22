package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TauxAsserts.*;
import static com.mycompany.myapp.domain.TauxTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TauxMapperTest {

    private TauxMapper tauxMapper;

    @BeforeEach
    void setUp() {
        tauxMapper = new TauxMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTauxSample1();
        var actual = tauxMapper.toEntity(tauxMapper.toDto(expected));
        assertTauxAllPropertiesEquals(expected, actual);
    }
}
