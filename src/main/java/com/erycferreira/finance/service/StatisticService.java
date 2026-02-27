package com.erycferreira.finance.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erycferreira.finance.domain.Transaction;
import com.erycferreira.finance.dto.StatisticResponse;

@Service
public class StatisticService {

    private final Clock clock;
    private final TransactionService transactionService;
    private static final Logger log
            = LoggerFactory.getLogger(StatisticService.class);

    public StatisticService(TransactionService transactionService, Clock clock) {
        this.transactionService = transactionService;
        this.clock = clock;
    }

    public StatisticResponse buildStatistic() {
        long start = System.nanoTime();
        OffsetDateTime last60seconds = OffsetDateTime.now(clock).minusSeconds(60);

        List<Transaction> transactions = this.transactionService.getTransactions().stream()
                .filter(t -> t.getDataHora().isAfter(last60seconds)).toList();

        long count = transactions.size();

        log.info("Calculating statistics from {}", last60seconds);

        if (transactions.isEmpty()) {
            return new StatisticResponse(
                    count,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            );
        }

        BigDecimal sum = transactions.stream()
                .map(Transaction::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal min = transactions.stream()
                .map(Transaction::getValor)
                .min(BigDecimal::compareTo)
                .orElse(null);

        BigDecimal max = transactions.stream()
                .map(Transaction::getValor)
                .max(BigDecimal::compareTo)
                .orElse(null);

        BigDecimal avg = sum.divide(
                BigDecimal.valueOf(count),
                2,
                RoundingMode.HALF_UP
        );

        long durationNs = System.nanoTime() - start;
        long durationMs = durationNs / 1_000_000;

        log.debug("Transactions considered for statistics: {}, calculated in {}",
                count,
                durationMs
        );

        return new StatisticResponse(
                count,
                sum,
                avg,
                min,
                max
        );
    }
}
