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

//
//@RestController
//@RequestMapping("/journeys")
//public class JourneyController {
//
//    private final JourneyService journeyService;
//
//    public JourneyController(JourneyService journeyService) {
//        this.journeyService = journeyService;
//    }
//
//    @GetMapping
//    public List<JourneyDto> findJourneys() {
//        return journeyService.findJourneys();
//    }
//
//    @GetMapping("/{id}")
//    public JourneyDto findJourney(@PathVariable("id") Long id) {
//        return journeyService.findJourneyById(id);
//    }
//
//    @PostMapping("/new")
//    public void handleJourneyCreation(
//            @Valid @RequestBody JourneyDto journey) {
//        journeyService.save(journey);
//    }
//
//    @PutMapping("/{id}")
//    public void handleJourneyUpdate(
//            @PathVariable Long id,
//            @Valid @RequestBody JourneyDto journey) {
//        journeyService.update(id, journey);
//    }
//
//    @DeleteMapping("/{id}")
//    public void handleJourneyDelete(@PathVariable Long id) {
//        journeyService.deleteById(id);
//    }
//}
//


@Controller
@RequestMapping("/journeys")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @GetMapping
    public String findJourneys(Model model) {
        final List<JourneyDto> journeys = journeyService.findJourneys();

        model.addAttribute("journeys", journeys);

        return "journeys/list";
    }

    @GetMapping("/{id}")
    public String findJourney(Model model, @PathVariable("id") Long id) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute("journey", journey);
            return "journeys/page";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @GetMapping("/new")
    public String createJourney(@ModelAttribute("journey") JourneyDto student) {
        return "journeys/new";
    }

    @PostMapping("/new")
    public String handleJourneyCreation(
            @Valid @ModelAttribute("journey") JourneyDto journey, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "journeys/new";
        }

        journeyService.save(journey);
        return "redirect:/journeys";
    }

    @GetMapping("/update/{id}")
    public String updateJourney(@PathVariable Long id, Model model) {
        final JourneyDto journey = journeyService.findJourneyById(id);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final String formattedDate = journey.getDateToJourney().format(formatter);
        model.addAttribute("journey", journey);
        model.addAttribute("formattedDate", formattedDate);
        return "journeys/update";
    }

    @PutMapping("/{id}")
    public String handleJourneyUpdate(
            @PathVariable Long id,
            @Valid @ModelAttribute("journey") JourneyDto journey,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "journeys/update";

        journeyService.update(id, journey);
        return "redirect:/journeys";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        try {
            final JourneyDto journey = journeyService.findJourneyById(id);
            model.addAttribute("journey", journey);
            return "journeys/delete";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "journeys/error";
        }
    }

    @DeleteMapping("/{id}")
    public String handleJourneyDelete(@PathVariable Long id) {

        journeyService.deleteById(id);
        return "redirect:/journeys";


    }

}


