package utilities;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	
	private static final Map<String, Object> data = new HashMap<>();

    public static void set(String key, Object value) {
        data.put(key, value);
        System.out.println("ScenarioContext Data: " + data);
    }

    public static <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    public static void clear() {
        data.clear();
    }

}
