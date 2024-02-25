package by.baranova.journeyjava.controller;

import by.baranova.journeyjava.exception.EntityNotFoundException;
import by.baranova.journeyjava.service.JourneyService;
import by.baranova.journeyjava.model.JourneyDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/journeys")
public class JourneyController {

    private static final String CONST_ATTRIBUTE = "journey";
    private static final String CONST_REDIRECT = "redirect:/journeys";
    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping
    public String findJourneys(@RequestParam(name = "country", required = false) String country, Model model) {
        List<JourneyDto> journeys;

        if (country != null) {
            journeys = journeyService.findJourneysByCountry(country);
        } else {
            journeys = journeyService.findJourneys();
        }
        model.addAttribute("journeys", journeys);
        return "journeys/list";
    }

    @DeleteMapping("/delete")
    public String handleJourneyDeleteByCountry(@RequestParam(name = "country") String country) {
        journeyService.deleteByCountry(country);
        return CONST_REDIRECT;
    }

    @GetMapping("/{id}")
    public String findJourney(Model model, @PathVariable("id") Long id) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute(CONST_ATTRIBUTE, journey);
            return "journeys/page";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @GetMapping("/new")
    public String createJourney(@ModelAttribute(CONST_ATTRIBUTE) JourneyDto student) {
        return "journeys/new";
    }

    @PostMapping("/new")
    public String handleJourneyCreation(
            @Valid @ModelAttribute(CONST_ATTRIBUTE) JourneyDto journey, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/new";
        }
        journeyService.save(journey);
        return CONST_REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String updateJourney(@PathVariable Long id, Model model) {
        final JourneyDto journey = journeyService.findJourneyById(id);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final String formattedDate = journey.getDateToJourney().format(formatter);
        model.addAttribute(CONST_ATTRIBUTE, journey);
        model.addAttribute("formattedDate", formattedDate);
        return "journeys/update";
    }

    @PutMapping("/{id}")
    public String handleJourneyUpdate(
            @PathVariable Long id,
            @Valid @ModelAttribute(CONST_ATTRIBUTE) JourneyDto journey,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "journeys/update";
        journeyService.update(id, journey);
        return CONST_REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute(CONST_ATTRIBUTE, journey);
            return "journeys/delete";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @DeleteMapping("/{id}")
    public String handleJourneyDelete(@PathVariable Long id) {
        journeyService.deleteById(id);
        return CONST_REDIRECT;
    }
}

