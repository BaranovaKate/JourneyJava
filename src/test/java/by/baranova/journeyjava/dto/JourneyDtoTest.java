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
}
