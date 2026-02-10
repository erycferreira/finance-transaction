package com.erycferreira.finance.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.erycferreira.finance.dto.TransactionRequest;

class TransactionServiceTest {

    private TransactionService service;

    @BeforeEach
    void setup() {
        Clock fixedClock = Clock.fixed(
                Instant.parse("2025-01-01T10:00:00Z"),
                ZoneOffset.UTC
        );

        service = new TransactionService(fixedClock);
    }

    @Test
    void shouldStoreTransactionInMemory() {
        TransactionRequest t = new TransactionRequest(
                new BigDecimal("100.00"),
                OffsetDateTime.parse("2025-01-01T09:59:30Z")
        );

        service.createTransaction(t);

        assertThat(service.getTransactions()).hasSize(1);
    }

    @Test
    void shouldClearTransactions() {
        service.clearTransactions();
        assertThat(service.getTransactions()).isEmpty();
    }
}
