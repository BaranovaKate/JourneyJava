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
//
//@ExtendWith(MockitoExtension.class)
//class AgencyServiceTest {
//
//    @Mock
//    private TravelAgencyRepository travelAgencyRepository;
//
//    @Mock
//    private JourneyRepository journeyRepository;
//
//    @InjectMocks
//    private AgencyService agencyService;
//
//    @Test
//    void testFindAgencyById_ExistingId() {
//        Long id = 1L;
//        TravelAgency expectedAgency = new TravelAgency();
//        expectedAgency.setId(id);
//        when(travelAgencyRepository.findById(id)).thenReturn(expectedAgency);
//
//        TravelAgency result = agencyService.findAgencyById(id);
//
//        assert result != null;
//        assert result.getId().equals(id);
//    }
//
//    @Test
//    void testFindAgencyById_NonExistingId() {
//        Long id = 1L;
//        when(travelAgencyRepository.findById(id)).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class, () -> agencyService.findAgencyById(id));
//    }
//
//    @Test
//    void testSave() {
//        TravelAgency travelAgency = new TravelAgency();
//
//        agencyService.save(travelAgency);
//
//        verify(travelAgencyRepository, times(1)).save(travelAgency);
//    }
//
//    @Test
//    void testDeleteById_ExistingId() {
//        Long id = 1L;
//        TravelAgency agencyToDelete = new TravelAgency();
//        agencyToDelete.setId(id);
//        List<Journey> journeysWithAgency = new ArrayList<>();
//        when(travelAgencyRepository.findById(id)).thenReturn(agencyToDelete);
//        when(journeyRepository.findByTravelAgencyId(id)).thenReturn(journeysWithAgency);
//
//        agencyService.deleteById(id);
//
//        verify(travelAgencyRepository, times(1)).deleteById(id);
//        verify(journeyRepository, times(1)).findByTravelAgencyId(id);
//        journeysWithAgency.forEach(journey -> verify(journeyRepository, times(1)).deleteById(journey.getId()));
//    }
//
//    @Test
//    void testDeleteById_NonExistingId() {
//        Long id = 1L;
//        when(travelAgencyRepository.findById(id)).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class, () -> agencyService.deleteById(id));
//    }
//    @Test
//    void testFindAgencies() {
//        List<TravelAgency> expectedAgencies = new ArrayList<>();
//        when(travelAgencyRepository.findAllWithJourneys()).thenReturn(expectedAgencies);
//
//        List<TravelAgency> result = agencyService.findAgencies();
//
//        assertEquals(expectedAgencies, result);
//    }
//
//    @Test
//    void testUpdate_ExistingId() {
//        Long id = 1L;
//        TravelAgencyDto updatedAgencyDto = new TravelAgencyDto();
//        TravelAgency agency = new TravelAgency();
//        agency.setId(id);
//        when(travelAgencyRepository.findById(id)).thenReturn(agency);
//
//        agencyService.update(id, updatedAgencyDto);
//
//        verify(travelAgencyRepository, times(1)).update(id, updatedAgencyDto);
//    }
//
//    @Test
//    void testUpdate_NonExistingId() {
//        Long id = 1L;
//        TravelAgencyDto updatedAgencyDto = new TravelAgencyDto();
//        when(travelAgencyRepository.findById(id)).thenReturn(null);
//
//        assertThrows(EntityNotFoundException.class, () -> agencyService.update(id, updatedAgencyDto));
//    }
//}
