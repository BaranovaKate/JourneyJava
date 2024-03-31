package by.baranova.journeyjava.service;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.TravelAgency;
import by.baranova.journeyjava.repository.JourneyRepository;
import by.baranova.journeyjava.repository.TravelAgencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AgencyService {

    private final TravelAgencyRepository travelAgencyRepository;

    private final JourneyRepository journeyRepository;


    public TravelAgency findAgencyById(final Long id) {
        TravelAgency agency = travelAgencyRepository.findById(id);
        if (agency == null) {
            throw new EntityNotFoundException(
                    "Agency with ID " + id + " not found");
        }
        return agency;
    }

    public void save(final TravelAgency travelAgency) {
        travelAgencyRepository.save(travelAgency);
    }

    public void deleteById(final Long id) {
        TravelAgency agencyToDelete = findAgencyById(id);
        if (agencyToDelete == null) {
            throw new EntityNotFoundException(
                    "Travel Agency with ID " + id + " not found");
        }
        List<Journey> journeysWithAgency = journeyRepository
                .findByTravelAgencyId(id);
        journeysWithAgency.forEach(journey -> journeyRepository
                .deleteById(journey.getId()));
        travelAgencyRepository.deleteById(id);
    }

    public List<TravelAgency> findAgencies() {
        return travelAgencyRepository.findAllWithJourneys();
    }

    public void update(final Long id, final TravelAgencyDto updatedAgency) {
        TravelAgency agency = findAgencyById(id);
        if (agency == null) {
            throw new EntityNotFoundException(
                    "Travel agency with ID " + id + " does not exist");
        }
        travelAgencyRepository.update(id, updatedAgency);
    }
}
