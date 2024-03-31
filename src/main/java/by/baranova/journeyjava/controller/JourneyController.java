package by.baranova.journeyjava.controller;

import by.baranova.journeyjava.dto.JourneyDto;
import by.baranova.journeyjava.service.CounterServiceJourney;
import by.baranova.journeyjava.service.JourneyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/journeys")
@AllArgsConstructor
@Tag(name = "Работа с путешествиями", description = "Данный "
        + "контроллер позволяет получать, добавлять, "
        + "обновлять и удалять путешествия")
public class JourneyController {

    private static final String REDIRECT = "redirect:/journeys";
    private static final String ATTRIBUTE = "journey";
    private static final String ERROR = "404 Not Found: {}";

    private final JourneyService journeyService;
    static final Logger LOGGER = LogManager.getLogger(JourneyController.class);

    @GetMapping
    @Operation(
            method = "GET",
            summary = "Получить список всех путешествий",
            description = "Выводит список всех путешествий. "
                    + "Так же выполняет поиск по стране"
    )
    public String findJourneys(final @RequestParam(
            name = "country", required = false) String country, Model model) {
        List<JourneyDto> journeys;
        if (country != null) {
            LOGGER.info("Display Journeys by country");
            journeys = journeyService.findJourneysByCountry(country);
        } else {

            CounterServiceJourney.incrementRequestCount();
            int requestCount = CounterServiceJourney.getRequestCount();
            LOGGER.info("Текущее количество запросов: {}", requestCount);

            LOGGER.info("Display all Journeys");
            journeys = journeyService.findJourneys();
        }
        model.addAttribute("journeys", journeys);
        return "journeys/list";
    }

    @GetMapping("/{id}")
    @Operation(
            method = "GET",
            summary = "Получить путешествие по id",
            description = "Выводит путешествие по id,"
                    + " содержащееся в базе данных"
    )
    public String findJourney(Model model, @PathVariable("id") Long id) {
        try {

            CounterServiceJourney.incrementRequestCount();
            int requestCount = CounterServiceJourney.getRequestCount();
            LOGGER.info("Текущее количество запросов: {}", requestCount);


            LOGGER.info("Display Journey by id");
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute(ATTRIBUTE, journey);
            return "journeys/page";
        } catch (EntityNotFoundException e) {

            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @GetMapping("/new")
    public String createJourney(@ModelAttribute(ATTRIBUTE) JourneyDto journey) {
        return "journeys/new";
    }

    @PostMapping("/new")
    @Operation(
            method = "POST",
            summary = "Создать путешествие",
            description = "Создает новое путешествие в базе данных"
    )
    public String handleJourneyCreation(
            @Valid @ModelAttribute(ATTRIBUTE) JourneyDto journey, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/new";
        }
        LOGGER.info("Create Journey");
        journeyService.save(journey);
        return REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String updateJourney(@PathVariable Long id, Model model) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final String formattedDate = journey.getDateToJourney().format(formatter);
            model.addAttribute(ATTRIBUTE, journey);
            model.addAttribute("formattedDate", formattedDate);
            return "journeys/update";
        } catch (EntityNotFoundException e) {
            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @PutMapping("/{id}")
    public String handleJourneyUpdate(
            @PathVariable Long id,
            @Valid @ModelAttribute(ATTRIBUTE) JourneyDto journey,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "journeys/update";
        journeyService.update(id, journey);
        LOGGER.info("Update Journey");
        return REDIRECT;
    }

    @PostMapping("/new/bulk/{agency}")
    public ResponseEntity<String> createJourneysBulk(@RequestBody List<JourneyDto> journeyDtos,
                                                     @PathVariable("agency") String agency) {
        LOGGER.info("POST endpoint /journeys/new/bulk/{agency} was called");

        try {
            journeyService.createJourneysBulk(journeyDtos, agency);
            return ResponseEntity.ok("Successfully created journeys in bulk for agency: " + agency);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute(ATTRIBUTE, journey);
            return "journeys/delete";
        } catch (EntityNotFoundException e) {
            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @Operation(
            method = "DELETE",
            summary = "Удалить путешествие по id",
            description = "Удаляет путешествие по id,"
                    + " из базы данных"
    )
    @DeleteMapping("/{id}")
    public String handleJourneyDelete(@PathVariable Long id) {
        journeyService.deleteById(id);
        LOGGER.info("Delete Journey by id");
        return REDIRECT;
    }

}