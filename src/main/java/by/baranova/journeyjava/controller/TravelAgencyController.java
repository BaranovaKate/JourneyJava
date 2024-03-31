package by.baranova.journeyjava.controller;

import by.baranova.journeyjava.dto.TravelAgencyDto;
import by.baranova.journeyjava.model.TravelAgency;
import by.baranova.journeyjava.service.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/travel-agencies")
@AllArgsConstructor
@Tag(name = "Работа с туристическими агенствами",
        description = "Данный контроллер позволяет получать,"
                + " добавлять, обновлять и удалять тур агенства")
public class TravelAgencyController {
    private static final String REDIRECT = "redirect:/travel-agencies";
    private static final String ERROR = "404 Not Found: {}";

    private final AgencyService agencyService;

    static final Logger LOGGER = LogManager
            .getLogger(TravelAgencyController.class);

    @GetMapping("/{id}")
    @Operation(
            method = "GET",
            summary = "Получить список тур агентств по id",
            description = "Выводит тур агентство по id,"
                    + " содержащеесся в базе данных"
    )
    public String getTravelAgencyById(Model model, final @PathVariable("id") Long id) {
        try {
            LOGGER.info("Display Travel Agencies by id");
            TravelAgency agency = agencyService.findAgencyById(id);
            model.addAttribute("agency", agency);
            return "journeys/pageAgency";
        } catch (EntityNotFoundException e) {
            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @GetMapping
    @Operation(
            method = "GET",
            summary = "Получить список всех тур агентств",
            description = "Выводит список всех тур агентств,"
                    + " содержащихся в базе данных"
    )
    public String getAllTravelAgenciesWithJourneys(Model model) {
        List<TravelAgency> agencies;
        LOGGER.info("Display Travel Agencies");
        agencies = agencyService.findAgencies();
        model.addAttribute("agencies", agencies);
        return "journeys/listAgency";
    }

    @GetMapping("/new")
    public String createAgency(@ModelAttribute("agency") TravelAgency agency) {
        return "journeys/newAgency";
    }

    @PostMapping("/new")
    @Operation(
            method = "POST",
            summary = "Создать туристическое агенство",
            description = "Создает новое туристическе агенство"
                    + " и добавляет его в базу данных."
    )
    public String createTravelAgency(
            @Valid @ModelAttribute("agency") TravelAgency agency, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/newAgency";
        }
        agencyService.save(agency);
        LOGGER.info("Create Travel Agencies");
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    @Operation(
            method = "DELETE",
            summary = "Удалить туристическое агенство",
            description = "Удаляет туристическое агенство из базы данных"
    )
    public String deleteTravelAgencyById(final @PathVariable Long id) {
        agencyService.deleteById(id);
        LOGGER.info("Delete Travel Agency by Id");
        return REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            final TravelAgency agency = agencyService.findAgencyById(id);
            model.addAttribute("agency", agency);
            return "journeys/deleteAgency";
        } catch (EntityNotFoundException e) {
            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @GetMapping("/update/{id}")
    public String updateAgency(@PathVariable Long id, Model model) {
        try {
            final TravelAgency agencyDto = agencyService.findAgencyById(id);
            model.addAttribute("agencyDto", agencyDto);
            return "journeys/updateAgency";
        } catch (EntityNotFoundException e) {
            LOGGER.error(ERROR, e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @PutMapping("/{id}")
    public String handleAgencyUpdate(@PathVariable Long id,
                                     @Valid @ModelAttribute("agencyDto") TravelAgencyDto travelAgency,
                                     BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "journeys/updateAgency";
        agencyService.update(id, travelAgency);
        LOGGER.info("Update Agency");
        return REDIRECT;
    }

}
