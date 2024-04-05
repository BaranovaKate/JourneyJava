package by.baranova.journeyjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JourneyTest {

    @Test
    void testJourneyConstructorAndGetters() {
        Long id = 1L;
        String country = "Country";
        String town = "Town";
        LocalDate dateToJourney = LocalDate.of(2024, 3, 15);
        LocalDate dateFromJourney = LocalDate.of(2024, 3, 20);
        TravelAgency travelAgency = new TravelAgency();

        Journey journey = new Journey();
        journey.setId(id);
        journey.setCountry(country);
        journey.setTown(town);
        journey.setDateToJourney(dateToJourney);
        journey.setDateFromJourney(dateFromJourney);
        journey.setTravelAgency(travelAgency);

        assertEquals(id, journey.getId());
        assertEquals(country, journey.getCountry());
        assertEquals(town, journey.getTown());
        assertEquals(dateToJourney, journey.getDateToJourney());
        assertEquals(dateFromJourney, journey.getDateFromJourney());
        assertEquals(travelAgency, journey.getTravelAgency());
    }

    @Test
    void testToString() {
        // Arrange
        Journey journey = new Journey();
        journey.setId(1L);
        journey.setCountry("Country");
        journey.setTown("Town");

        // Act
        String journeyString = journey.toString();

        // Assert
        assertTrue(journeyString.contains("id=1"));
        assertTrue(journeyString.contains("country=Country"));
        assertTrue(journeyString.contains("town=Town"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Journey journey1 = new Journey();
        journey1.setId(1L);
        journey1.setCountry("Country");
        journey1.setTown("Town");

        Journey journey2 = new Journey();
        journey2.setId(1L);
        journey2.setCountry("Country");
        journey2.setTown("Town");

        Journey journey3 = new Journey();
        journey3.setId(2L);
        journey3.setCountry("Country");
        journey3.setTown("Town");

        // Assert
        assertEquals(journey1, journey2);
        assertNotEquals(journey1, journey3);
        assertEquals(journey1.hashCode(), journey2.hashCode());
        assertNotEquals(journey1.hashCode(), journey3.hashCode());
    }
    @Test
    void testConstructorWithNullValues() {
        // Arrange
        Journey journey = new Journey();

        // Act
        journey.setId(null);
        journey.setCountry(null);
        journey.setTown(null);
        journey.setDateToJourney(null);
        journey.setDateFromJourney(null);
        journey.setTravelAgency(null);

        // Assert
        assertNull(journey.getId());
        assertNull(journey.getCountry());
        assertNull(journey.getTown());
        assertNull(journey.getDateToJourney());
        assertNull(journey.getDateFromJourney());
        assertNull(journey.getTravelAgency());
    }

    @Test
    void testEqualsAndHashCodeWithNullObject() {
        // Arrange
        Journey journey = new Journey();
        journey.setId(1L);
        journey.setCountry("Country");
        journey.setTown("Town");

        // Act
        boolean resultEquals = journey == null;
        int hashCode = journey.hashCode();

        // Assert
        assertFalse(resultEquals);
        assertNotEquals(0, hashCode);
    }
    @Test
    void testEqualsAndHashCodeWithDifferentClass() {
        // Arrange
        Journey journey = new Journey();
        journey.setId(1L);
        journey.setCountry("Country");
        journey.setTown("Town");

        // Act
        boolean resultEquals = journey.equals("not_a_journey");
        int hashCode = journey.hashCode();

        // Assert
        assertFalse(resultEquals);
        assertNotEquals(0, hashCode);
    }

    @Test
    void testEqualsAndHashCodeWithSameObject() {
        // Arrange
        Journey journey = new Journey();
        journey.setId(1L);
        journey.setCountry("Country");
        journey.setTown("Town");

        // Act
        boolean resultEquals = journey.equals(journey);
        int hashCode = journey.hashCode();

        // Assert
        assertTrue(resultEquals);
        assertEquals(journey.hashCode(), hashCode);
    }

    @Test
    void testEqualsAndHashCodeWithDifferentFieldValues() {
        // Arrange
        Journey journey1 = new Journey();
        journey1.setId(1L);
        journey1.setCountry("Country1");
        journey1.setTown("Town1");

        Journey journey2 = new Journey();
        journey2.setId(2L);
        journey2.setCountry("Country2");
        journey2.setTown("Town2");

        // Assert
        assertNotEquals(journey1, journey2);
        assertNotEquals(journey1.hashCode(), journey2.hashCode());
    }
}