package com.erycferreira.finance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.finance.dto.StatisticResponse;
import com.erycferreira.finance.service.StatisticService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/estatistica")
@Validated
public class StatisticController {

    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping()
    @Operation(
            summary = "Generate statistics",
            description = "generate statistics based on data of transactions in memory"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Show statistics"),
    })
    public ResponseEntity<StatisticResponse> getStatistic() {
        return ResponseEntity.ok(
                service.buildStatistic()
        );
    }

}
