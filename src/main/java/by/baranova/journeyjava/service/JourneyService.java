package by.baranova.journeyjava.service;

import by.baranova.journeyjava.cache.Cache;
import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.repository.JourneyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    private final Cache cache;

    static final Logger LOGGER = LogManager.getLogger(JourneyService.class);

    public JourneyDto findJourneyById(final Long id) {
        JourneyDto journey = (JourneyDto) cache.get("journey_" + id);
        if (journey == null) {
            journey = journeyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Journey with ID " + id + " not found"));
            cache.put("journey_" + id, journey);
        } else {
            LOGGER.info("Display Journey from cache");
        }
        return journey;
    }

    public void deleteById(final Long id) {
        journeyRepository.deleteById(id);
        cache.clear();
    }

    public List<JourneyDto> findJourneys() {
        List<JourneyDto> journeys = (List<JourneyDto>) cache.get("journeys");
        if (journeys == null) {
            journeys = journeyRepository.findAll();
            cache.put("journeys", journeys);
        } else {
            LOGGER.info("Display Journeys from cache");
        }
        return journeys;
    }

    public List<JourneyDto> findJourneysByCountry(final String country) {
        List<JourneyDto> journeys = (List<JourneyDto>) cache
                .get("journeys_" + country);
        if (journeys == null) {
            journeys = journeyRepository.findByCountry(country);
            cache.put("journeys_" + country, journeys);
        } else {
            LOGGER.info("Display Journeys from cache");
        }
        return journeys;
    }

    public void createJourneysBulk(List<JourneyDto> journeyDtos, String agency) {
        if (journeyDtos == null || journeyDtos.isEmpty()) {
            throw new IllegalArgumentException("No journeys provided");
        }
        boolean agencyNameConflict = journeyDtos.stream()
                .anyMatch(journeyDto -> {
                    TravelAgencyDto travelAgency = journeyDto.getTravelAgency();
                    return travelAgency == null || !travelAgency.getName().equals(agency);
                });
        if (agencyNameConflict) {
            throw new IllegalArgumentException("Agency name in URL does not" +
                    " match agency name in provided journey data");
        }
        List<String> errors = journeyDtos.stream()
                .map(journeyDto -> {
                    try {
                        journeyRepository.save(journeyDto);
                        return null;
                    } catch (Exception e) {
                        return "Error creating journey: " + e.getMessage();
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Errors occurred during bulk creation:\n"
                    + String.join("\n", errors));
        }
    }

    public void save(final JourneyDto journeyDto) {
        journeyRepository.save(journeyDto);
        cache.clear();
    }

    public void update(final Long id, final JourneyDto journey) {
        journeyRepository.update(id, journey);
        cache.clear();
    }
}
