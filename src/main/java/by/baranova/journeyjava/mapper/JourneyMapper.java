package by.baranova.journeyjava.mapper;

import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.JourneyDto;
import org.springframework.stereotype.Component;

@Component
public class JourneyMapper {
    public JourneyDto toDto(Journey journey) {
        final JourneyDto dto = new JourneyDto();
        dto.setId(journey.getId());
        dto.setCountry(journey.getCountry());
        dto.setTown(journey.getTown());
        dto.setDateToJourney(journey.getDateToJourney());
        dto.setDateFromJourney(journey.getDateFromJourney());

        return dto;
    }

    public Journey toEntity(JourneyDto dto) {
        final Journey entity = new Journey();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        entity.setTown(dto.getTown());
        entity.setDateToJourney(dto.getDateToJourney());
        entity.setDateFromJourney(dto.getDateFromJourney());

        return entity;
    }
}
