package com.erycferreira.finance.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.erycferreira.finance.dto.StatisticResponse;
import com.erycferreira.finance.dto.TransactionRequest;

class StatisticServiceTest {

    private TransactionService transactionService;
    private StatisticService statisticService;
    private Clock fixedClock;

    @BeforeEach
    void setup() {
        fixedClock = Clock.fixed(
                Instant.parse("2025-01-01T10:00:00Z"),
                ZoneOffset.UTC
        );

        transactionService = new TransactionService(fixedClock);
        statisticService = new StatisticService(transactionService, fixedClock);
    }

    @Test
    void shouldBuildStatisticsOnlyWithLast60SecondsTransactions() {
        transactionService.createTransaction(new TransactionRequest(
                new BigDecimal("10.00"),
                OffsetDateTime.now(fixedClock).minusSeconds(10)
        ));

        transactionService.createTransaction(new TransactionRequest(
                new BigDecimal("20.00"),
                OffsetDateTime.now(fixedClock).minusSeconds(20)
        ));

        transactionService.createTransaction(new TransactionRequest(
                new BigDecimal("999.00"),
                OffsetDateTime.now(fixedClock).minusSeconds(120)
        ));

        StatisticResponse response = statisticService.buildStatistic();

        assertThat(response.count()).isEqualTo(2);
        assertThat(response.sum()).isEqualByComparingTo("30.00");
        assertThat(response.avg()).isEqualByComparingTo("15.00");
        assertThat(response.min()).isEqualByComparingTo("10.00");
        assertThat(response.max()).isEqualByComparingTo("20.00");
    }

    @Test
    void shouldReturnZeroStatisticsWhenNoTransactionsInLast60Seconds() {
        transactionService.createTransaction(new TransactionRequest(
                new BigDecimal("100.00"),
                OffsetDateTime.now(fixedClock).minusSeconds(120)
        ));

        StatisticResponse response = statisticService.buildStatistic();

        assertThat(response.count()).isZero();
        assertThat(response.sum()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(response.avg()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(response.min()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(response.max()).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
