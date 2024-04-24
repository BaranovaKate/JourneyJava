package by.baranova.journeyjava.mapper;

import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.TravelAgency;
import by.baranova.journeyjava.repository.TravelAgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * The JourneyMapper class is responsible for converting
 * between Journey entities and JourneyDto
 * data transfer objects.
 */
@AllArgsConstructor
@Component
public class JourneyMapper {
    /** Repository for travel Agencies.
     */
    private final TravelAgencyRepository travelAgencyRepository;

    public JourneyDto toDto(final Journey journey) {
        final JourneyDto dto = new JourneyDto();
        dto.setId(journey.getId());
        dto.setCountry(journey.getCountry());
        dto.setTown(journey.getTown());
        dto.setDateToJourney(journey.getDateToJourney());
        dto.setDateFromJourney(journey.getDateFromJourney());
        dto.setTravelAgency(
                TravelAgencyMapper.toDto(journey.getTravelAgency()));
        return dto;
    }

    public Journey toEntity(final JourneyDto dto) {
        final Journey entity = new Journey();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        entity.setTown(dto.getTown());
        entity.setDateToJourney(dto.getDateToJourney());
        entity.setDateFromJourney(dto.getDateFromJourney());
        TravelAgency travelAgency = travelAgencyRepository
                .findById(dto.getTravelAgency().getId());
        entity.setTravelAgency(travelAgency);

        return entity;
    }
}
