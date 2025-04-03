package org.iot.patterns.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.iot.patterns.service.MobilePlanService;
import org.iot.patterns.service.OrderService;
import org.iot.patterns.service.PaymentService;
import org.iot.patterns.service.ServiceManager;
import org.iot.patterns.service.SubscriptionService;
import org.iot.patterns.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceManagerImpl implements ServiceManager {
    private final MobilePlanService mobilePlanService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @PostConstruct
    public void init() {
        performDatabaseWrites();
    }

    private void performDatabaseWrites() {
        userService.writeToDatabase();
        mobilePlanService.writeToDatabase();
        orderService.writeToDatabase();
        paymentService.writeToDatabase();
        subscriptionService.writeToDatabase();
    }
}
