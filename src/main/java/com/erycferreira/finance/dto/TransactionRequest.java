package com.erycferreira.finance.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Transaction request payload")
public record TransactionRequest(
    @Schema(example = "100.00", description = "Transaction value")
    @NotNull(message = "É obrigatório informar valor")
    @PositiveOrZero(message = "É obrigatório informar valor maior ou igual a 0")
    BigDecimal valor,

    @Schema(example = "2026-01-01T10:00:00Z", description = "Transaction timestamp")
    @NotNull(message = "É obrigatório informar data e hora da transação")
    OffsetDateTime dataHora
) {}
