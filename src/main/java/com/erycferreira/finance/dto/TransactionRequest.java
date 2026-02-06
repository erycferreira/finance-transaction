package com.erycferreira.finance.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransactionRequest(
    @NotNull(message = "É obrigatório informar valor")
    @PositiveOrZero(message = "É obrigatório informar valor maior ou igual a 0")
    BigDecimal valor,

    @NotNull(message = "É obrigatório informar data e hora da transação")
    OffsetDateTime dataHora
) {}
