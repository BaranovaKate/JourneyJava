package by.baranova.journeyjava.service;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.TravelAgency;
import by.baranova.journeyjava.repository.JourneyRepository;
import by.baranova.journeyjava.repository.TravelAgencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AgencyServiceTest {

    private AgencyService agencyService;
    private TravelAgencyRepository travelAgencyRepository;
    private JourneyRepository journeyRepository;

    @BeforeEach
    void setUp() {
        travelAgencyRepository = mock(TravelAgencyRepository.class);
        journeyRepository = mock(JourneyRepository.class);
        agencyService = new AgencyService(travelAgencyRepository, journeyRepository);
    }

    @Test
    void testFindAgencyById_ExistingId() {
        // Arrange
        Long id = 1L;
        TravelAgency expectedAgency = new TravelAgency();
        expectedAgency.setId(id);
        when(travelAgencyRepository.findById(id)).thenReturn(expectedAgency);

        // Act
        TravelAgency result = agencyService.findAgencyById(id);

        // Assert
        assertEquals(expectedAgency, result);
    }

    @Test
    void testFindAgencyById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(travelAgencyRepository.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> agencyService.findAgencyById(id));
    }

    @Test
    void testSave() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();

        // Act
        agencyService.save(travelAgency);

        // Assert
        verify(travelAgencyRepository).save(travelAgency);
    }

    @Test
    void testDeleteById_ExistingId() {
        // Arrange
        Long id = 1L;
        TravelAgency agencyToDelete = new TravelAgency();
        agencyToDelete.setId(id);
        List<Journey> journeysWithAgency = new ArrayList<>();
        when(travelAgencyRepository.findById(id)).thenReturn(agencyToDelete);
        when(journeyRepository.findByTravelAgencyId(id)).thenReturn(journeysWithAgency);

        // Act
        agencyService.deleteById(id);

        // Assert
        verify(journeyRepository).findByTravelAgencyId(id);
        verify(journeyRepository, times(journeysWithAgency.size())).deleteById(any());
        verify(travelAgencyRepository).deleteById(id);
    }

    @Test
    void testDeleteById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(travelAgencyRepository.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> agencyService.deleteById(id));
    }

    @Test
    void testFindAgencies() {
        // Arrange
        List<TravelAgency> expectedAgencies = new ArrayList<>();
        when(travelAgencyRepository.findAllWithJourneys()).thenReturn(expectedAgencies);

        // Act
        List<TravelAgency> result = agencyService.findAgencies();

        // Assert
        assertEquals(expectedAgencies, result);
    }

    @Test
    void testUpdate_ExistingId() {
        // Arrange
        Long id = 1L;
        TravelAgencyDto updatedAgency = new TravelAgencyDto();
        TravelAgency agency = new TravelAgency();
        when(travelAgencyRepository.findById(id)).thenReturn(agency);

        // Act
        agencyService.update(id, updatedAgency);

        // Assert
        verify(travelAgencyRepository).update(id, updatedAgency);
    }

    @Test
    void testUpdate_NonExistingId() {
        // Arrange
        Long id = 1L;
        TravelAgencyDto updatedAgency = new TravelAgencyDto();
        when(travelAgencyRepository.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> agencyService.update(id, updatedAgency));
    }
}
