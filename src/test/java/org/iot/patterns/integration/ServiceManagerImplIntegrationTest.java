package org.iot.patterns.integration;

import org.iot.patterns.repository.MobilePlanRepository;
import org.iot.patterns.repository.OrderRepository;
import org.iot.patterns.repository.PaymentRepository;
import org.iot.patterns.repository.SubscriptionRepository;
import org.iot.patterns.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServiceManagerImplIntegrationTest {
    private static final int EXPECTED_ENTITY_COUNT = 1000;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MobilePlanRepository mobilePlanRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    void should_PopulateRepositoriesWithExpectedEntityCount_When_DataInitializationExecuted() {
        assertAll(
                () -> assertEquals(EXPECTED_ENTITY_COUNT, userRepository.count(), "User count mismatch"),
                () -> assertEquals(EXPECTED_ENTITY_COUNT, mobilePlanRepository.count(), "Mobile plan count mismatch"),
                () -> assertEquals(EXPECTED_ENTITY_COUNT, orderRepository.count(), "Order count mismatch"),
                () -> assertEquals(EXPECTED_ENTITY_COUNT, paymentRepository.count(), "Payment count mismatch"),
                () -> assertEquals(EXPECTED_ENTITY_COUNT, subscriptionRepository.count(), "Subscription count mismatch")
        );
    }

}
