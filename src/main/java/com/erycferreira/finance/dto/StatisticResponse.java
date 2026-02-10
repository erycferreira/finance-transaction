package com.erycferreira.finance.dto;

import java.math.BigDecimal;

public record StatisticResponse(
    long count,
    BigDecimal sum,
    BigDecimal avg,
    BigDecimal min,
    BigDecimal max
) {}
