package org.iot.patterns.entity.enums;

import java.util.Random;

public enum SubscriptionStatus {
    ACTIVE,
    CANCELLED,
    EXPIRED;

    private static final Random RANDOM = new Random();

    public static String randomStatus() {
        SubscriptionStatus[] statuses = SubscriptionStatus.values();
        int randomIndex = RANDOM.nextInt(statuses.length);
        return statuses[randomIndex].toString();
    }
}