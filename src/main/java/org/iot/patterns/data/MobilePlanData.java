package org.iot.patterns.data;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class MobilePlanData {
    public static final List<String> HEADERS = List.of("mobilePlanId", "name", "description", "price", "data_limit", "call_minutes", "sms_count");
    private static final Random RANDOM = new Random();
    private static Long primaryKey = 1L;

    public static List<String> generate() {
        String id = Long.toString(primaryKey++);
        String planName = "Plan" + id;
        String description = "Description for " + planName;
        String price = String.valueOf(50 + RANDOM.nextInt(100));  // e.g., 50–149
        String dataLimit = String.valueOf(1 + RANDOM.nextInt(10)); // e.g., 1–10
        String callMinutes = String.valueOf(100 + RANDOM.nextInt(300)); // e.g., 100–399
        String smsCount = String.valueOf(50 + RANDOM.nextInt(50)); // e.g., 50–99
        return List.of(id, planName, description, price, dataLimit, callMinutes, smsCount);
    }
}
