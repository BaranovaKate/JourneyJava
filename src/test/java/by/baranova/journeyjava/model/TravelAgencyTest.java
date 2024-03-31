package by.baranova.journeyjava.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelAgencyTest {

    @Test
    void testTravelAgencyConstructorAndGetters() {
        Long id = 1L;
        String name = "Travel Agency";
        List<Journey> journeys = new ArrayList<>();

        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(id);
        travelAgency.setName(name);
        travelAgency.setJourneys(journeys);

        assertEquals(id, travelAgency.getId());
        assertEquals(name, travelAgency.getName());
        assertEquals(journeys, travelAgency.getJourneys());
    }
}