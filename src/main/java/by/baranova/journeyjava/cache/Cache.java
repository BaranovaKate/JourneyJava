package by.baranova.journeyjava.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
//@Data
public class Cache<K, V> {

    private final Map<K, V> hashMap = new ConcurrentHashMap<>();


//    private Map<String, Object> hashMap = new ConcurrentHashMap<>();

    private static final int MAX_SIZE = 100;

    public void put(final K key, final V value) {
        if (hashMap.containsKey(key)) {
            hashMap.put(key, value);
        } else {
            hashMap.put(key, value);
            if (hashMap.size() >= MAX_SIZE) {
                hashMap.clear();
            }
        }
    }

    public Object get(final K key) {
        return hashMap.get(key);
    }


    public void clear() {
        hashMap.clear();
    }
}
