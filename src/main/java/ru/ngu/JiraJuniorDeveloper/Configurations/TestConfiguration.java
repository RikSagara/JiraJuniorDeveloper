package ru.ngu.JiraJuniorDeveloper.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Configuration
@ComponentScan(basePackages = {"ru.ngu.JiraJuniorDeveloper.Web",
                                "ru.ngu.JiraJuniorDeveloper.DataBase",
                                "ru.ngu.JiraJuniorDeveloper.Configurations"},
        excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {ProdConfiguration.class,WebConfiguration.class}
        ))
public class TestConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("TestPersistenceUnit");
        return bean;
    }
}
