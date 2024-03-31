package by.baranova.journeyjava.mapper;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class TravelAgencyMapperTest {

    @Test
    void testToDto() {
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(1L);
        travelAgency.setName("Travel World");

        TravelAgencyDto dto = TravelAgencyMapper.toDto(travelAgency);

        assertEquals(travelAgency.getId(), dto.getId());
        assertEquals(travelAgency.getName(), dto.getName());
    }
}