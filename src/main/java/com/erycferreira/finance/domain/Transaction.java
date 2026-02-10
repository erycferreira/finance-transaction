package com.erycferreira.finance.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {

    private BigDecimal valor;
    private OffsetDateTime dataHora;

    public Transaction(BigDecimal valor, OffsetDateTime dataHora) {
        if (valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }

        this.valor = valor;
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public OffsetDateTime getDataHora() {
        return this.dataHora;
    }
}
