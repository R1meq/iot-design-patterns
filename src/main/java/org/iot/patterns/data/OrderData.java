package org.iot.patterns.data;

import lombok.experimental.UtilityClass;
import org.iot.patterns.data.utils.RandomDateUtils;

import java.util.List;
import java.util.Random;

@UtilityClass
public class OrderData {
    public static final List<String> HEADERS = List.of("orderId", "user_id", "mobile_plan_id", "created_at");
    private static final Random RANDOM = new Random();
    private static Long primaryKey = 1L;

    public static List<String> generate() {
        String id = String.valueOf(primaryKey++);
        String userId = String.valueOf(RANDOM.nextInt(1000) + 1);
        String mobilePlanId = String.valueOf(RANDOM.nextInt(1000) + 1);
        String createdAt = RandomDateUtils.randomTimestampLastYear();
        return List.of(id, userId, mobilePlanId, createdAt);
    }
}