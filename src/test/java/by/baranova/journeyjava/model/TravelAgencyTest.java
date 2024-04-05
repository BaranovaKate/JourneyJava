package by.baranova.journeyjava.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelAgencyTest {

    @Test
    void testTravelAgencyConstructorAndGetters() {
        Long id = 1L;
        String name = "Travel Agency";
        List<Journey> journeys = new ArrayList<>();

        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(id);
        travelAgency.setName(name);
        travelAgency.setJourneys(journeys);

        assertEquals(id, travelAgency.getId());
        assertEquals(name, travelAgency.getName());
        assertEquals(journeys, travelAgency.getJourneys());
    }

    @Test
    void testToString() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(1L);
        travelAgency.setName("Agency Name");

        // Act
        String agencyString = travelAgency.toString();

        // Assert
        assertTrue(agencyString.contains("id=1"));
        assertTrue(agencyString.contains("name=Agency Name"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        agency1.setId(1L);
        agency1.setName("Agency Name");

        TravelAgency agency2 = new TravelAgency();
        agency2.setId(1L);
        agency2.setName("Agency Name");

        TravelAgency agency3 = new TravelAgency();
        agency3.setId(2L);
        agency3.setName("Agency Name");

        // Assert
        assertEquals(agency1, agency2);
        assertNotEquals(agency1, agency3);
        assertEquals(agency1.hashCode(), agency2.hashCode());
        assertNotEquals(agency1.hashCode(), agency3.hashCode());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();

        // Assert
        assertNull(travelAgency.getId());
        assertNull(travelAgency.getName());
        assertNull(travelAgency.getJourneys());
    }

    @Test
    void testSetter() {
        // Arrange
        Long id = 1L;
        String name = "Travel Agency";
        List<Journey> journeys = new ArrayList<>();
        TravelAgency travelAgency = new TravelAgency();

        // Act
        travelAgency.setId(id);
        travelAgency.setName(name);
        travelAgency.setJourneys(journeys);

        // Assert
        assertEquals(id, travelAgency.getId());
        assertEquals(name, travelAgency.getName());
        assertEquals(journeys, travelAgency.getJourneys());
    }

    @Test
    void testEqualsAndHashCodeWithNull() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        TravelAgency agency2 = new TravelAgency();

        // Assert
        assertEquals(agency1, agency2);
        assertEquals(agency1.hashCode(), agency2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithSameReference() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        TravelAgency agency2 = agency1;

        // Assert
        assertEquals(agency1, agency2);
        assertEquals(agency1.hashCode(), agency2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentClass() {
        // Arrange
        TravelAgency agency = new TravelAgency();

        // Assert
        assertNotEquals(agency, "Not a TravelAgency");
    }
}