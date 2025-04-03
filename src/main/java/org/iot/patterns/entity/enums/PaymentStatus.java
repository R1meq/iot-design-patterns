package org.iot.patterns.entity.enums;

import java.util.Random;

public enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED,
    REFUNDED;

    private static final Random RANDOM = new Random();

    public static String randomStatus() {
        PaymentStatus[] statuses = PaymentStatus.values();
        int randomIndex = RANDOM.nextInt(statuses.length);
        return statuses[randomIndex].toString();
    }
}