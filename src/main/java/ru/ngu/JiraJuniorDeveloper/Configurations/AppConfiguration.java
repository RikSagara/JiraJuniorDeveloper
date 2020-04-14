package ru.ngu.JiraJuniorDeveloper.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.ngu.JiraJuniorDeveloper.DataBase")
public class AppConfiguration {


}
