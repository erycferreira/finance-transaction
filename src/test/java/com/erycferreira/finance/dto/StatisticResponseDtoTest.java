package com.erycferreira.finance.dto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class StatisticResponseDtoTest {

    @Test
    void shouldCreateDtoCorrectly() {
        StatisticResponse dto = new StatisticResponse(
                2L,
                BigDecimal.ONE,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                BigDecimal.TEN
        );

        assertThat(dto.count()).isEqualTo(2);
        assertThat(dto.sum()).isEqualByComparingTo("1");
        assertThat(dto.avg()).isEqualByComparingTo("10");
        assertThat(dto.min()).isEqualByComparingTo("0");
        assertThat(dto.max()).isEqualByComparingTo("10");
    }
}
