package by.baranova.journeyjava.repository;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import jakarta.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TravelAgencyRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private TravelAgencyRepository travelAgencyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testDeleteById_NonExistingAgency() {
        // Mocking the data
        Long id = 1L;
        when(session.get(TravelAgency.class, id)).thenReturn(null);

        // Calling the method under test
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            travelAgencyRepository.deleteById(id);
        });

        // Assertions
        assertEquals("Агентство с id " + id + " не найдено", exception.getMessage());
    }

    @Test
    void testUpdate_NonExistingAgency() {
        // Mocking the data
        Long id = 1L;
        TravelAgencyDto updatedAgency = new TravelAgencyDto();
        when(session.get(TravelAgency.class, id)).thenReturn(null);

        // Calling the method under test
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            travelAgencyRepository.update(id, updatedAgency);
        });

        // Assertions
        assertEquals("Агентство с id " + id + " не существует", exception.getMessage());
    }

    @Test
    void testSave() {
        // Arrange
        SessionFactory sessionFactory = mock(SessionFactory.class);
        TravelAgency travelAgency = new TravelAgency();

        TravelAgencyRepository travelAgencyRepository = new TravelAgencyRepository(sessionFactory);

        // Act
        travelAgencyRepository.save(travelAgency);

        // Assert
        verify(sessionFactory).inTransaction(any());
    }
}