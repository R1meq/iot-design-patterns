package org.iot.patterns.data;

import lombok.experimental.UtilityClass;
import org.iot.patterns.data.utils.AmountUtils;
import org.iot.patterns.data.utils.RandomDateUtils;
import org.iot.patterns.entity.enums.PaymentMethod;
import org.iot.patterns.entity.enums.PaymentStatus;

import java.util.List;
import java.util.Random;

@UtilityClass
public class PaymentData {
    public static final List<String> HEADERS = List.of("paymentId", "order_id", "amount", "created_at", "method", "status");
    private static final Random RANDOM = new Random();
    private static long primaryKey = 1L;

    public static List<String> generate() {
        String id = String.valueOf(primaryKey++);
        String orderId = String.valueOf(RANDOM.nextInt(1000) + 1);
        String amount = AmountUtils.randomAmount();
        String createdAt = RandomDateUtils.randomTimestampLastYear();
        String paymentMethod = PaymentMethod.randomMethod();
        String status = PaymentStatus.randomStatus();
        return List.of(id, orderId, amount, createdAt, paymentMethod, status);
    }
}