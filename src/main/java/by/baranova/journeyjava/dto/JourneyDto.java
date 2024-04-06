package by.baranova.journeyjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Data Transfer Object (DTO) representing a journey.
 * Used for transferring journey data between layers of the application.
 */

public class JourneyDto implements Serializable {

    /** Constant. */
    private static final int CONST = 20;

    /** Unique identifier for the journey. */
    private Long id;

    /** The country of the journey. */
    @NotBlank
    @Size(min = 2, max = CONST)
    private String country;

    /** The town of the journey. */
    @NotBlank
    @Size(min = 2, max = CONST)
    private String town;

    /** The end date of the journey. */
    @NotNull
    private LocalDate dateToJourney;

    /** The start date of the journey. */
    @NotNull
    private LocalDate dateFromJourney;

    /** The DTO representing the travel agency associated with the journey. */
    private TravelAgencyDto travelAgency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public LocalDate getDateToJourney() {
        return dateToJourney;
    }

    public void setDateToJourney(LocalDate dateToJourney) {
        this.dateToJourney = dateToJourney;
    }

    public LocalDate getDateFromJourney() {
        return dateFromJourney;
    }

    public void setDateFromJourney(LocalDate dateFromJourney) {
        this.dateFromJourney = dateFromJourney;
    }

    public TravelAgencyDto getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgencyDto travelAgency) {
        this.travelAgency = travelAgency;
    }

    public JourneyDto() {
    }

    @Override
    public String toString() {
        return "JourneyDto{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", dateToJourney=" + dateToJourney +
                ", dateFromJourney=" + dateFromJourney +
                ", travelAgency=" + travelAgency +
                '}';
    }
}
