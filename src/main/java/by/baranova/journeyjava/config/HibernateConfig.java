package by.baranova.journeyjava.config;

import by.baranova.journeyjava.model.Journey;
import by.baranova.journeyjava.model.TravelAgency;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(HibernateProperties hibernateProperties) {

        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.username", hibernateProperties.getUsername());
        properties.setProperty("hibernate.connection.password", hibernateProperties.getPassword());
        properties.setProperty("hibernate.connection.url", hibernateProperties.getUrl());
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.highlight_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        return new org.hibernate.cfg.Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Journey.class)
                .addAnnotatedClass(TravelAgency.class)
                .buildSessionFactory();
    }

}
