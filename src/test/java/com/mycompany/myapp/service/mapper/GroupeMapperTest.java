package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.GroupeAsserts.*;
import static com.mycompany.myapp.domain.GroupeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupeMapperTest {

    private GroupeMapper groupeMapper;

    @BeforeEach
    void setUp() {
        groupeMapper = new GroupeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getGroupeSample1();
        var actual = groupeMapper.toEntity(groupeMapper.toDto(expected));
        assertGroupeAllPropertiesEquals(expected, actual);
    }
}
