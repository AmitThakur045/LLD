package Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Global {
    private static Global instance;
    private Map<String, Integer> spamContactMap;

    private Global() {
        spamContactMap = new ConcurrentHashMap<>(); // Thread-safe map
    }

    public static synchronized Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public void reportSpam(String phoneNumber) {
        spamContactMap.compute(phoneNumber, (key, val) -> (val == null) ? 1 : val + 1);
    }
}
