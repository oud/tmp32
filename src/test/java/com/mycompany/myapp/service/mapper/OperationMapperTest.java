package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.OperationAsserts.*;
import static com.mycompany.myapp.domain.OperationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationMapperTest {

    private OperationMapper operationMapper;

    @BeforeEach
    void setUp() {
        operationMapper = new OperationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getOperationSample1();
        var actual = operationMapper.toEntity(operationMapper.toDto(expected));
        assertOperationAllPropertiesEquals(expected, actual);
    }
}
