package com.erycferreira.finance.service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.erycferreira.finance.domain.Transaction;
import com.erycferreira.finance.dto.TransactionRequest;
import com.erycferreira.finance.exception.DateTimeOutLimitException;

@Service
public class TransactionService {

    private final Clock clock;
    private final List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());

    public TransactionService(Clock clock) {
        this.clock = clock;
    }

    public void createTransaction(TransactionRequest request) {
        OffsetDateTime current = OffsetDateTime.now(clock);

        if (request.dataHora().isAfter(current)) {
            throw new DateTimeOutLimitException("Não é permitido transação com horário superior ao do servidor");
        }

        transactions.add(new Transaction(request.valor(), request.dataHora()));
    }

    public void clearTransactions() {
        this.transactions.clear();
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(this.transactions);
    }
}
