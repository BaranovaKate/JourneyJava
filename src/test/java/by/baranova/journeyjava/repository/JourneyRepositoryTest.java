package by.baranova.journeyjava.repository;

import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.mapper.JourneyMapper;
import by.baranova.journeyjava.model.Journey;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


class JourneyRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private JourneyMapper journeyMapper;

    @InjectMocks
    private JourneyRepository journeyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        // Mocking the data
        List<Journey> journeys = List.of(new Journey(), new Journey());
        when(sessionFactory.fromSession(any())).thenReturn(journeys);

        // Calling the method under test
        List<JourneyDto> journeyDtos = journeyRepository.findAll();

        // Assertions
        assertEquals(2, journeyDtos.size());
        // Add more assertions based on the expected behavior
    }

    @Test
    void testFindById() {
        // Mocking the data
        Long id = 1L;
        Journey journey = new Journey();
        when(sessionFactory.fromSession(any())).thenReturn(journey);
        when(journeyMapper.toDto(journey)).thenReturn(new JourneyDto());

        // Calling the method under test
        Optional<JourneyDto> journeyDtoOptional = journeyRepository.findById(id);

        // Assertions
        assertTrue(journeyDtoOptional.isPresent());
        // Add more assertions based on the expected behavior
    }


    @Test
    void testDeleteById_JourneyNotExists() {
        // Mocking the data
        Long id = 1L;
        when(journeyRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the method under test
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            journeyRepository.deleteById(id);
        });

        // Assertions
        assertEquals("Journey with id " + id + " not found", exception.getMessage());
        // Add more assertions based on the expected behavior
    }

    @Test
    void testSave() {
        // Mocking the data
        JourneyDto journeyDto = new JourneyDto();
        Journey journey = new Journey();
        when(journeyMapper.toEntity(journeyDto)).thenReturn(journey);

        // Calling the method under test
        journeyRepository.save(journeyDto);

        // Verify that sessionFactory.inTransaction() is called
        verify(sessionFactory, times(1)).inTransaction(any());
        // Add more verifications based on the expected behavior
    }


    @Test
    void testUpdate_JourneyNotExists() {
        // Mocking the data
        Long id = 1L;
        when(journeyRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the method under test
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            journeyRepository.update(id, new JourneyDto());
        });

        // Assertions
        assertEquals("Journey with id " + id + " does not exist", exception.getMessage());
        // Add more assertions based on the expected behavior
    }

}
