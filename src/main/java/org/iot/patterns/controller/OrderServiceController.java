package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.MobilePlan;
import org.iot.patterns.entity.Order;
import org.iot.patterns.service.OrderService;
import org.iot.patterns.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderServiceController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
