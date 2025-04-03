package org.iot.patterns.data.utils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@UtilityClass
public class RandomDateUtils {
    private static final Random RANDOM = new Random();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String randomTimestampLastYear() {
        Instant now = Instant.now();
        Instant oneYearAgo = now.minus(365, ChronoUnit.DAYS);
        long startMillis = oneYearAgo.toEpochMilli();
        long endMillis = now.toEpochMilli();
        long randomMillis = startMillis + (long) (RANDOM.nextDouble() * (endMillis - startMillis));
        return Instant.ofEpochMilli(randomMillis)
                .atZone(ZoneId.systemDefault())
                .format(FORMATTER);
    }

    public static String[] randomStartAndEndWithin30Days() {
        LocalDate startDate = randomLocalDateWithinLastYear();
        int offset = RANDOM.nextInt(31);
        LocalDate endDate = startDate.plusDays(offset);
        return new String[] { startDate.toString(), endDate.toString() };
    }

    private static LocalDate randomLocalDateWithinLastYear() {
        LocalDate now = LocalDate.now();
        LocalDate aYearAgo = now.minusDays(365);
        long startEpoch = aYearAgo.toEpochDay();
        long endEpoch = now.toEpochDay();
        long range = endEpoch - startEpoch + 1;

        // Generate a random day within the range
        long randomDayOffset = RANDOM.nextInt((int)range); // This is safe since range is ~365
        long randomDay = startEpoch + randomDayOffset;

        return LocalDate.ofEpochDay(randomDay);
    }
}
