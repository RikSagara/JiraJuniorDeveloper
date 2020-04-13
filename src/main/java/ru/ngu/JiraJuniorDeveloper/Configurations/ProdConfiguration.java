package ru.ngu.JiraJuniorDeveloper.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@ComponentScan(basePackages = {"ru.ngu.JiraJuniorDeveloper.Web", "ru.ngu.JiraJuniorDeveloper.DataBase","ru.ngu.JiraJuniorDeveloper.Configurations"})
public class ProdConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("ProdPersistenceUnit");
        return bean;
    }
}
