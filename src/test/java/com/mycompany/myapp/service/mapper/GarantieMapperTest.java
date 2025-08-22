package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.GarantieAsserts.*;
import static com.mycompany.myapp.domain.GarantieTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GarantieMapperTest {

    private GarantieMapper garantieMapper;

    @BeforeEach
    void setUp() {
        garantieMapper = new GarantieMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getGarantieSample1();
        var actual = garantieMapper.toEntity(garantieMapper.toDto(expected));
        assertGarantieAllPropertiesEquals(expected, actual);
    }
}
