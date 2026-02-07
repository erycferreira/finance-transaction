package com.erycferreira.finance.service;

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

    private final List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());

    public void createTransaction(TransactionRequest request) {
        OffsetDateTime current = OffsetDateTime.now();

        if (request.dataHora().isAfter(current)) {
            throw new DateTimeOutLimitException("Não é permitido transação com horário superior ao do servidor");
        }

        transactions.add(new Transaction(request.valor(), request.dataHora()));
    }

    public void deleteTransaction(){
        this.transactions.clear();
    }
}
