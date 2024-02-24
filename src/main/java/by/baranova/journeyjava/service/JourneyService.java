package by.baranova.journeyjava.service;
import by.baranova.journeyjava.exception.EntityNotFoundException;
import by.baranova.journeyjava.repository.JourneyRepository;
import by.baranova.journeyjava.model.JourneyDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public List<JourneyDto> findJourneys() {
        return journeyRepository.findAll();
    }

    public JourneyDto findJourneyById(Long id) {
        return journeyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Journey with id " + id + " does not exist"));
    }

    public void deleteById(Long id) {
        journeyRepository.deleteById(id);
    }

    public void save(JourneyDto journeyDto) {
        journeyRepository.save(journeyDto);
    }

    public void update(Long id, JourneyDto journey) {
        journeyRepository.update(id, journey);
    }
}