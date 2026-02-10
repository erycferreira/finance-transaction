package com.erycferreira.finance.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class TransactionTest {

    @Test
    void shouldCreateTransactionCorrectly() {
        Transaction tx = new Transaction(
                new BigDecimal("50.00"),
                OffsetDateTime.now()
        );

        assertThat(tx.getValor()).isEqualByComparingTo("50.00");
        assertThat(tx.getDataHora()).isNotNull();
    }
}
