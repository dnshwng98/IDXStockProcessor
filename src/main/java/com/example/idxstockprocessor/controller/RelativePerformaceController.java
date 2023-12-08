package com.example.idxstockprocessor.controller;

import com.example.idxstockprocessor.service.RelativePerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelativePerformaceController {
    @Autowired
    RelativePerformanceService relativePerformanceService;

    @GetMapping("/calculateAndUpdateRelativePerformance")
    public String calculateAndUpdateRelativePerformance(@RequestBody String datetime) {
        return relativePerformanceService.calculateAndUpdateRelativePerformance(datetime);
    }
}
