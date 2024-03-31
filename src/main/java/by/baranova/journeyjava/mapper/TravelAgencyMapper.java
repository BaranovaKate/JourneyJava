package by.baranova.journeyjava.mapper;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between
 * {@link TravelAgency} and {@link TravelAgencyDto}.
 */

@Component
public final class TravelAgencyMapper {

    /**
     * Converts a {@link TravelAgency}
     * entity to a {@link TravelAgencyDto}.
     *
     * @param travelAgency The source {@link TravelAgency} entity.
     * @return A new {@link TravelAgencyDto}
     * instance representing the converted data.
     */
    public static TravelAgencyDto toDto(final TravelAgency travelAgency) {
        final TravelAgencyDto dto = new TravelAgencyDto();
        dto.setId(travelAgency.getId());
        dto.setName(travelAgency.getName());
        return dto;
    }

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private TravelAgencyMapper() {
    }
}
