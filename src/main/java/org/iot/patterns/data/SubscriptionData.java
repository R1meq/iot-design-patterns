package org.iot.patterns.data;

import lombok.experimental.UtilityClass;
import org.iot.patterns.data.utils.RandomDateUtils;
import org.iot.patterns.entity.enums.SubscriptionStatus;

import java.util.List;
import java.util.Random;

@UtilityClass
public class SubscriptionData {
    public static final List<String> HEADERS = List.of("subscriptionId", "user_id", "plan_id", "start_date", "end_date", "status");
    private static final Random RANDOM = new Random();
    private static long primaryKey = 1L;

    public static List<String> generate() {
        String id = String.valueOf(primaryKey++);
        String userId = String.valueOf(RANDOM.nextInt(1000) + 1);
        String planId = String.valueOf(RANDOM.nextInt(1000) + 1);
        String status = SubscriptionStatus.randomStatus();

        String[] dates = RandomDateUtils.randomStartAndEndWithin30Days();
        String startDate = dates[0];
        String endDate = dates[1];

        return List.of(id, userId, planId, startDate, endDate, status);
    }
}