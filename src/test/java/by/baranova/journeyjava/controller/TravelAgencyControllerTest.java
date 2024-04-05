package by.baranova.journeyjava.controller;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import by.baranova.journeyjava.service.AgencyService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TravelAgencyControllerTest {

    @Mock
    AgencyService agencyService;

    @Mock
    Model model;

    @InjectMocks
    TravelAgencyController travelAgencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTravelAgencyById_ExistingId() {
        // Mocking the behavior
        Long id = 1L;
        TravelAgency agency = new TravelAgency();
        when(agencyService.findAgencyById(id)).thenReturn(agency);

        // Calling the method under test
        String viewName = travelAgencyController.getTravelAgencyById(model, id);

        // Assertions
        assertEquals("journeys/pageAgency", viewName);
        verify(model, times(1)).addAttribute("agency", agency);
    }

//    @Test
//    void testGetTravelAgencyById_NonExistingId() {
//        // Mocking the behavior
//        Long id = 1L;
//        when(agencyService.findAgencyById(id)).thenThrow(EntityNotFoundException.class);
//
//        // Calling the method under test
//        String viewName = travelAgencyController.getTravelAgencyById(model, id);
//
//        // Assertions
//        assertEquals("journeys/error", viewName);
//        verify(model, times(1)).addAttribute("errorMessage", "404 Not Found: null");
//    }

    @Test
    void testGetAllTravelAgenciesWithJourneys() {
        // Mocking the behavior
        List<TravelAgency> agencies = List.of(new TravelAgency(), new TravelAgency());
        when(agencyService.findAgencies()).thenReturn(agencies);

        // Calling the method under test
        String viewName = travelAgencyController.getAllTravelAgenciesWithJourneys(model);

        // Assertions
        assertEquals("journeys/listAgency", viewName);
        verify(model, times(1)).addAttribute("agencies", agencies);
    }

    @Test
    void testCreateTravelAgency_ValidInput() {
        // Mocking the behavior
        TravelAgency agency = new TravelAgency();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        // Calling the method under test
        String viewName = travelAgencyController.createTravelAgency(agency, bindingResult);

        // Assertions
        assertEquals("redirect:/travel-agencies", viewName);
        verify(agencyService, times(1)).save(agency);
    }

    @Test
    void testCreateTravelAgency_InvalidInput() {
        // Mocking the behavior
        TravelAgency agency = new TravelAgency();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Calling the method under test
        String viewName = travelAgencyController.createTravelAgency(agency, bindingResult);

        // Assertions
        assertEquals("journeys/newAgency", viewName);
        verify(agencyService, never()).save(agency);
    }

    @Test
    void testDeleteTravelAgencyById() {
        // Mocking the behavior
        Long id = 1L;

        // Calling the method under test
        String viewName = travelAgencyController.deleteTravelAgencyById(id);

        // Assertions
        assertEquals("redirect:/travel-agencies", viewName);
        verify(agencyService, times(1)).deleteById(id);
    }

    @Test
    void testShowDeleteForm_ExistingId() {
        // Mocking the behavior
        Long id = 1L;
        TravelAgency agency = new TravelAgency();
        when(agencyService.findAgencyById(id)).thenReturn(agency);

        // Calling the method under test
        String viewName = travelAgencyController.showDeleteForm(id, model);

        // Assertions
        assertEquals("journeys/deleteAgency", viewName);
        verify(model, times(1)).addAttribute("agency", agency);
    }

//    @Test
//    void testShowDeleteForm_NonExistingId() {
//        // Mocking the behavior
//        Long id = 1L;
//        when(agencyService.findAgencyById(id)).thenThrow(EntityNotFoundException.class);
//
//        // Calling the method under test
//        String viewName = travelAgencyController.showDeleteForm(id, model);
//
//        // Assertions
//        assertEquals("journeys/error", viewName);
//        verify(model, times(1)).addAttribute("errorMessage", "404 Not Found: null");
//    }

//    @Test
//    void testUpdateAgency_ExistingId() {
//        // Arrange
//        Long id = 1L;
//        TravelAgencyDto agencyDto = new TravelAgencyDto();
//        model.addAttribute("agencyDto", agencyDto);
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(agencyService.findAgencyById(id)).thenReturn(new TravelAgency());
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        // Act
//        String viewName = travelAgencyController.updateAgency(id, model);
//
//        // Assert
//        assertEquals("redirect:/travel-agencies", viewName);
//        verify(agencyService, times(1)).update(id, agencyDto);
//    }

//    @Test
//    void testUpdateAgency_NonExistingId() {
//        // Mocking the behavior
//        Long id = 1L;
//        TravelAgencyDto agencyDto = new TravelAgencyDto();
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(agencyService.findAgencyById(id)).thenThrow(EntityNotFoundException.class);
//
//        // Calling the method under test
//        String viewName = travelAgencyController.updateAgency(id, model);
//
//        // Assertions
//        assertEquals("journeys/error", viewName);
//        verify(model, times(1)).addAttribute("errorMessage", "404 Not Found: null");
//    }
}
