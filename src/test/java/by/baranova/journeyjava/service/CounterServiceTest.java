package by.baranova.journeyjava.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CounterServiceTest {

    @Test
    void incrementRequestCount() {
        // Arrange
        CounterService.incrementRequestCount();
        CounterService.incrementRequestCount();

        // Act
        int count = CounterService.getRequestCount();

        // Assert
        assertEquals(2, count);
    }

    @Test
    void getRequestCount() {
        // Arrange
        CounterService.incrementRequestCount();
        CounterService.incrementRequestCount();

        // Act
        int count = CounterService.getRequestCount();

        // Assert
        assertEquals(2, count);
    }

    @Test
    void testEquals() {
        // Arrange
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        // Act & Assert
        assertEquals(service1, service2);
    }

    @Test
    void canEqual() {
        // Arrange
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        // Act & Assert
        assertTrue(service1.canEqual(service2));
    }

    @Test
    void testHashCode() {
        // Arrange
        CounterService service1 = new CounterService();
        CounterService service2 = new CounterService();

        // Act & Assert
        assertEquals(service1.hashCode(), service2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        CounterService service = new CounterService();

        // Act
        String stringRepresentation = service.toString();

        // Assert
        assertTrue(stringRepresentation.contains("CounterService"));
    }
}