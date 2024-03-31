package by.baranova.journeyjava.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class representing a simple cache based on HashMap.
 */
@Component
@Data
public class Cache {

    private Map<String, Object> hashMap = new ConcurrentHashMap<>();

    public void put(final String key, final Object value) {
        hashMap.put(key, value);
    }

    public Object get(final String key) {
        return hashMap.get(key);
    }


    public void clear() {
        hashMap.clear();
    }
}
