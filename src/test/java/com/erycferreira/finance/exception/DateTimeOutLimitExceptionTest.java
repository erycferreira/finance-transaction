package com.erycferreira.finance.exception;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class DateTimeOutLimitExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        DateTimeOutLimitException ex
                = new DateTimeOutLimitException("Data fora do limite");

        assertThat(ex.getMessage()).isEqualTo("Data fora do limite");
    }
}
