package by.baranova.journeyjava.repository;

import by.baranova.journeyjava.mapper.JourneyMapper;
import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.JourneyDto;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JourneyRepository {

    private final SessionFactory sessionFactory;
    private final JourneyMapper journeyMapper;
    private static final String CONST_UPDATE = """
                    UPDATE Journey S SET\s
                       S.country = :country,\s
                       S.town = :town,\s
                       S.dateToJourney = :dateToJourney,\s
                       S.dateFromJourney = :dateFromJourney
                    WHERE S.id = :id""";

    public JourneyRepository(SessionFactory sessionFactory, JourneyMapper journeyMapper) {
        this.sessionFactory = sessionFactory;
        this.journeyMapper = journeyMapper;
    }

    public List<JourneyDto> findAll() {
        final List<Journey> journeys = sessionFactory.fromSession(session -> {
            Query<Journey> query = session.createQuery("FROM Journey ", Journey.class);
            return query.list();
        });
        return journeys.stream()
                .map(journeyMapper::toDto)
                .toList();
    }

    public Optional<JourneyDto> findById(Long id) {
        return sessionFactory.fromSession(session -> {
            Query<Journey> query = session.createQuery("FROM Journey S WHERE S.id = :id", Journey.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional().map(journeyMapper::toDto);
        });
    }

    public void deleteById(Long id) {
        sessionFactory.inTransaction(session -> {
            final MutationQuery query = session.createMutationQuery("""
                    DELETE FROM Journey
                    WHERE id = :id
                    """);
            query.setParameter("id", id);
            query.executeUpdate();
        });
    }

    public void save(JourneyDto journeyDto) {
        final Journey journey = journeyMapper.toEntity(journeyDto);
        sessionFactory.inTransaction(session -> {
            session.persist(journey);
        });
    }

    public void update(Long id, JourneyDto journey) {
        sessionFactory.inTransaction(session -> {
            final MutationQuery query = session.createMutationQuery(CONST_UPDATE);

            query.setParameter("id", id);
            query.setParameter("country", journey.getCountry());
            query.setParameter("town", journey.getTown());
            query.setParameter("dateToJourney", journey.getDateToJourney());
            query.setParameter("dateFromJourney", journey.getDateFromJourney());

            query.executeUpdate();
        });
    }
}