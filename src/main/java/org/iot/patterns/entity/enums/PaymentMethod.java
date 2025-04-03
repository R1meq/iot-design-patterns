package org.iot.patterns.entity.enums;

import java.util.Random;

public enum PaymentMethod {
    CREDIT_CARD,
    PAYPAL,
    BANK_TRANSFER;

    private static final Random RANDOM = new Random();

    public static String randomMethod() {
        PaymentMethod[] methods = PaymentMethod.values();
        int randomIndex = RANDOM.nextInt(methods.length);
        return methods[randomIndex].toString();
    }
}