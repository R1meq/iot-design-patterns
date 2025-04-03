package org.iot.patterns.data;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class UserData {
    public static final List<String> HEADERS = List.of("userId", "first_name", "last_name", "email");
    private static final List<String> FIRST_NAMES = List.of("John", "Jane", "Alice", "Bob");
    private static final List<String> LAST_NAMES = List.of("Smith", "Doe", "Brown", "Johnson");
    private static final Random RANDOM = new Random();
    private static Long primaryKey = 1L;

    public static List<String> generate() {
        String id = Long.toString(primaryKey++);
        String firstName = FIRST_NAMES.get(RANDOM.nextInt(FIRST_NAMES.size()));
        String lastName = LAST_NAMES.get(RANDOM.nextInt(LAST_NAMES.size()));
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + id + "@example.com";
        return List.of(id, firstName, lastName, email);
    }
}