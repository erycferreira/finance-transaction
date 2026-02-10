package com.erycferreira.finance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.erycferreira.finance.dto.StatisticResponse;
import com.erycferreira.finance.service.StatisticService;

@RestController
@RequestMapping("/estatistica")
@Validated
public class StatisticController {

    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<StatisticResponse> getStatistic() {
        return ResponseEntity.ok(
                service.buildStatistic()
        );
    }

}
