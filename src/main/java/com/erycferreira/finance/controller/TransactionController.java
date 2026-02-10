package com.erycferreira.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.finance.dto.TransactionRequest;
import com.erycferreira.finance.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
@Validated
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Void> transaction(
            @Valid @RequestBody TransactionRequest request
    ) {
        service.createTransaction(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> clearTransactions() {
        service.clearTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
