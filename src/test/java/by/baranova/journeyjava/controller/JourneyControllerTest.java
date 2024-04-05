package by.baranova.journeyjava.controller;
import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.service.CounterService;
import by.baranova.journeyjava.service.JourneyService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JourneyControllerTest {

    @Mock
    JourneyService journeyService;

    @Mock
    CounterService counterService;

    @Mock
    Model model;

    @InjectMocks
    JourneyController journeyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindJourneys_NoCountry() {
        // Mocking the behavior
        when(journeyService.findJourneys()).thenReturn(List.of(new JourneyDto()));

        // Calling the method under test
        String viewName = journeyController.findJourneys(null, model);

        // Assertions
        assertEquals("journeys/list", viewName);
        verify(journeyService, times(1)).findJourneys();
        verify(model, times(1)).addAttribute("journeys", List.of(new JourneyDto()));
    }

    @Test
    void testFindJourneys_WithCountry() {
        // Mocking the behavior
        String country = "TestCountry";
        when(journeyService.findJourneysByCountry(country)).thenReturn(List.of(new JourneyDto()));

        // Calling the method under test
        String viewName = journeyController.findJourneys(country, model);

        // Assertions
        assertEquals("journeys/list", viewName);
        verify(journeyService, times(1)).findJourneysByCountry(country);
        verify(model, times(1)).addAttribute("journeys", List.of(new JourneyDto()));
    }

    @Test
    void testFindJourney_ExistingId() {
        // Mocking the behavior
        Long id = 1L;
        JourneyDto journeyDto = new JourneyDto();
        when(journeyService.findJourneyById(id)).thenReturn(journeyDto);

        // Calling the method under test
        String viewName = journeyController.findJourney(model, id);

        // Assertions
        assertEquals("journeys/page", viewName);
        verify(journeyService, times(1)).findJourneyById(id);
        verify(model, times(1)).addAttribute("journey", journeyDto);
    }

//    @Test
//    void testFindJourney_NonExistingId() {
//        // Mocking the behavior
//        Long id = 1L;
//        when(journeyService.findJourneyById(id)).thenThrow(EntityNotFoundException.class);
//
//        // Calling the method under test
//        String viewName = journeyController.findJourney(model, id);
//
//        // Assertions
//        assertEquals("journeys/error", viewName);
//        verify(model, times(1)).addAttribute("errorMessage", "404 Not Found: null");
//    }

    @Test
    void testCreateJourney() {
        // Calling the method under test
        String viewName = journeyController.createJourney(new JourneyDto());

        // Assertions
        assertEquals("journeys/new", viewName);
    }

    @Test
    void testUpdateJourney_ExistingId() {
        // Mocking the behavior
        Long id = 1L;
        JourneyDto journeyDto = new JourneyDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(journeyService.findJourneyById(id)).thenReturn(journeyDto);

        // Calling the method under test
        String viewName = journeyController.handleJourneyUpdate(id, journeyDto, bindingResult);

        // Assertions
        assertEquals("redirect:/journeys", viewName);
        verify(journeyService, times(1)).update(id, journeyDto);
    }

//    @Test
//    void testUpdateJourney_NonExistingId() {
//        // Mocking the behavior
//        Long id = 1L;
//        JourneyDto journeyDto = new JourneyDto();
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//        when(journeyService.findJourneyById(id)).thenThrow(EntityNotFoundException.class);
//
//        // Calling the method under test
//        String viewName = journeyController.handleJourneyUpdate(id, journeyDto, bindingResult);
//
//        // Assertions
//        assertEquals("journeys/error", viewName);
//        verify(model, times(1)).addAttribute("errorMessage", "404 Not Found: null");
//    }


}