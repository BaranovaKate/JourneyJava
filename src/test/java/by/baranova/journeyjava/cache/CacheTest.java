package by.baranova.journeyjava.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



class CacheTest {

    private Cache<String, Object> cache;

    @BeforeEach
    void setUp() {
        cache = new Cache<>();
    }

    @Test
    void putAndGet() {
        cache.put("key1", "value1");
        assertEquals("value1", cache.get("key1"));
    }

    @Test
    void putTwiceAndGet() {
        cache.put("key1", "value1");
        cache.put("key1", "value2");
        assertEquals("value2", cache.get("key1"));
    }
    @Test
    void clear() {
        cache.put("testKey", "testValue");
        cache.clear();
        assertNull(cache.get("testKey"));
    }


    @Test
    void clearWhenSizeExceeds() {
        for (int i = 0; i < 110; i++) {
            cache.put("key" + i, "value" + i);
        }
        assertNull(cache.get("key0"));
        assertNotNull(cache.get("key100"));
    }

}
