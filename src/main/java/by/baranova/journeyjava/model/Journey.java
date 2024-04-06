package by.baranova.journeyjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Entity class representing a journey.
 */

//@Data
@Entity
@Table(name = "journeys")
public class Journey {
    /** Constant. */
    private static final int CONST = 32;

    /** Unique identifier for the journey. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** The country of the journey. */
    @Column(name = "country", nullable = false, length = CONST)
    private String country;

    /** The town of the journey. */
    @Column(name = "town", nullable = false, length = CONST)
    private String town;

    /** The end date of the journey. */
    @Column(name = "dateToJourney", nullable = false)
    private LocalDate dateToJourney;

    /** The start date of the journey. */
    @Column(name = "dateFromJourney", nullable = false)
    private LocalDate dateFromJourney;

    /** The travel agency associated with the journey. */
    @ManyToOne
    @JoinColumn(name = "travel_agency_id", nullable = false)
    @JsonIgnoreProperties("journeys")
    private TravelAgency travelAgency;


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

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", dateToJourney=" + dateToJourney +
                ", dateFromJourney=" + dateFromJourney +
                ", travelAgency=" + travelAgency +
                '}';
    }

    public Journey() {
    }
}