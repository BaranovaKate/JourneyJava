package by.baranova.journeyjava.service;

import by.baranova.journeyjava.cache.Cache;
import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.repository.JourneyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JourneyServiceTest {

    @Mock
    private JourneyRepository journeyRepository;

    @Mock
    private Cache cache;

    @InjectMocks
    private JourneyService journeyService;

    private JourneyDto testJourneyDto;

    @BeforeEach
    void setUp() {
        testJourneyDto = new JourneyDto();
        testJourneyDto.setId(1L);
        testJourneyDto.setCountry("Test Country");
    }

    @Test
    void testFindJourneyById_ExistingId() {
        Long id = 1L;
        JourneyDto expectedJourney = new JourneyDto();
        expectedJourney.setId(id);
        when(cache.get("journey_" + id)).thenReturn(null);
        when(journeyRepository.findById(id)).thenReturn(Optional.of(expectedJourney));

        JourneyDto result = journeyService.findJourneyById(id);

        assertEquals(expectedJourney, result);
        verify(cache, times(1)).put("journey_" + id, expectedJourney);
    }

    @Test
    void testFindJourneyById_NonExistingId() {
        Long id = 1L;
        when(cache.get("journey_" + id)).thenReturn(null);
        when(journeyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> journeyService.findJourneyById(id));
    }

    @Test
    void deleteById_ExistingId_DeletesJourneyAndClearsCache() {
        journeyService.deleteById(1L);

        verify(journeyRepository, times(1)).deleteById(1L);
        verify(cache, times(1)).clear();
    }

    @Test
    void findJourneys_EmptyCache_ReturnsJourneysFromRepository() throws InterruptedException {
        when(cache.get("journeys")).thenReturn(null);
        when(journeyRepository.findAll()).thenReturn(Collections.singletonList(testJourneyDto));

        List<JourneyDto> result = journeyService.findJourneys();

        assertEquals(1, result.size());
        assertEquals(testJourneyDto, result.get(0));
    }

    @Test
    void findJourneys_CacheNotEmpty_ReturnsJourneysFromCache() throws InterruptedException {
        when(cache.get("journeys")).thenReturn(Collections.singletonList(testJourneyDto));

        List<JourneyDto> result = journeyService.findJourneys();

        assertEquals(1, result.size());
        assertEquals(testJourneyDto, result.get(0));
    }

    @Test
    void findJourneysByCountry_EmptyCache_ReturnsJourneysFromRepository() {
        String country = "Test Country";
        when(cache.get("journeys_" + country)).thenReturn(null);
        when(journeyRepository.findByCountry(country)).thenReturn(Collections.singletonList(testJourneyDto));

        List<JourneyDto> result = journeyService.findJourneysByCountry(country);

        assertEquals(1, result.size());
        assertEquals(testJourneyDto, result.get(0));
    }

    @Test
    void findJourneysByCountry_CacheNotEmpty_ReturnsJourneysFromCache() {
        String country = "Test Country";
        when(cache.get("journeys_" + country)).thenReturn(Collections.singletonList(testJourneyDto));

        List<JourneyDto> result = journeyService.findJourneysByCountry(country);

        assertEquals(1, result.size());
        assertEquals(testJourneyDto, result.get(0));
    }

    @Test
    void createJourneysBulk_ValidData() {

        JourneyRepository journeyRepository = mock(JourneyRepository.class);
        Cache cache = mock(Cache.class);
        JourneyService journeyService = new JourneyService(journeyRepository, cache);

        TravelAgencyDto testAgency = new TravelAgencyDto();
        testAgency.setName("TestAgency");

        JourneyDto testJourneyDto = new JourneyDto();
        testJourneyDto.setTravelAgency(testAgency);

        List<JourneyDto> journeyDtos = Collections.singletonList(testJourneyDto);
        String agency = "TestAgency";

        doNothing().when(journeyRepository).save(any(JourneyDto.class));

        assertDoesNotThrow(() -> journeyService.createJourneysBulk(journeyDtos, agency));
    }

    @Test
    void createJourneysBulk_NullJourneyList_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> journeyService.createJourneysBulk(null, "TestAgency"));
    }

    @Test
    void createJourneysBulk_JourneyListWithMismatchedAgencyName_ThrowsIllegalArgumentException() {

        List<JourneyDto> journeyDtos = Collections.singletonList(testJourneyDto);

        assertThrows(IllegalArgumentException.class, () -> journeyService.createJourneysBulk(journeyDtos, "MismatchedAgency"));
    }

    @Test
    void createJourneysBulk_ThrowsRuntimeException() {
        List<JourneyDto> journeyDtos = Collections.singletonList(testJourneyDto);

        lenient().doThrow(new RuntimeException("Test exception")).when(journeyRepository).save(any(JourneyDto.class));

        assertThrows(RuntimeException.class, () -> journeyService.createJourneysBulk(journeyDtos, "TestAgency"));
    }

    @Test
    void testUpdate() {

        Long id = 1L;
        JourneyDto journeyDto = new JourneyDto();

        journeyService.update(id, journeyDto);

        verify(journeyRepository, times(1)).update(id, journeyDto);
        verify(cache, times(1)).clear();
    }
}
