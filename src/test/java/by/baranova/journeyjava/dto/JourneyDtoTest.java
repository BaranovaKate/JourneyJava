package by.baranova.journeyjava.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JourneyDtoTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testJourneyDtoValidationSuccess() {
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setCountry("France");
        journeyDto.setTown("Paris");
        journeyDto.setDateFromJourney(LocalDate.of(2024, 3, 10));
        journeyDto.setDateToJourney(LocalDate.of(2024, 3, 15));

        Set<ConstraintViolation<JourneyDto>> violations = validator.validate(journeyDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testJourneyDtoValidationFailure() {
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setCountry("");
        journeyDto.setTown("London");
        journeyDto.setDateFromJourney(LocalDate.of(2024, 3, 10));
        journeyDto.setDateToJourney(LocalDate.of(2024, 3, 5));

        Set<ConstraintViolation<JourneyDto>> violations = validator.validate(journeyDto);

        assertEquals(2, violations.size());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        JourneyDto journeyDto = new JourneyDto();
        Long id = 1L;
        String country = "Country";
        String town = "Town";
        LocalDate dateToJourney = LocalDate.now().plusDays(7);
        LocalDate dateFromJourney = LocalDate.now();

        // Act
        journeyDto.setId(id);
        journeyDto.setCountry(country);
        journeyDto.setTown(town);
        journeyDto.setDateToJourney(dateToJourney);
        journeyDto.setDateFromJourney(dateFromJourney);

        // Assert
        assertEquals(id, journeyDto.getId());
        assertEquals(country, journeyDto.getCountry());
        assertEquals(town, journeyDto.getTown());
        assertEquals(dateToJourney, journeyDto.getDateToJourney());
        assertEquals(dateFromJourney, journeyDto.getDateFromJourney());
    }

    @Test
    void testToString() {
        // Arrange
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setId(1L);
        journeyDto.setCountry("Country");
        journeyDto.setTown("Town");
        journeyDto.setDateToJourney(LocalDate.now().plusDays(7));
        journeyDto.setDateFromJourney(LocalDate.now());

        // Act
        String journeyDtoString = journeyDto.toString();

        // Assert
        assertTrue(journeyDtoString.contains("id=1"));
        assertTrue(journeyDtoString.contains("country=Country"));
        assertTrue(journeyDtoString.contains("town=Town"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        JourneyDto journeyDto1 = new JourneyDto();
        journeyDto1.setId(1L);
        journeyDto1.setCountry("Country");
        journeyDto1.setTown("Town");
        journeyDto1.setDateToJourney(LocalDate.now().plusDays(7));
        journeyDto1.setDateFromJourney(LocalDate.now());

        JourneyDto journeyDto2 = new JourneyDto();
        journeyDto2.setId(1L);
        journeyDto2.setCountry("Country");
        journeyDto2.setTown("Town");
        journeyDto2.setDateToJourney(LocalDate.now().plusDays(7));
        journeyDto2.setDateFromJourney(LocalDate.now());

        JourneyDto journeyDto3 = new JourneyDto();
        journeyDto3.setId(2L);
        journeyDto3.setCountry("Country");
        journeyDto3.setTown("Town");
        journeyDto3.setDateToJourney(LocalDate.now().plusDays(7));
        journeyDto3.setDateFromJourney(LocalDate.now());

        // Assert
        assertEquals(journeyDto1, journeyDto2);
        assertNotEquals(journeyDto1, journeyDto3);
        assertEquals(journeyDto1.hashCode(), journeyDto2.hashCode());
        assertNotEquals(journeyDto1.hashCode(), journeyDto3.hashCode());
    }

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setId(id);

        // Act & Assert
        assertEquals(id, journeyDto.getId());
    }

    @Test
    void getCountry() {
        // Arrange
        String country = "TestCountry";
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setCountry(country);

        // Act & Assert
        assertEquals(country, journeyDto.getCountry());
    }
    @Test
    void testEquals() {
        // Arrange
        JourneyDto journeyDto1 = new JourneyDto();
        JourneyDto journeyDto2 = new JourneyDto();

        // Act & Assert
        assertEquals(journeyDto1, journeyDto2);
    }

    @Test
    void canEqual() {
        // Arrange
        JourneyDto journeyDto1 = new JourneyDto();
        JourneyDto journeyDto2 = new JourneyDto();

        // Act & Assert
        assertTrue(journeyDto1.canEqual(journeyDto2));
    }
    @Test
    void getTown() {
        // Arrange
        String town = "TestTown";
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setTown(town);

        // Act & Assert
        assertEquals(town, journeyDto.getTown());
    }

    @Test
    void getDateToJourney() {
        // Arrange
        LocalDate dateToJourney = LocalDate.of(2022, 4, 1);
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setDateToJourney(dateToJourney);

        // Act & Assert
        assertEquals(dateToJourney, journeyDto.getDateToJourney());
    }

    @Test
    void getDateFromJourney() {
        // Arrange
        LocalDate dateFromJourney = LocalDate.of(2022, 3, 30);
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setDateFromJourney(dateFromJourney);

        // Act & Assert
        assertEquals(dateFromJourney, journeyDto.getDateFromJourney());
    }

    @Test
    void getTravelAgency() {
        // Arrange
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        JourneyDto journeyDto = new JourneyDto();
        journeyDto.setTravelAgency(travelAgencyDto);

        // Act & Assert
        assertEquals(travelAgencyDto, journeyDto.getTravelAgency());
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setId(id);

        // Assert
        assertEquals(id, journeyDto.getId());
    }

    @Test
    void setCountry() {
        // Arrange
        String country = "TestCountry";
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setCountry(country);

        // Assert
        assertEquals(country, journeyDto.getCountry());
    }

    @Test
    void setTown() {
        // Arrange
        String town = "TestTown";
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setTown(town);

        // Assert
        assertEquals(town, journeyDto.getTown());
    }

    @Test
    void setDateToJourney() {
        // Arrange
        LocalDate dateToJourney = LocalDate.of(2022, 4, 1);
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setDateToJourney(dateToJourney);

        // Assert
        assertEquals(dateToJourney, journeyDto.getDateToJourney());
    }

    @Test
    void setDateFromJourney() {
        // Arrange
        LocalDate dateFromJourney = LocalDate.of(2022, 3, 30);
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setDateFromJourney(dateFromJourney);

        // Assert
        assertEquals(dateFromJourney, journeyDto.getDateFromJourney());
    }

    @Test
    void setTravelAgency() {
        // Arrange
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        JourneyDto journeyDto = new JourneyDto();

        // Act
        journeyDto.setTravelAgency(travelAgencyDto);

        // Assert
        assertEquals(travelAgencyDto, journeyDto.getTravelAgency());
    }
}
