package by.baranova.journeyjava.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TravelAgencyDtoTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testTravelAgencyDtoValidationSuccess() {
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        travelAgencyDto.setId(1L);
        travelAgencyDto.setName("Travel World");

        Set<ConstraintViolation<TravelAgencyDto>> violations = validator.validate(travelAgencyDto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void testTravelAgencyDtoValidationFailure() {
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        travelAgencyDto.setId(null);
        travelAgencyDto.setName("");

        Set<ConstraintViolation<TravelAgencyDto>> violations = validator.validate(travelAgencyDto);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testGettersAndSetters() {
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        Long id = 1L;
        String name = "Agency";

        travelAgencyDto.setId(id);
        travelAgencyDto.setName(name);

        assertEquals(id, travelAgencyDto.getId());
        assertEquals(name, travelAgencyDto.getName());
    }

    @Test
    void testToString() {
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        travelAgencyDto.setId(1L);
        travelAgencyDto.setName("Agency");

        String travelAgencyDtoString = travelAgencyDto.toString();

        assertTrue(travelAgencyDtoString.contains("id=1"));
        assertTrue(travelAgencyDtoString.contains("name=Agency"));
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        TravelAgencyDto travelAgencyDto1 = new TravelAgencyDto();
        travelAgencyDto1.setId(1L);
        travelAgencyDto1.setName("Agency");

        TravelAgencyDto travelAgencyDto2 = new TravelAgencyDto();
        travelAgencyDto2.setId(1L);
        travelAgencyDto2.setName("Agency");

        TravelAgencyDto travelAgencyDto3 = new TravelAgencyDto();
        travelAgencyDto3.setId(2L);
        travelAgencyDto3.setName("Agency");

        assertEquals(travelAgencyDto1, travelAgencyDto2);
        assertNotEquals(travelAgencyDto1, travelAgencyDto3);
        assertEquals(travelAgencyDto1.hashCode(), travelAgencyDto2.hashCode());
        assertNotEquals(travelAgencyDto1.hashCode(), travelAgencyDto3.hashCode());
    }

    @Test
    void getId() {
        Long id = 1L;
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        travelAgencyDto.setId(id);

        assertEquals(id, travelAgencyDto.getId());
    }

    @Test
    void getName() {
        String name = "TestName";
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();
        travelAgencyDto.setName(name);

        assertEquals(name, travelAgencyDto.getName());
    }

    @Test
    void setId() {
        Long id = 1L;
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();

        travelAgencyDto.setId(id);

        assertEquals(id, travelAgencyDto.getId());
    }

    @Test
    void setName() {
        String name = "TestName";
        TravelAgencyDto travelAgencyDto = new TravelAgencyDto();

        travelAgencyDto.setName(name);

        assertEquals(name, travelAgencyDto.getName());
    }

    @Test
    void canEqual() {
        // Arrange
        TravelAgencyDto travelAgencyDto1 = new TravelAgencyDto();
        travelAgencyDto1.setId(1L);
        travelAgencyDto1.setName("TestName");

        TravelAgencyDto travelAgencyDto2 = new TravelAgencyDto();
        travelAgencyDto2.setId(1L);
        travelAgencyDto2.setName("TestName");

        // Act & Assert
        assertTrue(travelAgencyDto1.canEqual(travelAgencyDto2));
    }

    @Test
    void testHashCode() {
        TravelAgencyDto travelAgencyDto1 = new TravelAgencyDto();
        travelAgencyDto1.setId(1L);
        travelAgencyDto1.setName("TestName");

        TravelAgencyDto travelAgencyDto2 = new TravelAgencyDto();
        travelAgencyDto2.setId(1L);
        travelAgencyDto2.setName("TestName");

        assertEquals(travelAgencyDto1.hashCode(), travelAgencyDto2.hashCode());
    }
}