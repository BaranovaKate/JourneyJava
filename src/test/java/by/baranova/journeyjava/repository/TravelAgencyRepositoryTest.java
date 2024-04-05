package by.baranova.journeyjava.repository;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

//    @Test
//    void testFindById_ExistingAgency() {
//        // Mocking the data
//        Long id = 1L;
//        TravelAgency travelAgency = new TravelAgency();
//        when(session.get(TravelAgency.class, id)).thenReturn(travelAgency);
//
//        // Calling the method under test
//        TravelAgency result = travelAgencyRepository.findById(id);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals(travelAgency, result);
//    }

//    @Test
//    void testFindById_NonExistingAgency() {
//        // Mocking the data
//        Long id = 1L;
//        when(session.get(TravelAgency.class, id)).thenReturn(null);
//
//        // Calling the method under test and verifying the thrown exception
//        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
//            travelAgencyRepository.findById(id);
//        });
//
//        // Assertions
//        assertNotNull(exception);
//        assertEquals("Агентство с id " + id + " не найдено", exception.getMessage());
//    }

//    @Test
//    void testFindAllWithJourneys() {
//        // Mocking the data
//        List<TravelAgency> agencies = List.of(new TravelAgency(), new TravelAgency());
//        Query<TravelAgency> query = session.createQuery("FROM TravelAgency J LEFT JOIN FETCH J.journeys", TravelAgency.class);
//        when(session.createQuery(anyString(), eq(TravelAgency.class))).thenReturn(query);
//        when(query.list()).thenReturn(agencies);
//
//        // Calling the method under test
//        List<TravelAgency> result = travelAgencyRepository.findAllWithJourneys();
//
//        // Assertions
//        assertEquals(2, result.size());
//        assertEquals(agencies, result);
//    }
//    @Test
//    void testSave() {
//        // Mocking the data
//        TravelAgency travelAgency = new TravelAgency();
//
//        // Calling the method under test
//        travelAgencyRepository.save(travelAgency);
//
//        // Verify that sessionFactory.inTransaction() is called
//        verify(session).persist(travelAgency);
//    }

//    @Test
//    void testDeleteById_ExistingAgency() {
//        // Mocking the data
//        Long id = 1L;
//        TravelAgency travelAgency = new TravelAgency();
//        when(session.get(TravelAgency.class, id)).thenReturn(travelAgency);
//
//        // Calling the method under test
//        travelAgencyRepository.deleteById(id);
//
//        // Verify that sessionFactory.inTransaction() is called
//        verify(session).delete(travelAgency);
//    }

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

//    @Test
//    void testUpdate_ExistingAgency() {
//        // Mocking the data
//        Long id = 1L;
//        TravelAgencyDto updatedAgency = new TravelAgencyDto();
//        updatedAgency.setName("Updated Name");
//        TravelAgency travelAgency = new TravelAgency();
//        when(session.get(TravelAgency.class, id)).thenReturn(travelAgency);
//
//        // Calling the method under test
//        travelAgencyRepository.update(id, updatedAgency);
//
//        // Assertions
//        assertEquals(updatedAgency.getName(), travelAgency.getName());
//    }

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
}