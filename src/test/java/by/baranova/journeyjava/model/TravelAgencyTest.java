package by.baranova.journeyjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TravelAgencyTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(id);

        // Act
        Long retrievedId = travelAgency.getId();

        // Assert
        assertEquals(id, retrievedId);
    }

    @Test
    void getName() {
        // Arrange
        String name = "Agency";
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setName(name);

        // Act
        String retrievedName = travelAgency.getName();

        // Assert
        assertEquals(name, retrievedName);
    }

    @Test
    void getJourneys() {
        // Arrange
        List<Journey> journeys = new ArrayList<>();
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setJourneys(journeys);

        // Act
        List<Journey> retrievedJourneys = travelAgency.getJourneys();

        // Assert
        assertEquals(journeys, retrievedJourneys);
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        TravelAgency travelAgency = new TravelAgency();

        // Act
        travelAgency.setId(id);

        // Assert
        assertEquals(id, travelAgency.getId());
    }

    @Test
    void setName() {
        // Arrange
        String name = "Agency";
        TravelAgency travelAgency = new TravelAgency();

        // Act
        travelAgency.setName(name);

        // Assert
        assertEquals(name, travelAgency.getName());
    }

    @Test
    void setJourneys() {
        // Arrange
        List<Journey> journeys = new ArrayList<>();
        TravelAgency travelAgency = new TravelAgency();

        // Act
        travelAgency.setJourneys(journeys);

        // Assert
        assertEquals(journeys, travelAgency.getJourneys());
    }

    @Test
    void testEquals() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        agency1.setId(1L);
        agency1.setName("Agency");

        TravelAgency agency2 = new TravelAgency();
        agency2.setId(1L);
        agency2.setName("Agency");

        TravelAgency agency3 = new TravelAgency();
        agency3.setId(2L);
        agency3.setName("Agency");

        // Assert
        assertEquals(agency1, agency2);
        assertNotEquals(agency1, agency3);
    }

    @Test
    void canEqual() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();

        // Assert
        assertTrue(travelAgency.canEqual(new TravelAgency()));
        assertFalse(travelAgency.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        agency1.setId(1L);
        agency1.setName("Agency");

        TravelAgency agency2 = new TravelAgency();
        agency2.setId(1L);
        agency2.setName("Agency");

        TravelAgency agency3 = new TravelAgency();
        agency3.setId(2L);
        agency3.setName("Agency");

        // Assert
        assertEquals(agency1.hashCode(), agency2.hashCode());
        assertNotEquals(agency1.hashCode(), agency3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.setId(1L);
        travelAgency.setName("Agency");

        // Act
        String agencyString = travelAgency.toString();

        // Assert
        assertTrue(agencyString.contains("id=1"));
        assertTrue(agencyString.contains("name=Agency"));
    }
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

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        agency1.setId(1L);
        agency1.setName("Agency Name");

        TravelAgency agency2 = new TravelAgency();
        agency2.setId(1L);
        agency2.setName("Agency Name");

        TravelAgency agency3 = new TravelAgency();
        agency3.setId(2L);
        agency3.setName("Agency Name");

        // Assert
        assertEquals(agency1, agency2);
        assertNotEquals(agency1, agency3);
        assertEquals(agency1.hashCode(), agency2.hashCode());
        assertNotEquals(agency1.hashCode(), agency3.hashCode());
    }

    @Test
    void testEmptyConstructor() {
        // Arrange
        TravelAgency travelAgency = new TravelAgency();

        // Assert
        assertNull(travelAgency.getId());
        assertNull(travelAgency.getName());
        assertNull(travelAgency.getJourneys());
    }

    @Test
    void testSetter() {
        // Arrange
        Long id = 1L;
        String name = "Travel Agency";
        List<Journey> journeys = new ArrayList<>();
        TravelAgency travelAgency = new TravelAgency();

        // Act
        travelAgency.setId(id);
        travelAgency.setName(name);
        travelAgency.setJourneys(journeys);

        // Assert
        assertEquals(id, travelAgency.getId());
        assertEquals(name, travelAgency.getName());
        assertEquals(journeys, travelAgency.getJourneys());
    }

    @Test
    void testEqualsAndHashCodeWithNull() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        TravelAgency agency2 = new TravelAgency();

        // Assert
        assertEquals(agency1, agency2);
        assertEquals(agency1.hashCode(), agency2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithSameReference() {
        // Arrange
        TravelAgency agency1 = new TravelAgency();
        TravelAgency agency2 = agency1;

        // Assert
        assertEquals(agency1, agency2);
        assertEquals(agency1.hashCode(), agency2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentClass() {
        // Arrange
        TravelAgency agency = new TravelAgency();

        // Assert
        assertNotEquals(agency, "Not a TravelAgency");
    }


    // Тест для проверки наличия аннотации @Entity
    @Test
    void testEntityAnnotation() {
        assertTrue(TravelAgency.class.isAnnotationPresent(Entity.class));
    }

    // Тест для проверки наличия аннотации @Table с правильным именем таблицы
    @Test
    void testTableAnnotation() {
        Table tableAnnotation = TravelAgency.class.getAnnotation(Table.class);
        assertNotNull(tableAnnotation);
        assertEquals("travel_agencies", tableAnnotation.name());
    }

    // Тест для проверки наличия аннотации @Id
    // Тест для проверки наличия аннотации @Id
    @Test
    void testIdAnnotationForIdField() {
        try {
            Field field = TravelAgency.class.getDeclaredField("id");
            assertNotNull(field.getAnnotation(Id.class));
        } catch (NoSuchFieldException e) {
            fail("Field 'id' not found");
        }
    }

    // Тест для проверки наличия аннотации @GeneratedValue для поля id
    @Test
    void testGeneratedValueAnnotationForId() throws NoSuchFieldException {
        assertNotNull(TravelAgency.class.getDeclaredField("id").getAnnotation(GeneratedValue.class));
    }

    // Тест для проверки наличия аннотации @Column для поля id с правильными параметрами
    @Test
    void testIdColumnAnnotation() throws NoSuchFieldException {
        Column columnAnnotation = TravelAgency.class.getDeclaredField("id").getAnnotation(Column.class);
        assertNotNull(columnAnnotation);
        assertEquals("id", columnAnnotation.name());
        assertTrue(columnAnnotation.nullable());
    }

    // Тест для проверки наличия аннотации @Column для поля name с правильными параметрами
    @Test
    void testNameColumnAnnotation() throws NoSuchFieldException {
        Column columnAnnotation = TravelAgency.class.getDeclaredField("name").getAnnotation(Column.class);
        assertNotNull(columnAnnotation);
        assertEquals("name", columnAnnotation.name());
        assertFalse(columnAnnotation.nullable());
        assertEquals(64, columnAnnotation.length());
    }

    // Тест для проверки наличия аннотации @OneToMany
    @Test
    void testOneToManyAnnotationForJourneys() {
        try {
            Field field = TravelAgency.class.getDeclaredField("journeys");
            assertNotNull(field.getAnnotation(OneToMany.class));
        } catch (NoSuchFieldException e) {
            fail("Field 'journeys' not found");
        }
    }

    // Тест для проверки наличия аннотации @JsonIgnoreProperties для поля journeys
    @Test
    void testJsonIgnorePropertiesAnnotation() throws NoSuchFieldException {
        JsonIgnoreProperties ignorePropertiesAnnotation = TravelAgency.class.getDeclaredField("journeys").getAnnotation(JsonIgnoreProperties.class);
        assertNotNull(ignorePropertiesAnnotation);
        assertArrayEquals(new String[]{"travelAgency"}, ignorePropertiesAnnotation.value());
    }

    // Тест для проверки сгенерированных методов Lombok
    @Test
    void testLombokGeneratedMethods() {
        TravelAgency travelAgency = new TravelAgency();
        assertNotNull(travelAgency.toString());
        //  assertNotNull(travelAgency.hashCode());
        //assertNotNull(travelAgency.equals(travelAgency));
    }

}
//
//class TravelAgencyTest {
//
//    @Test
//    void testTravelAgencyConstructorAndGetters() {
//        Long id = 1L;
//        String name = "Travel Agency";
//        List<Journey> journeys = new ArrayList<>();
//
//        TravelAgency travelAgency = new TravelAgency();
//        travelAgency.setId(id);
//        travelAgency.setName(name);
//        travelAgency.setJourneys(journeys);
//
//        assertEquals(id, travelAgency.getId());
//        assertEquals(name, travelAgency.getName());
//        assertEquals(journeys, travelAgency.getJourneys());
//    }
//
//    @Test
//    void testToString() {
//        // Arrange
//        TravelAgency travelAgency = new TravelAgency();
//        travelAgency.setId(1L);
//        travelAgency.setName("Agency Name");
//
//        // Act
//        String agencyString = travelAgency.toString();
//
//        // Assert
//        assertTrue(agencyString.contains("id=1"));
//        assertTrue(agencyString.contains("name=Agency Name"));
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        // Arrange
//        TravelAgency agency1 = new TravelAgency();
//        agency1.setId(1L);
//        agency1.setName("Agency Name");
//
//        TravelAgency agency2 = new TravelAgency();
//        agency2.setId(1L);
//        agency2.setName("Agency Name");
//
//        TravelAgency agency3 = new TravelAgency();
//        agency3.setId(2L);
//        agency3.setName("Agency Name");
//
//        // Assert
//        assertEquals(agency1, agency2);
//        assertNotEquals(agency1, agency3);
//        assertEquals(agency1.hashCode(), agency2.hashCode());
//        assertNotEquals(agency1.hashCode(), agency3.hashCode());
//    }
//
//    @Test
//    void testEmptyConstructor() {
//        // Arrange
//        TravelAgency travelAgency = new TravelAgency();
//
//        // Assert
//        assertNull(travelAgency.getId());
//        assertNull(travelAgency.getName());
//        assertNull(travelAgency.getJourneys());
//    }
//
//    @Test
//    void testSetter() {
//        // Arrange
//        Long id = 1L;
//        String name = "Travel Agency";
//        List<Journey> journeys = new ArrayList<>();
//        TravelAgency travelAgency = new TravelAgency();
//
//        // Act
//        travelAgency.setId(id);
//        travelAgency.setName(name);
//        travelAgency.setJourneys(journeys);
//
//        // Assert
//        assertEquals(id, travelAgency.getId());
//        assertEquals(name, travelAgency.getName());
//        assertEquals(journeys, travelAgency.getJourneys());
//    }
//
//    @Test
//    void testEqualsAndHashCodeWithNull() {
//        // Arrange
//        TravelAgency agency1 = new TravelAgency();
//        TravelAgency agency2 = new TravelAgency();
//
//        // Assert
//        assertEquals(agency1, agency2);
//        assertEquals(agency1.hashCode(), agency2.hashCode());
//    }
//
//    @Test
//    void testEqualsAndHashCodeWithSameReference() {
//        // Arrange
//        TravelAgency agency1 = new TravelAgency();
//        TravelAgency agency2 = agency1;
//
//        // Assert
//        assertEquals(agency1, agency2);
//        assertEquals(agency1.hashCode(), agency2.hashCode());
//    }
//
//    @Test
//    void testEqualsAndHashCodeWithDifferentClass() {
//        // Arrange
//        TravelAgency agency = new TravelAgency();
//
//        // Assert
//        assertNotEquals(agency, "Not a TravelAgency");
//    }
//}