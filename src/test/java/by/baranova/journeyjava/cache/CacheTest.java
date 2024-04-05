package by.baranova.journeyjava.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private Cache cache;

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    @Test
    void testPutAndGet() {
        String key = "testKey";
        String value = "testValue";

        cache.put(key, value);
        assertEquals(value, cache.get(key));
    }

    @Test
    void testPutSameKey() {
        String key = "testKey";
        String value1 = "value1";
        String value2 = "value2";

        cache.put(key, value1);
        cache.put(key, value2);
        assertEquals(value2, cache.get(key));
    }

    @Test
    void testGetNonExistingKey() {
        assertNull(cache.get("nonExistingKey"));
    }

    @Test
    void testClear() {
        String key = "testKey";
        String value = "testValue";

        cache.put(key, value);
        cache.clear();
        assertNull(cache.get(key));
    }

    @Test
    void testClearEmptyCache() {
        cache.clear();
        assertEquals(0, cache.getHashMap().size());
    }

    @Test
    void testDataAnnotation() {
        Cache cache1 = new Cache();
        Cache cache2 = new Cache();

        // Устанавливаем значение для ключа "testKey"
        cache1.put("testKey", "testValue");

        // Проверяем, что методы getter и setter работают корректно
        assertEquals("testValue", cache1.get("testKey"));
        cache1.put("testKey", "newValue");
        assertEquals("newValue", cache1.get("testKey"));

        // Проверяем, что метод toString() возвращает не нулевое значение
        assertNotNull(cache1.toString());

        // Проверяем, что объекты cache1 и cache2 не являются одним и тем же объектом
        assertNotSame(cache1, cache2);
    }
}
