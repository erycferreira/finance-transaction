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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Create a transaction",
            description = "Receives a transaction and stores it in memory"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transaction created"),
        @ApiResponse(responseCode = "422", description = "Invalid transaction"),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<Void> transaction(
            @Valid @RequestBody TransactionRequest request
    ) {
        service.createTransaction(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping()
    @Operation(
            summary = "Clean all transactions",
            description = "clear all stores it in memory"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "All Transactions cleaned"),
    })
    public ResponseEntity<Void> clearTransactions() {
        service.clearTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
