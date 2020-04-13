package ru.ngu.JiraJuniorDeveloper.Configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"ru.ngu.JiraJuniorDeveloper.Web", "ru.ngu.JiraJuniorDeveloper.DataBase","ru.ngu.JiraJuniorDeveloper.Configurations"})
public class ProdConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ProdPersistenceUnit");
    }
}
