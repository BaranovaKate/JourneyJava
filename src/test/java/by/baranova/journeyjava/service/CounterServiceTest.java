package by.baranova.journeyjava.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CounterServiceTest {

    @Test
    void testEquals() {
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        assertEquals(service1, service2);
    }

    @Test
    void canEqual() {
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        assertTrue(service1.canEqual(service2));
    }

    @Test
    void testHashCode() {
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        assertEquals(service1.hashCode(), service2.hashCode());
    }

    @Test
    void testToString() {
        CounterService service = new CounterService();

        String stringRepresentation = service.toString();

        assertTrue(stringRepresentation.contains("CounterService"));
    }
}