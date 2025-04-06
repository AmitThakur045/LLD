package Utils;

import java.util.UUID;

public class Utils {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isValidPhoneNumber(String input) {
        String regex = "^[6-9]\\d{9}$";
        return input != null && input.matches(regex);
    }
}
