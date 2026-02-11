package com.erycferreira.finance.service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erycferreira.finance.domain.Transaction;
import com.erycferreira.finance.dto.TransactionRequest;
import com.erycferreira.finance.exception.DateTimeOutLimitException;

@Service
public class TransactionService {

    private final Clock clock;
    private final List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());
    private static final Logger log = LoggerFactory.getLogger(StatisticService.class);

    public TransactionService(Clock clock) {
        this.clock = clock;
    }

    public void createTransaction(TransactionRequest request) {
        OffsetDateTime current = OffsetDateTime.now(clock);

        if (request.dataHora().isAfter(current)) {
            log.warn("Transaction rejected: timestamp is in the future ({})", request.dataHora());
            throw new DateTimeOutLimitException("Não é permitido transação com horário superior ao do servidor");
        }

        log.info("Transaction accepted value={} timestamp={}", request.valor(), request.dataHora());

        transactions.add(new Transaction(request.valor(), request.dataHora()));
    }

    public void clearTransactions() {
        this.transactions.clear();
        log.info("All transactions cleared from memory");
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(this.transactions);
    }
}
