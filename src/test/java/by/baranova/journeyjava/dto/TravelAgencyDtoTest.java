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
}