package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.service.MobilePlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/mobile-plans")
@RequiredArgsConstructor
public class MobilePlanController {
    private final MobilePlanService mobilePlanService;

    @GetMapping
    public ResponseEntity<List<MobilePlan>> getAllMobilePlans() {
        return ResponseEntity.ok(mobilePlanService.findAll());
    }
}
