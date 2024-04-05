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

    @Test
    void putAndGet() {
        // Arrange
        Cache cache = new Cache();

        // Act
        cache.put("testKey", "testValue");
        Object retrievedValue = cache.get("testKey");

        // Assert
        assertEquals("testValue", retrievedValue);
    }

    @Test
    void clear() {
        // Arrange
        Cache cache = new Cache();
        cache.put("testKey", "testValue");

        // Act
        cache.clear();

        // Assert
        assertNull(cache.get("testKey"));
    }

    @Test
    void equalsAndHashCode() {
        // Arrange
        Cache cache1 = new Cache();
        Cache cache2 = new Cache();

        // Act & Assert
        assertEquals(cache1, cache2);
        assertEquals(cache1.hashCode(), cache2.hashCode());

        // Arrange
        cache1.put("testKey", "testValue");

        // Act & Assert
        assertNotEquals(cache1, cache2);
        assertNotEquals(cache1.hashCode(), cache2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Cache cache = new Cache();
        cache.put("testKey", "testValue");

        // Act
        String cacheString = cache.toString();

        // Assert
        assertTrue(cacheString.contains("testKey"));
        assertTrue(cacheString.contains("testValue"));
    }

    @Test
    void getHashMap() {
        // Arrange
        Cache cache = new Cache();
        Map<String, Object> expectedHashMap = new HashMap<>();
        expectedHashMap.put("testKey", "testValue");
        cache.setHashMap(expectedHashMap);

        // Act
        Map<String, Object> actualHashMap = cache.getHashMap();

        // Assert
        assertEquals(expectedHashMap, actualHashMap);
    }

    @Test
    void setHashMap() {
        // Arrange
        Cache cache = new Cache();
        Map<String, Object> expectedHashMap = new HashMap<>();
        expectedHashMap.put("testKey", "testValue");

        // Act
        cache.setHashMap(expectedHashMap);

        // Assert
        assertEquals(expectedHashMap, cache.getHashMap());
    }

    @Test
    void canEqual() {
        // Arrange
        Cache cache1 = new Cache();
        Cache cache2 = new Cache();

        // Act & Assert
        assertTrue(cache1.canEqual(cache2));
    }
}
//
//public class CacheTest {
//
//    private Cache cache;
//
//    @BeforeEach
//    void setUp() {
//        cache = new Cache();
//    }
//
//    @Test
//    void testPutAndGet() {
//        String key = "testKey";
//        String value = "testValue";
//
//        cache.put(key, value);
//        assertEquals(value, cache.get(key));
//    }
//
//    @Test
//    void testPutSameKey() {
//        String key = "testKey";
//        String value1 = "value1";
//        String value2 = "value2";
//
//        cache.put(key, value1);
//        cache.put(key, value2);
//        assertEquals(value2, cache.get(key));
//    }
//
//    @Test
//    void testGetNonExistingKey() {
//        assertNull(cache.get("nonExistingKey"));
//    }
//
//    @Test
//    void testClear() {
//        String key = "testKey";
//        String value = "testValue";
//
//        cache.put(key, value);
//        cache.clear();
//        assertNull(cache.get(key));
//    }
//
//    @Test
//    void testClearEmptyCache() {
//        cache.clear();
//        assertEquals(0, cache.getHashMap().size());
//    }
//
//    @Test
//    void testDataAnnotation() {
//        Cache cache1 = new Cache();
//        Cache cache2 = new Cache();
//
//        // Устанавливаем значение для ключа "testKey"
//        cache1.put("testKey", "testValue");
//
//        // Проверяем, что методы getter и setter работают корректно
//        assertEquals("testValue", cache1.get("testKey"));
//        cache1.put("testKey", "newValue");
//        assertEquals("newValue", cache1.get("testKey"));
//
//        // Проверяем, что метод toString() возвращает не нулевое значение
//        assertNotNull(cache1.toString());
//
//        // Проверяем, что объекты cache1 и cache2 не являются одним и тем же объектом
//        assertNotSame(cache1, cache2);
//    }
//}
