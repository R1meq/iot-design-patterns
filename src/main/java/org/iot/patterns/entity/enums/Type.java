package org.iot.patterns.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    USER(0, 3, 4),           // 4 columns
    MOBILE_PLAN(4, 10, 7),   // 7 columns
    ORDER(11, 14, 4),        // 4 columns
    PAYMENT(15, 20, 6),      // 6 columns
    SUBSCRIPTION(21, 26, 6); // 6 columns

    private final int startIndex;
    private final int endIndex;
    private final int expectedColumnCount;
}
