package ru.ngu.JiraJuniorDeveloper.Configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "ru.ngu.JiraJuniorDeveloper.DataBase")
public class AppConfiguration {


}
