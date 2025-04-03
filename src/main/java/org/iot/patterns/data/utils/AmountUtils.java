package org.iot.patterns.data.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@UtilityClass
public class AmountUtils {
    private static final Random RANDOM = new Random();

    public static String randomAmount() {
        double amountDouble = 10.00 + (RANDOM.nextDouble() * (500.00 - 10.00));
        return BigDecimal.valueOf(amountDouble)
                .setScale(2, RoundingMode.HALF_UP)
                .toString();
    }
}