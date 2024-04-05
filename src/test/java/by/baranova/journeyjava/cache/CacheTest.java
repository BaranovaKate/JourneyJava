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
}
//
//@ExtendWith(MockitoExtension.class)
//class CacheTest {
//
//    @InjectMocks
//    private Cache cache;
//
//    @Test
//    void testPutAndGet_Success() {
//        String key = "testKey";
//        String value = "testValue";
//
//        cache.put(key, value);
//
//        assertEquals(value, cache.get(key));
//    }
//
//    @Test
//    void testGet_NotFound() {
//        String key = "nonExistentKey";
//
//        Object result = cache.get(key);
//
//        assertNull(result);
//    }
//
//    @Test
//    void testClear_Success() {
//        String key = "testKey";
//        String value = "testValue";
//        cache.put(key, value);
//
//        cache.clear();
//
//        assertNull(cache.get(key));
//    }
//}